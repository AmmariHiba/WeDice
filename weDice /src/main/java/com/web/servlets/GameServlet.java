package com.web.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import com.web.helpers.GameContextManagement;

@WebServlet("/back/game")
public class GameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        play(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        play(request, response);
    }

    protected void play(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the user's session
        HttpSession session = request.getSession();

        // Get the current user from the session
        User user = (User) session.getAttribute("user");

        // Manage data in ServletContext
        GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());
        // Call the updateScore method to update the bestScore
        gameContext.updateScore(user);

        // Get the game state object from the session
        GameState gameState = (GameState) session.getAttribute("gameState");

        // Verifier si la partie doit etre stoppé 
        if (user.getCompteurLancer() >= 3 || gameState.isGameOver()) {
            if (gameState.hasSameDieSelectedTwice()) {
                gameState.addMessage(new Message("Vous avez sélectionné le même dé deux fois. Fin du jeu.", Message.INFO));
                gameState.setGameOver(true);
                gameState.addMessage(new Message("Votre score est -1.", Message.INFO));
            } else {
                if (!gameState.isGameOver()) {
                    gameState.addMessage(new Message("Game Over", Message.INFO));
                    // Indiquer game over
                    gameState.setGameOver(true);
                }
            }
            // Redirection vers game.jsp
            getServletContext().getRequestDispatcher("/WEB-INF/vues/back/game.jsp").forward(request, response);
            return;
        } else {
            String selectedDieStr = request.getParameter("numeroDe");
            int selectedDie = -1;
            if (selectedDieStr != null && !selectedDieStr.isEmpty()) {
                try {
                    selectedDie = Integer.parseInt(selectedDieStr);
                } catch (NumberFormatException e) {
                    
                }
            }

            if (gameState.hasDieBeenSelected(selectedDie)) {
                // Add message indicating the same die selection
                gameState.addMessage(new Message("Vous avez déjà sélectionné le dé " + selectedDie + ". Fin du jeu.", Message.INFO));
                user.setScore(-1);
                // Score : -1
                gameState.setGameOver(true);
                gameState.addMessage(new Message("Votre score est -1.", Message.INFO));
            } else {
                // marquer le dé comme déja lancé
                gameState.markDieAsSelected(selectedDie);

                int result = new Random().nextInt(6) + 1;
                gameState.addDiceResult(selectedDie, result);
                // Verifier si les résultats se repetent 
                if (gameState.hasSuccessiveSameResult(result)) {
                    gameState.addMessage(new Message("Vous avez obtenu le même résultat successivement. Fin du jeu.", Message.INFO));
                    gameState.setGameOver(true);
                }

                // Ajouter message qui indique le resultats du dernier lancé 
                gameState.addMessage(new Message("Résultat du dé " + selectedDie + ": " + result, Message.INFO));

                // Check if the game should end based on the result
                if ((selectedDie == 1 && (result == 6 || result == 5)) ||
                        (selectedDie == 2 && (result == 6 || result == 1)) ||
                        (selectedDie == 3 && (result == 1 || result == 2))) {
                    // Message de game over
                    gameState.addMessage(new Message("Un lancer du dé " + selectedDie + " a donné un résultat invalide. Fin du jeu.", Message.INFO));
                    gameState.setGameOver(true);
                } else {
                    // Mettre à jour le score
                    user.incrementLance();

                    // Verifier s'il faut stopper dans le second lancé 
                    if (user.getCompteurLancer() == 2) {
                        Map<Integer, Integer> diceResults = gameState.getDiceResultsMap();
                        boolean gameShouldEnd = false;
                        for (Map.Entry<Integer, Integer> entry : diceResults.entrySet()) {
                            int previousDieNumber = entry.getKey();
                            int previousDieResult = entry.getValue();
                            // Verifier la croissance ou la décroissance dans le 2eme lancer pour voir s'il faut stopper le jeu
                            if (selectedDie > previousDieNumber && result <= previousDieResult) {
                                gameState.addMessage(new Message("Le résultat du dé " + selectedDie + " doit être supérieur au résultat du dé " + previousDieNumber + ". Fin du jeu.", Message.INFO));
                                gameState.setGameOver(true);
                                gameShouldEnd = true;
                                break;
                            } else if (selectedDie < previousDieNumber && result >= previousDieResult) {
                                gameState.addMessage(new Message("Le résultat du dé " + selectedDie + " doit être inférieur au résultat du dé " + previousDieNumber + ". Fin du jeu.", Message.INFO));
                                gameState.setGameOver(true);
                                gameShouldEnd = true;
                                break;
                            }
                        }
                        if (gameShouldEnd) {
                            // Redirection vers game.jsp pour afficher le message associé au dernier lancé 
                            getServletContext().getRequestDispatcher("/WEB-INF/vues/back/game.jsp").forward(request, response);
                            return;
                        }
                    }

                    // Verifier que les 3 dés sont lancés 
                    if (user.getCompteurLancer() == 3) {
                        // Calcul du resultats 
                        int result1 = gameState.getLastRollResult(1);
                        int result2 = gameState.getLastRollResult(2);
                        int result3 = gameState.getLastRollResult(3);

                        if (result1 < result2 && result2 < result3) {
                            int totalScore = result1 + result2 + result3;
                            user.setScore(totalScore);
                            if (user.getScore() > user.getBestScore()) {
                                user.setBestScore(user.getScore());
                                gameContext.updateScore(user);
                            }
                            gameState.addMessage(new Message("Félicitations ! Votre score est de " + totalScore + " points.", Message.INFO));
                        } else {
                            user.setScore(0);
                            gameState.addMessage(new Message("Oups. Votre score est de 0.", Message.INFO));
                        }
                        // Set game over flag
                        gameState.setGameOver(true);
                    }
                }
            }

            // Redirection vers game.jsp pour afficher le message associé au dernier lancé 
            getServletContext().getRequestDispatcher("/WEB-INF/vues/back/game.jsp").forward(request, response);
        }
    }
}

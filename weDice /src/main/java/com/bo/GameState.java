package com.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class GameState {

    private User user;
    private boolean gameOver = false;
    private List<Message> messages = new ArrayList<>();
    private Map<Integer,Integer> diceResultsMap = new HashMap<>();
    private Set<Integer> selectedDice = new HashSet<>();
    private Integer previousRollResult;

    public void reset() {
        gameOver = false;
        messages.clear();
        user.setScore(0);
        user.setCompteurLancer(0);
        selectedDice.clear();
        diceResultsMap.clear();
        previousRollResult = null;
    }

    public String toString() {
        return "GameState [Score=" + user.getScore() + ", Roll Count=" + user.getCompteurLancer() + ", messages="
                + messages + "]";
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
    

  

	public GameState() {
        this.gameOver = false;
        this.selectedDice = new HashSet<>();
        this.diceResultsMap = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public GameState(User user) {
    	this();
        this.user = user;
    }

 // Method to add the result of a dice roll
    public void addDiceResult(int dieNumber, int result) {
        // Initialize diceResults map if null
        if (diceResultsMap == null) {
            diceResultsMap = new HashMap<>();
        }
        // Add the result to the map with the die number as key
        diceResultsMap.put(dieNumber, result);
        // Mark the selected die as rolled
        markDieAsSelected(dieNumber);
    }
    public int getLastRollResult(int rollNumber) {
        int lastIndex = diceResultsMap.size() - rollNumber;
        return lastIndex >= 0 ? diceResultsMap.getOrDefault(rollNumber, -1) : -1;
    }

    public void markDieAsSelected(int dieNumber) {
        selectedDice.add(dieNumber);
    }

    public boolean hasDieBeenSelected(int dieNumber) {
        return selectedDice.contains(dieNumber);
    }

    public boolean hasSameDieSelectedTwice() {
        Set<Integer> uniqueDice = new HashSet<>();
        for (int die : selectedDice) {
            if (!uniqueDice.add(die)) {
                return true; // Same die selected twice
            }
        }
        return false; // No same die selected twice
    }
    
    public boolean hasSuccessiveSameResult(int currentRollResult) {
        if (previousRollResult != null && currentRollResult == previousRollResult) {
            // If the current roll result is the same as the previous one, reset previousRollResult and return true
            //previousRollResult = null;
            return true;
        } else {
            // Update the previous roll result with the current roll result
            previousRollResult = currentRollResult;
            // Return false indicating no successive same results
            return false;
        }
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

	public Map<Integer, Integer> getDiceResultsMap() {
		return diceResultsMap;
	}

	public void setDiceResultsMap(Map<Integer, Integer> diceResultsMap) {
		this.diceResultsMap = diceResultsMap;
	}
    
}
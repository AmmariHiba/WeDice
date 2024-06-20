package com.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bo.Message;
import com.bo.User;
import com.web.helpers.GameContextManagement;

@WebServlet("/UserManagementServlet")
public class UserManagementServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String createUserFormPage = "/WEB-INF/vues/pages/formInscription.jsp";

        ServletContext cntx = getServletContext();

        // On affiche le formulaire d'ajout
        if (request.getParameter("create") != null) {
            cntx.getRequestDispatcher(createUserFormPage).forward(request, response);
            // fin
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorPage = "/WEB-INF/vues/pages/error.jsp";
        String loginForm = "/WEB-INF/vues/pages/loginForm.jsp";
        ServletContext cntx = getServletContext();

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(nom, prenom, login, password, 0, 0);

        GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());

        List<Message> messages = (List<Message>) request.getAttribute("messages");

        if (messages != null && !messages.isEmpty()) {
            // Forward to the error page if there are validation messages
            cntx.getRequestDispatcher(errorPage).forward(request, response);
            return;
        }

        // On teste si un utilisateur existe avec le login choisi
        if (gameContext.getUserByLogin(login) != null) {
            messages = new ArrayList<>();
            // Ajouter des message d'erreur dans la requete
            messages.add(new Message("Login existe déjà", Message.WARN));
            request.setAttribute("messages", messages);
            cntx.getRequestDispatcher(errorPage).forward(request, response);
            return;
        }

        // On ajoute l'utilisateur
        gameContext.insertUser(user);

        // On redirige vers la page login avec un message de succès
        messages = new ArrayList<>();
        messages.add(new Message("Utilisateur correctement ajouté", Message.INFO));
        
        // On enregistre la liste des messages comme attributs de requete
        request.setAttribute("messages", messages);

        // On redirige vers la vue
        cntx.getRequestDispatcher(loginForm).forward(request, response);
    }
}

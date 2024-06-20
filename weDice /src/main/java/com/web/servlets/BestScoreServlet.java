package com.web.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bo.User;
import com.web.helpers.GameContextManagement;

@WebServlet("/back/bestScore")
public class BestScoreServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the context manager instance
        GameContextManagement contextManager = GameContextManagement.getInstance(getServletContext());

        // Retrieve the list of users with their best scores
        List<User> users = contextManager.getAllUsers();

        // Set the list of users with best scores as a request attribute
        request.setAttribute("users", users);

        // Forward the request to the JSP for display
        getServletContext().getRequestDispatcher("/WEB-INF/vues/back/bestScore.jsp")
                             .forward(request, response);
    }
}

package com.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bo.Message;


@WebFilter("/UserManagementServlet")
public class UserValidationFilter implements Filter {

 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // la validation que pour les requetes POST 
        if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
            List<Message> messages = new ArrayList<>();

            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (isNullOrEmpty(nom) || isNullOrEmpty(prenom) || isNullOrEmpty(login) || isNullOrEmpty(password)) {
                messages.add(new Message("Tous les champs sont obligatoires", Message.WARN));
            }  else if (password.length() < 8) {
                messages.add(new Message("Le mot de passe doit contenir au moins 8 caractères", Message.WARN));
            }

            if (!messages.isEmpty()) {
                request.setAttribute("messages", messages);
                httpRequest.getRequestDispatcher("/WEB-INF/vues/pages/error.jsp").forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}

package com.web.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bo.GameState;

@WebServlet("/back/ReinitGameServlet")
public class ReinitGameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		GameState gs = (GameState) session.getAttribute("gameState");

		if (gs != null) {

			gs.reset();
		}

		// reinit old result avec une valeur impossible pour le dé
		session.setAttribute("old_result", -1);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/back/game.jsp").forward(request, response);

		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

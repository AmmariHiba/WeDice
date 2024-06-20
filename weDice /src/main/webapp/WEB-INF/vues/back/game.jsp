<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bo.GameState"%>
<%@page import="com.bo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Game</title>
<link href="${pageContext.request.contextPath}/style/gamepage.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/gamepage.js"></script>
</head>
<body>

<div class="user-section">
    <h4>Utilisateur connecté: <c:out value="${sessionScope.user.nom}" /></h4>
    <a href="${pageContext.request.contextPath}/back/ReinitGameServlet" class="button">Réinitialiser le jeu</a><br>
    <a href="${pageContext.request.contextPath}/back/bestScore" class="button">Meilleurs scores</a>
    <a href="${pageContext.request.contextPath}/DeconnectServlet" class="logout-button">Se déconnecter</a> 
</div>

<h4>Jeu de dé</h4>
<p>Veuillez saisir le numéro du dé que vous souhaitez lancer :</p>

<form action="${pageContext.request.contextPath}/back/game" method="get">
    <input type="number" id="numeroDe" name="numeroDe" min="1" max="3" required placeholder="Numéro de dé">
    <button type="submit">Lancer le dé</button>
</form>

<%
    GameState gameState = (GameState) request.getSession().getAttribute("gameState");
    if (gameState != null) {
        // Affichage des messages de jeu
        List<Message> messages = gameState.getMessages();
        for (Message message : messages) {
            out.println("<p class='game-message'>" + message.getText() + "</p>");
        }
    }
%>

<c:if test="${sessionScope.gameState != null && sessionScope.gameState.gameOver}">
    <h4>Fin de la partie</h4>
    <p>Votre score pour cette partie : ${sessionScope.user.score}</p>
    <p>Meilleur score : ${sessionScope.user.bestScore}</p>
</c:if>

</body>
</html>

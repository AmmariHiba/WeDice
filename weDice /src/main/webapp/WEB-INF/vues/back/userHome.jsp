<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bo.GameState"%>
<%@page import="com.bo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home</title>
<link href="${pageContext.request.contextPath}/style/homepage.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/homepage.js"></script>
</head>
<body>

<div class="user-section">
    <h4>Utilisateur connecté: <c:out value="${sessionScope.gameState.user.nom}" /></h4>
    <a href="${pageContext.request.contextPath}/NewGameServlet" class="button">Nouvelle Partie</a>
    <a href="${pageContext.request.contextPath}/back/bestScore" class="button">Meilleurs Scores</a>
    <a href="${pageContext.request.contextPath}/DeconnectServlet" class="logout-button">Se déconnecter</a> 
    <button class="button" id="gameInfoButton">À propos du jeu</button>
</div>

<!-- Modal -->
<div id="gameInfoModal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <h2>À propos du jeu</h2>
    <p>C'est un jeu <span class="highlight">purement de hasard</span>. Le but du jeu est de lancer trois dés dans <span class="highlight">n'importe quel ordre</span> et trouver :</p>
    <p><span class="condition">Résultat dé 1 < Résultat dé 2 < Résultat dé 3</span></p>
    <p>Si vous lancez <span class="highlight">le même dé</span>, le jeu se termine et votre score est <span class="score-negative">-1</span></p>
    <p>Si la condition de gain est vérifiée, votre score est <span class="score-positive">la somme des résultats des dés</span></p>
    <p><span class="enjoy">Amusez-vous bien !</span></p>
  </div>
</div>


</body>
</html>

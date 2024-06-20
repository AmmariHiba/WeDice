<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.bo.Message"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.GameState"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>App Game</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/style/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/style/signin.css"
	rel="stylesheet">
</head>

<body>
	
	<div class="container">

		<form class="form-signin" method="POST"
			action="${pageContext.request.contextPath}/LoginServlet">
			<div>
				<a
					href="${pageContext.request.contextPath}/UserManagementServlet?create">S'inscrire</a>
			</div>
			<h2 class="form-signin-heading">Connexion</h2>
			<label for="inputEmail" class="sr-only">Nom d'utilisateur</label> <input
				type="text" class="form-control" placeholder="Nom d'utilisateur"
				required autofocus name="login"> <label for="inputPassword"
				class="sr-only">Mot de passe</label> <input type="password"
				id="inputPassword" class="form-control" placeholder="Password" name="password"
				required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

		<div>

			<ul>

				<c:forEach items="${requestScope.messages}" var="msg">

					
					<c:choose>
					  <c:when test="${msg.type == Message.WARN}">
					   <li style="color: orange">${msg.text}</li>
					  </c:when>
					  <c:when test="${msg.type == Message.INFO}">
					   <li style="color: green">${msg.text}</li>
					  </c:when>
					   <c:when test="${msg.type == Message.ERROR}">
					   <li style="color: red">${msg.text}</li>
					  </c:when>
					  <c:otherwise>
					  <li >${msg.text}</li>
					  </c:otherwise>
					</c:choose>

				</c:forEach>
			</ul>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
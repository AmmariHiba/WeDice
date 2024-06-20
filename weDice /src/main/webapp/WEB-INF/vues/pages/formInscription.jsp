<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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


<link href="${pageContext.request.contextPath}/style/inscription.css"
	rel="stylesheet">

<!-- Custom styles for this template -->

</head>
<body>
    <div class="container">
        <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/UserManagementServlet">
             <div>
            	<a href="${pageContext.request.contextPath}/">Se connecter </a>
       		 </div>
        
            <h2 class="form-signin-heading">Inscription</h2>
		    <label for="nom">Nom<span class="requis">*</span></label> 
			<input type="text" id="nom" name="nom" value="" size="20" maxlength="60" class="form-control small" />
			<label for="prenom">Prénom<span class="requis">*</span></label> 
			<input type="text" id="prenom" name="prenom" value="" size="20" maxlength="60" class="form-control small" /> <br />

			<label for="email">Nom d'utilisateur<span class="requis">*</span></label> 
			<input type="text" id="login" name="login" value="" size="20" maxlength="60" class="form-control" /> <br />

			<label for="mdp">Mot de passe<span class="requis">*</span></label> 
			<input type="password" id="mdp" name="password" value="" size="20" maxlength="20" class="form-control" /> <br />

            <button class="btn btn-lg btn-primary btn-block" type="submit">S'inscrire</button>
        </form>
    </div>
</body>
</html>
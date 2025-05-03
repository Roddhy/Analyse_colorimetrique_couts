<!DOCTYPE html>
<html lang="fr">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/navbar.css">
	<title>Navbar Example</title>
	<style>

	</style>
</head>
<body>
<div class="navbar">
	<div>
		<a href="analyse.jsp">Accueil</a>
		<c:if test="${sessionScope.user.role== 'admin'}">
			<a href="controleurFichier">Historique</a>
			<a href="cout">Paramètre</a>
		</c:if>
		<c:if test="${sessionScope.user.role =='user'}">
			<a href="#">Mon historique d'analyse</a>
		</c:if>
	</div>
	<c:if test="${sessionScope.user!=null}">
		<div class="nav">
			<a href="deconnexion">deconnexion</a>
		</div>
	</c:if>
	<c:if test="${sessionScope.user==null}">
		<div class="nav">
			<a href="#" onclick="showForm()">Se connecter</a>
		</div>
		<div id="overlay" onclick="hideForm()"></div>
		<div id="formContainer">
			<span class="close-btn" onclick="hideForm()">&times;</span>
			<form action="log" method="post">
				<h3>Connexion</h3>
				<div>
					<input type="text" id="login" name="login" required placeholder="LOGIN">
				</div>
				<div>
					<input type="password" id="password" name="mdp"  placeholder="MOT DE PASSE" required>
				</div>
				<div>
					<input type="submit" class="btn btn-primary x" value="connexion">
					<c:if test="${not empty message}">
						<div id="errorMessage" class="alert alert-danger">${message}</div>
					</c:if>
				</div>
			</form>
		</div>
	</c:if>
</div>
<script>
    function showForm() {
        document.getElementById('formContainer').style.display = 'block';
        document.getElementById('overlay').style.display = 'block';
    }

    function hideForm() {
        document.getElementById('formContainer').style.display = 'none';
        document.getElementById('overlay').style.display = 'none';
    }
    window.onload = function () {
        const errorMessage = document.getElementById('errorMessage');
        if (errorMessage) {
            showForm();
        }
    };
</script>
</body>
</html>

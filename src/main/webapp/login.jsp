<%--
  Created by IntelliJ IDEA.
  User: Abdoul
  Date: 24/12/2024
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<title>Connexion</title>
</head>
<body>
<div class="login-container">
	<img src="https://cdn-icons-png.flaticon.com/512/1946/1946429.png" alt="User Icon">
	<form method="post" action="loginController" >
		<input type="text" name="username" placeholder="LOGIN" >
		<input type="password" name="password" placeholder="MOT DE PASSE">
		<input type="submit" value="ENTRER">
	</form>
</div>
</body>
</html>

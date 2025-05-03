<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/analyse.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

<!-- Conteneur principal pour la confirmation -->
<div class="container text-center mt-5">
    <!-- Message de confirmation stylisé -->
    <div class="alert alert-success custom-alert" role="alert">
        ${messageInfo}
    </div>

    <!-- Bouton de retour à l'accueil -->
    <div class="mt-4">
        <a href="analyse.jsp" class="btn btn-primary btn-lg custom-btn">Retour à l'accueil</a>
    </div>
</div>

</body>
</html>

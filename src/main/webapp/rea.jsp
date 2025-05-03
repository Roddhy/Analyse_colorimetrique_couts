
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<head>
    <title>Affichage des données</title>
    <Link rel="stylesheet" type="text/css" href="css/rea.css">
    <!-- Inclure Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container my-5">
    <h1 class="text-center mb-4">Détails du fichier</h1>
    <c:if test="${not empty fichier}">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Chemin</th>
                <th>Nombre de pages</th>
                <th>Date d'analyse</th>
                <th>Coût</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${fichier.id}</td>
                <td>${fichier.nom}</td>
                <td>${fichier.chemin}</td>
                <td>${fichier.nbrPage}</td>
                <td>${fichier.dateAnalyse}</td>
                <td>${fichier.cout}</td>
            </tr>
            </tbody>
        </table>
    </c:if>

    <h2 class="mt-5">Pages</h2>
    <c:if test="${not empty page}">
        <table class="table table-bordered table-hover">
            <thead class="table-primary">
            <tr>
                <th>Numéro de page</th>
                <th>Pixel Noir</th>
                <th>Pixel Cyan</th>
                <th>Pixel Jaune</th>
                <th>Pixel Magenta</th>
                <th>Pixel Blanc</th>
                <th>Combinaison</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page}" var="page">
                <tr>
                    <td>${page.numeroPage}</td>
                    <td>${page.pixelNoir}</td>
                    <td>${page.pixelCyan}</td>
                    <td>${page.pixelJaune}</td>
                    <td>${page.pixelMagenta}</td>
                    <td>${page.blanc}</td>
                    <td>${page.pixelCombinaison}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <h2 class="mt-5">Couvertures</h2>
    <c:if test="${not empty couvertures}">
        <table class="table table-striped table-hover">
            <thead class="table-secondary">
            <tr>
                <th>Numéro de page</th>
                <th>Noir</th>
                <th>Cyan</th>
                <th>Jaune</th>
                <th>Magenta</th>
                <th>Blanc</th>
                <th>Combinaison</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${couvertures}" var="couverture">
                <tr>
                    <td>${couverture.numeroPage}</td>
                    <td>${couverture.noir}</td>
                    <td>${couverture.cyan}</td>
                    <td>${couverture.jaune}</td>
                    <td>${couverture.magenta}</td>
                    <td>${couverture.blanc}</td>
                    <td>${couverture.combinaison}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <h2 class="mt-5">Total Pixel</h2>
    <c:if test="${not empty totalPixel}">
        <table class="table table-bordered table-hover">
            <thead class="table-success">
            <tr>
                <th>Noir</th>
                <th>Cyan</th>
                <th>Jaune</th>
                <th>Magenta</th>
                <th>Combinaison</th>
                <th>Blanc</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${totalPixel}" var="pixel">
                <tr>
                    <td>${pixel.noir}</td>
                    <td>${pixel.cyan}</td>
                    <td>${pixel.jaune}</td>
                    <td>${pixel.magenta}</td>
                    <td>${pixel.combinaison}</td>
                    <td>${pixel.blanc}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div class="mt-4">
    <a href="analyse.jsp" class="btn btn-primary btn-lg custom-btn">Retour à l'accueil</a>
</div>
<!-- Inclure Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

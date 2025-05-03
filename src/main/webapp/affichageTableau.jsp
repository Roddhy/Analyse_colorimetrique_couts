
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="css/affichageTableau.css">
	<title>Affichage Tableau</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h1></h1>
<table>
	<thead>
	<tr>
		<th>Ordre</th>
		<th>Nom du fichier</th>
		<th>Nombre de pages</th>
		<th>Date d'analyse</th>
		<th>Heure d'analyse</th>
		<th>Couverture de noir (%)</th>
		<th>Couverture de jaune (%)</th>
		<th>Couverture de magenta (%)</th>
		<th>Couverture de cyan (%)</th>
		<th>Coût total (€)</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${listDesNomDeFichierSelectionner}" var="fichier">
		<tr>
			<td>${fichier.id}</td>
			<td>${fichier.nom}</td>
			<td>${fichier.nbrPage}</td>
			<td>${fichier.dateAnalyse}</td>
			<td>${fichier.heureAnalyse}</td>
			<td>${fichier.couvertureNoir}</td>
			<td>${fichier.couvertureJaune}</td>
			<td>${fichier.couvertureMagenta}</td>
			<td>${fichier.couvertureCyan}</td>
			<td>${fichier.cout}</td>
			<td><a class="a" href="afficheurHistogrammePerso?id=${fichier.id}"> Detaille</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="mt-4">
	<a href="analyse.jsp" class="btn btn-primary btn-lg custom-btn">Retour à l'accueil</a>
</div>
</body>
</html>

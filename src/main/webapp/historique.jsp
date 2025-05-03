<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/historiques.css">
	<title>Historique</title>
</head>
<body>
<%@ include file="navbar.jsp" %>

<form  action="controleurComparaison" method="post">
		<table>
			<tr><td colspan="5">Liste des precedentes analyses</td></tr>
			<c:if test="${message == null}">
				<tr>
					<td>Ordre</td>
					<td>Nom du fichier</td>
					<td>Date d'analyse</td>
					<td>Heure d'analyse</td>
					<td>Action</td>
					<td>Option</td>
				</tr>
				<c:forEach items="${fichierPdfList}" var="fichier">
					<tr>
						<td>${fichier.id}</td>
						<td>${fichier.nom}</td>
						<td>${fichier.dateAnalyse}</td>
						<td>${fichier.heureAnalyse}</td>
						<td><input type="checkbox"  name="check" value="${fichier.id}"></td>
						<td><a href="rea?id=${fichier.id}" >Afficher l'analyse</a></td>
					</tr>
				</c:forEach>
		</c:if>
		<c:if test="${message != null}">
			<tr><td colspan="5">${message}</td></tr>
		</c:if>
	</table>
	<input type="submit" value="Comparer">
	<c:if test="${error!=null}">
		<p class="p">${error}</p>
	</c:if>
</form>
<div class="mt-4">
	<a href="analyse.jsp" class="btn btn-primary btn-lg custom-btn">Retour à l'accueil</a>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
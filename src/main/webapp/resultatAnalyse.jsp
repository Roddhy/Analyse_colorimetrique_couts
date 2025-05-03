<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Analyse CMJN et Blanc</title>
	<link rel="stylesheet" type="text/css" href="css/resultats.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>
</head>
<body>
<%@ include file="navbar.jsp" %>
<button onclick="downloadPDF()">Télécharger en PDF</button>

<div class="main">
	<h1>Analyse du fichier : ${p2}</h1>
	<h1>Analyse des pages et des couleurs</h1>
	<p>Nombre de pages : ${p}</p>

	<table border="1">
		<thead>
		<tr>
			<th>Page</th>
			<th colspan="2">Blanc</th>
			<th colspan="2">Cyan</th>
			<th colspan="2">Magenta</th>
			<th colspan="2">Jaune</th>
			<th colspan="2">Noir</th>
			<th colspan="2">Combinaisons</th>
		</tr>
		<tr>
			<th></th>
			<th>Couverture (%)</th>
			<th>Pixels</th>
			<th>Couverture (%)</th>
			<th>Pixels</th>
			<th>Couverture (%)</th>
			<th>Pixels</th>
			<th>Couverture (%)</th>
			<th>Pixels</th>
			<th>Couverture (%)</th>
			<th>Pixels</th>
			<th>Couverture (%)</th>
			<th>Pixels</th>
		</tr>
		</thead>
		<tbody>

		<c:forEach var="index" begin="0" end="${p - 1}">
			<tr>
				<td>${index + 1}</td>
				<td>${couvertureBlanc[index]}</td>
				<td>${PixelsBlanc[index]}</td>
				<td>${couvertureCyan[index]}</td>
				<td>${PixelsCyan[index]}</td>
				<td>${couvertureMagenta[index]}</td>
				<td>${PixelsMagenta[index]}</td>
				<td>${couvertureJaune[index]}</td>
				<td>${PixelsJaune[index]}</td>
				<td>${couvertureNoir[index]}</td>
				<td>${PixelsNoir[index]}</td>
				<td>${couvertureCombinations[index]}</td>
				<td>${PixelsCombinations[index]}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<h1>Analyse globale du fichier PDF</h1>
<%--	<p>Nombre total de pixels : ${totalPixels}</p>--%>

	<table border="1">
		<thead>
		<tr>
			<th>Catégorie</th>
			<th>Nombre de pixels</th>
			<th>Couverture (%)</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>Blanc</td>
			<td>${totalPixelsBlanc}</td>
			<td>${totalCouvertureBlanc}</td>
		</tr>
		<tr>
			<td>Noir</td>
			<td>${totalPixelsNoir}</td>
			<td>${totalCouvertureNoir}</td>
		</tr>
		<tr>
			<td>Cyan</td>
			<td>${totalPixelsCyan}</td>
			<td>${totalCouvertureCyan}</td>
		</tr>
		<tr>
			<td>Magenta</td>
			<td>${totalPixelsMagenta}</td>
			<td>${totalCouvertureMagenta}</td>
		</tr>
		<tr>
			<td>Jaune</td>
			<td>${totalPixelsJaune}</td>
			<td>${totalCouvertureJaune}</td>
		</tr>
		<tr>
			<td>Combinaisons</td>
			<td>${totalPixelsCombinations}</td>
			<td>${totalCouvertureCombinations}</td>
		</tr>
		</tbody>
	</table>

	<div style="font-size: 18px; font-weight: bold; color: #444; margin: 20px 0; line-height: 1.6;">
		Le coût estimé d'impression pour ce fichier <span style="text-decoration: underline;">en couleur</span> est :
		<span style="color: #007bff;">${coutEstimer} €</span>
	</div>
<%--	<div style="font-size: 18px; font-weight: bold; color: #333; margin: 20px 0; line-height: 1.6;">--%>
<%--		Le coût estimé d'impression pour ce fichier en--%>
<%--		<span style="color: #555; text-decoration: underline;">Couleur</span> est :--%>
<%--		<span style="color: #007bff; font-weight: bold;">${coutestimer2} €</span>--%>
<%--	</div>--%>
	<div style="font-size: 18px; font-weight: bold; color: #333; margin: 20px 0; line-height: 1.6;">
		Le coût estimé d'impression pour ce fichier en
		<span style="color: #555; text-decoration: underline;">Noir et Blanc ff</span> est :
		<span style="color: #007bff; font-weight: bold;">${coutestimer3} €</span>
	</div>
</div>



<div class="btn">
	<a  class="btne" href="sauvegarde">Sauvegarder l'analyse</a>
</div>
<div class="btn">
	<a  class="btne" href="analyse.jsp">Refaire une autre Analyse</a>
</div>
<%@ include file="footer.jsp" %>
<script>
	function downloadPDF() {
		const element = document.querySelector('.main');
		html2pdf()
				.from(element)
				.set({
					filename: 'resultat.pdf',
					jsPDF: { unit: 'mm', format: 'a4', orientation: 'landscape' }  // Change l'orientation à paysage
				})
				.save();
	}
</script>

</body>
</html>

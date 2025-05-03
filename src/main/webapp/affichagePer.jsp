<%--
  Created by IntelliJ IDEA.
  User: Abdoul
  Date: 07/12/2024
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Abdoul
  Date: 06/12/2024
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title >Histogrammes par Fichier</title>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>
	<style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            color: #f5f5f5;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }
		h1{
			text-align: center;
			color: black;
		}
		h3{
			text-align: center;
		}
		.chart-item{

		}
		.x{
			width: 100%;
            display: flex;
            flex-wrap: wrap; /* Permet d'aligner plusieurs graphiques */
            gap: 40px; /* Espacement entre les graphiques */
            justify-content: center; /* Centre les graphiques horizontalement */
			padding:0;
			margin-bottom: 23px;
		}
        /* Conteneur principal */
        #charts-container {
            display: flex;
            flex-wrap: wrap; /* Permet d'aligner plusieurs graphiques */
            gap: 20px; /* Espacement entre les graphiques */
            justify-content: center; /* Centre les graphiques horizontalement */
        }
        .title{
            text-align: center;
        }

        /* Style pour chaque graphique */
        .chart-container {
            width: 400px; /* Largeur du graphique */
            height: 300px; /* Hauteur du graphique */
            background-color: #f9f9f9; /* Couleur de fond */
            border: 1px solid #ccc; /* Bordure */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Ombre */
            padding: 10px; /* Espacement interne */
			margin-bottom: 50px;
        }

        .legend {
            margin-top: 10px;
        }
        .legend-item {
            display: flex;
            align-items: center;
            margin-bottom: 5px;
        }
        .legend-color {
            width: 20px;
            height: 20px;
            margin-right: 10px;
            border: 1px solid #000;
        }
        .noir {
            background-color: rgba(0, 0, 0, 0.6);
        }
        .cyan {
            background-color: rgba(0, 255, 255, 0.6);
        }
        .magenta {
            background-color: rgba(255, 0, 255, 0.6);
        }
        .jaune {
            background-color: rgba(255, 255, 0, 0.6);
        }
	</style>

</head>
<body>
<%@ include file="navbar.jsp" %>
<button onclick="downloadPDF()">Télécharger en PDF</button>
<div class="main">
	<h1 class="title">Histogramme par Fichier</h1>

	<!-- Section pour les graphiques -->
	<div id="charts-container" ></div>

	<script>
		// Récupérer les données transmises par la servlet
		const couvertureParFichier = {
			<%
                Map<String, Map<String, Double>> couvertureParFichier =
                    (Map<String, Map<String, Double>>) request.getAttribute("couvertureParFichier");
                for (Map.Entry<String, Map<String, Double>> fileEntry : couvertureParFichier.entrySet()) {
                    String fileName = fileEntry.getKey();
                    Map<String, Double> colors = fileEntry.getValue();
            %>
			"<%= fileName %>": {
				<% for (Map.Entry<String, Double> colorEntry : colors.entrySet()) { %>
				"<%= colorEntry.getKey() %>": <%= colorEntry.getValue() %>,
				<% } %>
			},
			<% } %>
		};
		// Générer un histogramme pour chaque fichier
		const chartsContainer = document.getElementById('charts-container');
		Object.entries(couvertureParFichier).forEach(([fileName, coverage], index) => {
			// Créer un conteneur pour chaque graphique
			const chartContainer = document.createElement('div');
			chartContainer.className = 'chart-container'; // Ajoute une classe CSS
			chartsContainer.appendChild(chartContainer);

			// Créer un canvas pour chaque fichier
			const canvas = document.createElement('canvas');
			canvas.id = `chart-${index}`;
			chartContainer.appendChild(canvas); // Ajoute le canvas au conteneur

			// Données pour Chart.js
			const labels = Object.keys(coverage); // Les couleurs
			const data = Object.values(coverage); // Les pourcentages

			// Générer le graphique
			new Chart(canvas.getContext('2d'), {
				type: 'bar',
				data: {
					labels: labels,
					datasets: [{
						label: fileName,
						data: data,
						backgroundColor: [
							'rgba(0, 0, 0, 0.6)', // Noir
							'rgba(0, 255, 255, 0.6)', // Cyan
							'rgba(255, 0, 255, 0.6)', // Magenta
							'rgba(255, 255, 0, 0.6)' // Jaune
						],
						borderWidth: 1
					}]
				},
				options: {
					responsive: true,
					maintainAspectRatio: false, // Permet de personnaliser hauteur et largeur via CSS
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
		});
	</script>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<title>Diagrammes Circulaires avec Légendes</title>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<h1>Couverture par page du fichier : ${fichier}</h1>

	<div class="x">

		<c:forEach var="page" items="${pages}" varStatus="status">
			<div class="chart-itemX">
				<h3>${page.nomPage}</h3>
				<canvas id="chart-${status.index}" style="max-width: 300px; max-height: 300px;"></canvas>
			</div>

			<script>
				const ctx${status.index} = document.getElementById('chart-${status.index}').getContext('2d');
				new Chart(ctx${status.index}, {
					type: 'pie',
					data: {
						labels: [
							`Noir (<c:out value="${page.pourcentages['Noir']}" />%)`,
							`Cyan (<c:out value="${page.pourcentages['Cyan']}" />%)`,
							`Magenta (<c:out value="${page.pourcentages['Magenta']}" />%)`,
							`Jaune (<c:out value="${page.pourcentages['Jaune']}" />%)`
						],
						datasets: [{
							data: [
								<c:out value="${page.couleurs['Noir']}" />,
								<c:out value="${page.couleurs['Cyan']}" />,
								<c:out value="${page.couleurs['Magenta']}" />,
								<c:out value="${page.couleurs['Jaune']}" />
							],
							backgroundColor: [
								'rgba(0, 0, 0, 0.7)',
								'rgba(0, 255, 255, 0.7)',
								'rgba(255, 0, 255, 0.7)',
								'rgba(255, 255, 0, 0.7)'
							],
							borderWidth: 1
						}]
					},
					options: {
						responsive: true,
						plugins: {
							legend: {
								display: true,
								position: 'top'
							},
							tooltip: {
								callbacks: {
									label: function(tooltipItem) {
										const value = tooltipItem.raw;
										const percentage = ((value / tooltipItem.dataset.data.reduce((a, b) => a + b, 0)) * 100).toFixed(2);
										return `${tooltipItem.label}: ${value} (${percentage}%)`;
									}
								}
							}
						}
					}
				});
			</script>
		</c:forEach>
	</div>
</div>


<%@ include file="footer.jsp" %>
<script>
	function downloadPDF() {
		const element = document.querySelector('.main');
		html2pdf()
				.from(element)
				.set({
					filename: 'details.pdf',
					jsPDF: { unit: 'mm', format: 'a4', orientation: 'landscape' }  // Change l'orientation à paysage
				})
				.save();
	}
</script>
<div class="mt-4">
	<a href="analyse.jsp" class="btn btn-primary btn-lg custom-btn">Retour à l'accueil</a>
</div>

</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Analyse d'un fichier PDF</title>
    <link rel="stylesheet" type="text/css" href="css/analyse.css">
    <link rel="stylesheet" type="text/css" href="css/barreDechargement.css">

    <script>
        function showLoader() {
            document.getElementById("loader").style.display = "block";
        }

        function hideLoader() {
            document.getElementById("loader").style.display = "none";
        }
    </script>

</head>
<body>
<%@ include file="navbar.jsp" %>

    <div class="main-content">
        <div id="loader">
            <div class="spinner"></div>
        </div>
        <h1>Analyse d'un fichier PDF</h1>
        <div class="form-container">
            <form class="x" action="upload" method="post" enctype="multipart/form-data" onsubmit="showLoader()";>
                <label for="fileInput">Entrer un fichier :</label>
                <input type="file" id="fileInput" name="pdfFile" accept="application/pdf" required>
                <input class="x" type="submit" value="Analyser">
            </form>
        </div>
    </div>
<%@ include file="footer.jsp" %>
</body>
</html>

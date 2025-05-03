<!DOCTYPE html>
<html lang="fr">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Paramètres des Coûts</title>
    <link rel="stylesheet" type="text/css" href="css/param.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">
</head>
<body>

<%@ include file="navbar.jsp" %>
    <main>
        <section>
            <h1>Paramètres des Coûts</h1>
            <div class="section" id="cost-parameters">
                <h2>Coût par Unité d’Encre :</h2>
                <form action="cout" method="post">
                    <div>
                        <label for="black"> Noir & blanc :</label>
                        <input type="text" pattern="^\d+(\.\d+)?$"
                               title="Veuillez entrer un nombre valide avec un point comme séparateur décimal" id="black" name="black" placeholder="${cout.noir}"
                               value="${cout.noir}"> €/Unité
                    </div>
                    <div>
                        <label for="magenta"> Magenta :</label>
                        <input type="text"
                               pattern="^\d+(\.\d+)?$"
                               title="Veuillez entrer un nombre valide avec un point comme séparateur décimal"
                               id="magenta" name="magenta" placeholder="${cout.magenta}"  value="${cout.magenta}"> €/Unité
                    </div>
                    <div>
                        <label for="yellow"> Jaune :</label>
                        <input type="text" id="yellow"
                               pattern="^\d+(\.\d+)?$" title="Veuillez entrer un nombre valide avec un point comme séparateur décimal"
                               name="yellow" placeholder="${cout.jaune}"  value="${cout.jaune}"> €/Unité
                    </div>
                    <div>
                        <label for="cyan"> Cyan :</label>
                        <input type="text"
                               pattern="^\d+(\.\d+)?$" title="Veuillez entrer un nombre valide avec un point comme séparateur décimal"
                               id="cyan" name="cyan" placeholder="${cout.cyan}"  value="${cout.cyan}" b> €/Unité
                    </div>
                    <div>
                        <label for="cyan"> Cout fixe par page :</label>
                        <input type="text"
                               pattern="^\d+(\.\d+)?$" title="Veuillez entrer un nombre valide avec un point comme séparateur décimal"
                               id="cfp" name="cfp" placeholder="${cout.coutFixePage}"  value="${cout.coutFixePage}" > €/Unité
                    </div>

                    <button type="submit">Sauvegarder</button>
                    <c:if test="${succes!=null}">
                        <div id="message" class="hidden">
                            <p class="succes">${succes}</p>
                        </div>

                    </c:if>
                </form>
            </div>
        </section>
    </main>
<div class="mt-4">
    <a href="analyse.jsp" class="btn btn-primary btn-lg custom-btn">Retour à l'accueil</a>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>

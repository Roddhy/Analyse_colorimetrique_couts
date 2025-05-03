#  Analyse Colorimétrique et Estimation de Coûts

Projet Java EE permettant de réaliser une analyse colorimétrique de documents pdf ou image et d'en estimer les coûts d'impression en fonction des résultats.

---

##  Structure du Projet

Application Java EE packagée au format *WAR**, prête à être déployée sur un serveur d'application compatible Jakarta EE 9+ .

---

##  Technologies et Dépendances

| Composant                     | Description                                                   |
|-------------------------------|---------------------------------------------------------------|
| **Langage**                   | Java 11                                                       |
| **Framework**                 | Jakarta EE 9.1 (Servlets, JSP, MVC, JPA, Web Services)        |
| **PDF Processing**            | [Apache PDFBox](https://pdfbox.apache.org/)                  |
| **Tests**                     | JUnit 5                                                       |
| **Persistance**               | EclipseLink JPA                                               |
| **Templating**                | JSP + JSTL                                                    |
| **Packaging**                 | WAR (Web Application Archive)                                |

---

##  Lancement du Projet

### 1. Prérequis
- Java 11
- Maven 3.x
- Serveur Jakarta EE 9+ 

### 2. Compilation

mvn clean package

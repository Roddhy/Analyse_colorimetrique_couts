package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Couvertures;
import model.Fichier;
import services.FichierPdfService;
import services.CouvertureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@WebServlet("/afficheurHistogrammePerso")

/**
 * servlet qui affiche l'histogramme personnaliser de chaque fichier
 */
public class HistogrammePers extends HttpServlet {
    @EJB
    FichierPdfService fichierPdfService;
    @EJB
    CouvertureService  couvertureService;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        Fichier fichier = fichierPdfService.findFieldById(id);

        Map<String, Map<String, Double>> couvertureParFichier = new HashMap<>();
        couvertureParFichier.put(fichier.getNom(), Map.of("Noir", fichier.getCouvertureNoir(), "Cyan", fichier.getCouvertureCyan(),
                "Magenta", fichier.getCouvertureMagenta(), "Jaune", fichier.getCouvertureJaune()));
        List<Map<String, Object>> pages = new ArrayList<>();
        List<Couvertures>  couvertureList = couvertureService.findPageByFieldId(fichier.getId());
        for (Couvertures couverture : couvertureList) {

            Map<String, Object> pageData = new HashMap<>();

            pageData.put("nomPage", "Page " + couverture.getNumeroPage());
            // Couvertures par couleur
            Map<String, Double> couleurs = new HashMap<>();

            couleurs.put("Noir", couverture.getNoir());
            couleurs.put("Cyan", couverture.getCyan());
            couleurs.put("Magenta", couverture.getMagenta());
            couleurs.put("Jaune", couverture.getJaune());

            Map<String, Double> pourcentages = new HashMap<>();

            pourcentages.put("Noir", couverture.getNoir());
            pourcentages.put("Cyan",couverture.getCyan());
            pourcentages.put("Magenta", couverture.getMagenta());
            pourcentages.put("Jaune", couverture.getJaune());

            pageData.put("couleurs", couleurs);       // Ajout des valeurs absolues
            pageData.put("pourcentages", pourcentages); // Ajout des pourcentages
            pages.add(pageData); // Ajouter les données de la page à la liste
        }

        // Attacher les données à la requête pour la JSP
        request.setAttribute("pages", pages);
        request.setAttribute("couvertureParFichier", couvertureParFichier);
        request.setAttribute("fichier", fichier.getNom());
        // Rediriger vers la JSP
        request.getRequestDispatcher("affichagePer.jsp").forward(request, response);

    }

}

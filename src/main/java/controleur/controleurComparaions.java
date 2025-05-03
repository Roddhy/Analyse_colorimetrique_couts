package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Fichier;
import services.FichierPdfService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/controleurComparaison")
/**
 * servlet qui s'occupe du controle des analyse selectionneés avant la comparaison
 * et aussi d'afficher l'ensemble des analyses deja faites
 */
public class controleurComparaions extends HttpServlet {
    @EJB
    FichierPdfService fichierPdfService;
    public void doPost(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {

        String[] check = request.getParameterValues("check");
        List<Fichier> fichierpdfListR= fichierPdfService.getAllField();
        List<Fichier> listDesNomDeFichierSelectionner=new ArrayList<>();
//        List<Resultat> listDesResultatDanalyseDesFS=new ArrayList<>();
        if (check == null || check.length <2) {
            String message = "selectionner 2 fichiers au minimum";
            request.setAttribute("error", message);
            request.setAttribute("fichierPdfList",fichierpdfListR);
            request.getRequestDispatcher("historique.jsp").forward(request,response);
            return;
        }
        for (String item : check) {
            listDesNomDeFichierSelectionner.add(fichierPdfService.findFieldById(Integer.parseInt(item)));
        }
        HttpSession session=request.getSession();
        session.setAttribute("listDesNomDeFichierSelectionner", listDesNomDeFichierSelectionner);
        response.sendRedirect("afficheurTableau");
    }

}

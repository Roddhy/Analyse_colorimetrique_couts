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
import java.util.List;

@WebServlet("/afficheurTableau")

public class AfficheurTableau  extends HttpServlet {
    @EJB
    FichierPdfService fichierPdfService;

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        HttpSession session= request.getSession();
        List<Fichier> fichierpdfListR= fichierPdfService.getAllField();
        List<Fichier> listDesNomDeFichierSelectionner= (List<Fichier>) session.getAttribute("listFichierPdfSelectionner");

        request.setAttribute("fichierPdfList",fichierpdfListR);
        request.setAttribute("listDesNomDeFichierSelectionner",listDesNomDeFichierSelectionner);

        request.getRequestDispatcher("affichageTableau.jsp").forward(request,response);
    }
}

package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Fichier;
import services.FichierPdfService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet qui affiche les precedentes analyse
 */
@WebServlet("/controleurFichier")
public class FichierControleur extends HttpServlet {
@EJB
    FichierPdfService fichierPdfService;


    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        List<Fichier> fichierpdfList= fichierPdfService.getAllField();
        if(fichierpdfList==null){
            String message="Aucun fichier a afficher";
            request.setAttribute("message",message);
            response.sendRedirect("historique.jsp");
            return;
        }
        request.setAttribute("fichierPdfList",fichierpdfList);
        request.getRequestDispatcher("historique.jsp").forward(request,response);
    }

}

package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cout;
import services.CoutService;

import java.io.IOException;

@WebServlet("/cout")
/**
 *
 */
public class ControleurCout extends HttpServlet {
    @EJB
    CoutService coutService; // tu trouvera ici les services que jai du creer

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cout cout= coutService.getAll();
        if(cout==null){
            request.setAttribute("message","la table est vide");
            response.sendRedirect("parametre.jsp");
            return;
        }
        request.setAttribute("cout",cout);
        request.getRequestDispatcher("parametre.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Thread.sleep(50); // Simule un délai pour le traitement de la requête
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Double noir= Double.valueOf(request.getParameter("black"));
        Double jaune= Double.valueOf(request.getParameter("yellow"));
        Double magenta= Double.valueOf(request.getParameter("magenta"));
        Double cyan= Double.valueOf(request.getParameter("cyan"));
        double cfp= Double.parseDouble(request.getParameter("cfp"));

        coutService.update(noir,jaune,magenta,cyan,cfp);

        Cout cout= coutService.getAll();
        request.setAttribute("cout",cout);
        request.setAttribute("succes","Modifications effectueés avec succès");
        request.getRequestDispatcher("parametre.jsp").forward(request,response);

    }

}

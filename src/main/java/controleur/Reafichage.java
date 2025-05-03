package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Couvertures;
import model.Fichier;
import model.Page;
import model.TotalPixel;
import services.CouvertureService;
import services.FichierPdfService;
import services.PageService;
import services.TotalPixelService;

import java.io.IOException;
import java.util.List;

@WebServlet("/rea")
public class Reafichage extends HttpServlet {
    @EJB
    FichierPdfService fichierPdfService;
    @EJB
    PageService pageService;
    @EJB
    CouvertureService couvertureService;
    @EJB
    TotalPixelService totalPixelService;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Fichier fichier = fichierPdfService.findFieldById(id);
        List<Page> page=pageService.findPageByFieldId(id);
        List<Couvertures> couvertures=couvertureService.findPageByFieldId(id);
        List<TotalPixel> totalPixel=totalPixelService.findPageByFieldId(id);
        request.setAttribute("fichier", fichier);
        request.setAttribute("page", page);
        request.setAttribute("couvertures", couvertures);
        request.setAttribute("totalPixel", totalPixel);
        request.getRequestDispatcher("rea.jsp").forward(request, response);
    }
}

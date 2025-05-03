package services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Couvertures;
import model.Fichier;
import model.Page;
import model.TotalPixel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Stateless
public class Reafficher{
    @EJB
    FichierPdfService fichierPdfService;
    @EJB
    PageService pageService;
    @EJB
    CouvertureService couvertureService;
    @EJB
    TotalPixelService totalPixelService;
    public void reafficher(int id, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Fichier fichier = fichierPdfService.findFieldById(id);
       List<Page> page=pageService.findPageByFieldId(id);
       List<Couvertures> couvertures=couvertureService.findPageByFieldId(id);
       List<TotalPixel> totalPixel=totalPixelService.findPageByFieldId(id);
       req.setAttribute("fichier", fichier);
       req.setAttribute("page", page);
       req.setAttribute("couvertures", couvertures);
       req.setAttribute("totalPixel", totalPixel);
    req.getRequestDispatcher("rea.jsp").forward(req, resp);
    }
}

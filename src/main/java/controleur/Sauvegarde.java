package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.FichierPdfService;
import services.SauvegardeService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

/**
 * Servlet qui s'occupe de la sauvegarde des donneés dans la bd apres
 * que l'utilisateur ait cliquer sur sauva=egarder l'analyse
 */
@WebServlet("/sauvegarde")
public class Sauvegarde extends HttpServlet {
    @EJB
    FichierPdfService fichierPdfService;
    @EJB
    SauvegardeService sauvegardeService;


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nomFichier = (String) session.getAttribute("nom");
        String dateAnalyse = (String) session.getAttribute("date");
        String heureAnalyse = (String) session.getAttribute("heure");
        Integer nombrePages = (Integer) session.getAttribute("nombrePages");
        double estimationCouleur = (Double) session.getAttribute("estimation1");
        double estimationNb = (Double) session.getAttribute("estimation2");

        byte[] pdfData = (byte[]) session.getAttribute("pdfData");
        File tempFile = File.createTempFile("uploadedPdf", ".pdf");
        if (pdfData != null) {
            // Créer un fichier temporaire
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(pdfData); // Écrit les données dans le fichier
            }
        }
        // Appel de la méthode qui prend un File
        String chemin = fichierPdfService.chemin(tempFile);
        // Récupérer les listes de couverture depuis la session
        List<Double> ListcouvertureNoir = (List<Double>) session.getAttribute("ListcouvertureNoir");
        List<Double> ListcouvertureCyan = (List<Double>) session.getAttribute("ListcouvertureCyan");
        List<Double> ListcouvertureCombinaison = (List<Double>) session.getAttribute("ListcouvertureCombinaison");
        List<Double> ListcouvertureJaune = (List<Double>) session.getAttribute("ListcouvertureJaune");
        List<Double> ListcouvertureMagenta = (List<Double>) session.getAttribute("ListcouvertureMagenta");
        List<Double> blanc = (List<Double>) session.getAttribute("ListcouvertureBlanc");

        // Récupérer les listes de pixels depuis la session
        List<Integer> pixelsNoir = (List<Integer>) session.getAttribute("Noir");
        List<Integer> pixelsCyan = (List<Integer>) session.getAttribute("Cyan");
        List<Integer> pixelsCombinations = (List<Integer>) session.getAttribute("Combinations");
        List<Integer> pixelsJaune = (List<Integer>) session.getAttribute("Jaune");
        List<Integer> pixelsMagenta = (List<Integer>) session.getAttribute("Magenta");
        List<Integer> blancPixels = (List<Integer>) session.getAttribute("Blanc");



        Double totalCouvertureNoir = (Double) session.getAttribute("TotalCouverturesNoir");
        Double totalCouvertureCyan = (Double) session.getAttribute("TotalCouverturesCyan");
        Double totalCouvertureCombinaison = (Double) session.getAttribute("TotalCouverturesCombinations");
        Double totalCouvertureJaune = (Double) session.getAttribute("TotalCouverturesJaune");
        Double totalCouvertureMagenta = (Double) session.getAttribute("TotalCouverturesMagenta");
        Double totalBlancC= (Double) session.getAttribute("TotalCouverturesblanc");
        int id = sauvegardeService.sauvegardeFichierPdf(nomFichier,chemin,dateAnalyse,heureAnalyse,nombrePages,totalCouvertureNoir,totalCouvertureJaune
                ,totalCouvertureMagenta,totalCouvertureCyan,estimationCouleur,totalCouvertureCombinaison,totalBlancC,estimationNb);


        Double totalPixelsNoir = (Double) session.getAttribute("TotalpixelsNoir");
        Double totalPixelsCyan = (Double) session.getAttribute("TotalpixelsCyan");
        Double totalPixelsCombinations = (Double) session.getAttribute("TotalpixelsCombinations");
        Double totalPixelsJaune = (Double) session.getAttribute("TotalpixelsJaune");
        Double totalPixelsMagenta = (Double) session.getAttribute("TotalpixelsMagenta");
        Double totalBlancPixels = (Double) session.getAttribute("Totalpixelsblanc");


        boolean listPixelp= sauvegardeService.sauvegardeListPixel(pixelsNoir,pixelsCyan,pixelsCombinations,pixelsJaune,pixelsMagenta,nombrePages,id,blancPixels);
       boolean  totalPixel= sauvegardeService.sauvegardeTotalPixel(totalPixelsNoir,totalPixelsMagenta,totalPixelsCyan,totalPixelsJaune,totalPixelsCombinations,id,totalBlancPixels);
      boolean ListCouverture= sauvegardeService.sauvegardeListCouverture(ListcouvertureNoir,ListcouvertureCyan,ListcouvertureCombinaison,ListcouvertureJaune,ListcouvertureMagenta,blanc,nombrePages,id);

      if (ListCouverture && totalPixel && listPixelp) {
          req.setAttribute("messageInfo","Analyse sauvegarder avec succès");
          req.getRequestDispatcher("confirmation.jsp").forward(req, res);
          return;
      }
        req.setAttribute("messageInfo","Echec lors de la sauvegarde de l'nalyse");
        req.getRequestDispatcher("confirmation.jsp").forward(req, res);


        }
    }


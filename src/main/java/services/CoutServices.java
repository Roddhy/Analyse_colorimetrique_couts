package services;



import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

import model.Cout;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import jakarta.ejb.Stateless;
import model.Cout;


import java.util.List;

@Stateless

public class CoutServices {
    @EJB
    AnalyseService analyseService;


   public double calculerCoutImpression(
           double couvertureNoir, double couvertureCyan, double couvertureMagenta, double couvertureJaune,
          double coutNoir, double coutCyan, double coutMagenta, double coutJaune,
           double coutFixeParPage, int nombrePages) {

       // Calcul du coût d'impression par couleur
       double coutEncreNoir = couvertureNoir * coutNoir * nombrePages;
        double coutEncreCyan = couvertureCyan * coutCyan * nombrePages;
      double coutEncreMagenta = couvertureMagenta * coutMagenta * nombrePages;
        double coutEncreJaune = couvertureJaune * coutJaune * nombrePages;

        // Calcul du coût fixe total
        double coutFixeTotal = coutFixeParPage * nombrePages;

        // Somme des coûts pour obtenir le coût total
       double coutTotal = coutEncreNoir + coutEncreCyan + coutEncreMagenta + coutEncreJaune + coutFixeTotal;

        // Retour du coût total
       return Math.round(coutTotal * 100.0) / 100.0;

   }


public double estimationCoutImpressionCouleur(Cout cout,Part fichierPdf) throws IOException {

    double coutTotal = 0.0;

    try (InputStream inputStream = fichierPdf.getInputStream();
         PDDocument document = PDDocument.load(inputStream)) {
        int nombrePages = document.getNumberOfPages();
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int i = 0; i < nombrePages; i++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150, ImageType.RGB);
            long totalPixelsPage = (long) image.getWidth() * image.getHeight();
            int[] pixelCounts = analyseService.comptagePixelsParCouleur(image);
            double[] couvertureCMJN = analyseService.calculerCouvertureCMJN(image);
            // Coût pour chaque page
            double coutPage = 0.0;
            coutPage += couvertureCMJN[3] * cout.getNoir(); // Noir
            coutPage += couvertureCMJN[0] * cout.getCyan(); // Cyan
            coutPage += couvertureCMJN[1] * cout.getMagenta(); // Magenta
            coutPage += couvertureCMJN[2] * cout.getJaune(); // Jaune

            coutPage *= totalPixelsPage / 100.0; // Ajuster par rapport au pourcentage
            coutPage += cout.getCoutFixePage(); // Ajouter le coût fixe pour cette page

            coutTotal += coutPage;
        }
    }

    // Arrondi au centième pour le coût total
    return Math.round(coutTotal * 100.0) / 100.0;
}

    public double estimationCoutImpressionNoirEtBlanc(Cout cout, Part fichierPdf) throws IOException {

        double coutTotal = 0.0;

        try (InputStream inputStream = fichierPdf.getInputStream();
             PDDocument document = PDDocument.load(inputStream)) {

            int nombrePages = document.getNumberOfPages();

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int i = 0; i < nombrePages; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150, ImageType.GRAY);  // Utilisation de l'image en niveaux de gris
                long totalPixelsPage = (long) image.getWidth() * image.getHeight();

                // Comptage des pixels noirs (dans le cas d'une image en noir et blanc)
                int pixelCountNoir = 0;
                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        int pixel = image.getRGB(x, y);
                        int r = (pixel >> 16) & 0xff;
                        int g = (pixel >> 8) & 0xff;
                        int b = pixel & 0xff;

                        // Vérifier si le pixel est proche de noir (en niveaux de gris)
                        if (r < 128 && g < 128 && b < 128) {  // seuil ajustable pour considérer comme noir
                            pixelCountNoir++;
                        }
                    }
                }

                // Coût pour chaque page
                double coutPage = 0.0;
                double couvertureNoir = (double) pixelCountNoir / totalPixelsPage;  // Pourcentage de pixels noirs

                coutPage += couvertureNoir * cout.getNoir();  // Coût basé sur la couverture en noir

                coutPage *= totalPixelsPage / 100.0; // Ajuster par rapport au nombre total de pixels
                coutPage += cout.getCoutFixePage(); // Ajouter le coût fixe pour cette page

                coutTotal += coutPage;
            }
        }

        // Arrondi au centième pour le coût total
        return Math.round(coutTotal * 100.0) / 100.0;
    }



}

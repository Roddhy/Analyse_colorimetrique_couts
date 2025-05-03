package controleur;

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
import services.*;

@WebServlet("/upload")
@MultipartConfig
/**
 * Servlet dans laquelle l'analyse se fera
 * #CLEMENT voici a quoi devrait ressembler ta servlet
 * jai concatener ton travail et celui de #Yassin ici
 * tu retouvera les services dans
 * @AnalyseService dans le package Services
 */
public class ControleurAnalyse extends HttpServlet {
    /**
     * Service utiliser durant l'analyse
     * @AnalyseService EJB fournissant les services necessaire  liee a l'analyse du fichier
     * @FichierPadfService EJB fournissant les services liee a lentité Fichier pour la Base de donnee
     * @SessionService EJB pour stocker des donnees dans la session
     * @CoutService EJB fournissant les services liee a lentité Cout pour la Base de donnee
     * calucle du cout etc..
     */
    @EJB
    AnalyseService analyseService;
    @EJB
    FichierPdfService fichierPdfService;
    @EJB
    SessionService sessionService;
    @EJB
    CoutServices coutServices;
    @EJB
    CoutService coutService;

    @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String date=fichierPdfService.dateAnalayse();
        String heure=fichierPdfService.heureAnalayse();

        Part fichierPdf = request.getPart("pdfFile");

        sessionService.setFichierInsession(fichierPdf,request);

        session.setAttribute("date",date);
        session.setAttribute("heure",heure);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServletException("Interruption pendant le traitement de la requête", e);
        }

        String fileName=fichierPdf.getSubmittedFileName();
        //liste de pixel
        List<Integer> pixelsNoir = new ArrayList<>();
        List<Integer> pixelsCyan = new ArrayList<>();
        List<Integer> pixelsMagenta = new ArrayList<>();
        List<Integer> pixelsJaune = new ArrayList<>();
        List<Integer> pixelsCombinations = new ArrayList<>();
        List<Integer> pixelsBlanc = new ArrayList<>();
        //liste de couverture
        List<Double> couvertureBlanc = new ArrayList<>();
        List<Double> couvertureCyan = new ArrayList<>();
        List<Double> couvertureMagenta = new ArrayList<>();
        List<Double> couvertureJaune = new ArrayList<>();
        List<Double> couvertureNoir = new ArrayList<>();
        List<Double> couvertureCombinations = new ArrayList<>();

        try (InputStream inputStream = fichierPdf.getInputStream();
             PDDocument document = PDDocument.load(inputStream)) {
            int nombrePages = document.getNumberOfPages();
            session.setAttribute("nombrePages", nombrePages);

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            // total pixel
            long totalPixelsNoir = 0, totalPixelsCyan = 0, totalPixelsMagenta = 0;
            long totalPixelsJaune = 0, totalPixelsCombinations = 0, totalPixelsBlanc = 0;
            long totalPixels = 0;

            for (int i = 0; i < nombrePages; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150, ImageType.RGB);
                totalPixels += (long) image.getWidth() * image.getHeight();

                int[] pixelCounts = analyseService.comptagePixelsParCouleur(image);
                double[] couvertureCMJN = analyseService.calculerCouvertureCMJN(image);

                pixelsNoir.add(pixelCounts[0]);
                pixelsCyan.add(pixelCounts[1]);
                pixelsMagenta.add(pixelCounts[2]);
                pixelsJaune.add(pixelCounts[3]);
                pixelsCombinations.add(pixelCounts[4]);
                pixelsBlanc.add(pixelCounts[5]);

                couvertureBlanc.add(couvertureCMJN[4]);
                couvertureCyan.add(couvertureCMJN[0]);
                couvertureMagenta.add(couvertureCMJN[1]);
                couvertureJaune.add(couvertureCMJN[2]);
                couvertureNoir.add(couvertureCMJN[3]);

                double totalPixelsPage = image.getWidth() * image.getHeight();
                double couvertureCombination = Math.round((pixelCounts[4] / totalPixelsPage) * 10000) / 100.0;
                couvertureCombinations.add(couvertureCombination);

                totalPixelsNoir += pixelCounts[0];
                totalPixelsCyan += pixelCounts[1];
                totalPixelsMagenta += pixelCounts[2];
                totalPixelsJaune += pixelCounts[3];
                totalPixelsCombinations += pixelCounts[4];
                totalPixelsBlanc += pixelCounts[5];
            }

            //convertire au 100eme pres
            Double totalCouvertureNoir = Math.round((double) totalPixelsNoir / totalPixels * 10000) / 100.0;
            Double totalCouvertureCyan = Math.round((double) totalPixelsCyan / totalPixels * 10000) / 100.0;
            Double totalCouvertureMagenta = Math.round((double) totalPixelsMagenta / totalPixels * 10000) / 100.0;
            Double totalCouvertureJaune = Math.round((double) totalPixelsJaune / totalPixels * 10000) / 100.0;
            Double totalCouvertureCombinations = Math.round((double) totalPixelsCombinations / totalPixels * 10000) / 100.0;
            double totalCouvertureBlanc = Math.round((double) totalPixelsBlanc / totalPixels * 10000) / 100.0;



            request.setAttribute("PixelsNoir", pixelsNoir);
            request.setAttribute("PixelsCyan", pixelsCyan);
            request.setAttribute("PixelsMagenta", pixelsMagenta);
            request.setAttribute("PixelsJaune", pixelsJaune);
            request.setAttribute("PixelsCombinations", pixelsCombinations);
            request.setAttribute("PixelsBlanc", pixelsBlanc);

            sessionService.setListPixelInsession(pixelsNoir, pixelsCyan, pixelsCombinations,
                    pixelsJaune, pixelsMagenta,pixelsBlanc, request);


            request.setAttribute("p", nombrePages);
            request.setAttribute("p2", fileName);
            sessionService.setInfoFichierInsession(fileName, nombrePages,request);

            request.setAttribute("couvertureBlanc", couvertureBlanc);
            request.setAttribute("couvertureCyan", couvertureCyan);
            request.setAttribute("couvertureMagenta", couvertureMagenta);
            request.setAttribute("couvertureJaune", couvertureJaune);
            request.setAttribute("couvertureNoir", couvertureNoir);
            request.setAttribute("couvertureCombinations", couvertureCombinations);

            sessionService.setListCouvertureInSession(couvertureNoir, couvertureCyan, couvertureCombinations,
                    couvertureJaune, couvertureMagenta, couvertureBlanc,request);


            request.setAttribute("totalPixels", totalPixels);
            request.setAttribute("totalPixelsNoir", totalPixelsNoir);
            request.setAttribute("totalPixelsCyan", totalPixelsCyan);
            request.setAttribute("totalPixelsMagenta", totalPixelsMagenta);
            request.setAttribute("totalPixelsJaune", totalPixelsJaune);
            request.setAttribute("totalPixelsCombinations", totalPixelsCombinations);
            request.setAttribute("totalPixelsBlanc", totalPixelsBlanc);
            sessionService.setTotalPixelInsession((double) totalPixelsNoir, (double) totalPixelsMagenta,
                    (double) totalPixelsCyan, (double) totalPixelsJaune, (double) totalPixelsCombinations, (double) totalPixelsBlanc, request);

            request.setAttribute("totalCouvertureNoir", totalCouvertureNoir);
            request.setAttribute("totalCouvertureCyan", totalCouvertureCyan);
            request.setAttribute("totalCouvertureMagenta", totalCouvertureMagenta);
            request.setAttribute("totalCouvertureJaune", totalCouvertureJaune);
            request.setAttribute("totalCouvertureCombinations", totalCouvertureCombinations);
            request.setAttribute("totalCouvertureBlanc", totalCouvertureBlanc);

            sessionService.setTotalCouvertureInsession(totalCouvertureNoir, totalCouvertureMagenta, totalCouvertureCyan,
                    totalCouvertureJaune, totalCouvertureCombinations, totalCouvertureBlanc, request);

            Cout cout = coutService.getAll();
            double noiretblanc = coutService.calculerCoutImpressionNoirBlanc(totalCouvertureNoir,cout.getNoir(),cout.getCoutFixePage(),nombrePages);
            double coutEstimerEncouleur=coutServices.estimationCoutImpressionCouleur(cout,fichierPdf);
            sessionService.setEstimationInsession(noiretblanc,coutEstimerEncouleur,request);

            /**
             *  double coutEstimer = coutServices.calculerCoutImpression(totalCouvertureNoir,totalCouvertureCyan,totalCouvertureMagenta,totalCouvertureJaune,
             *                     cout.getNoir(),cout.getCyan(),cout.getMagenta(),cout.getJaune(),cout.getCoutFixePage(),nombrePages);
             *             double coutestimerNoirSurBlan=coutService.calculerCoutImpressionNoirBlanc(totalCouvertureNoir,cout.getNoir(),cout.getCoutFixePage(),nombrePages);
             *             double coutNoirEtBlanc=coutServices.estimationCoutImpressionNoirEtBlanc(cout,fichierPdf);
             *              request.setAttribute("coutestimer2", coutEstimer); cetait juste un test
             */
            request.setAttribute("coutEstimer", coutEstimerEncouleur);
            request.setAttribute("coutestimer3", noiretblanc);
        }
        request.getRequestDispatcher("resultatAnalyse.jsp").forward(request, response);
    }

}




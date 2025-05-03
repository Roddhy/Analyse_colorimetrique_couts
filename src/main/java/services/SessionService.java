package services;

import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Stateless
public class SessionService {



    public  void setListCouvertureInSession(List<Double> couvertureNoir,
                                           List<Double> couvertureCyan, List<Double> couvertureCombinaison,
                                           List<Double> couvertureJaune, List<Double> couvertureMagenta, List<Double> couvertureBlanc,HttpServletRequest request){

        HttpSession session = request.getSession();
            session.setAttribute("ListcouvertureNoir", couvertureNoir);
            session.setAttribute("ListcouvertureCyan", couvertureCyan);
            session.setAttribute("ListcouvertureCombinaison", couvertureCombinaison);
            session.setAttribute("ListcouvertureJaune", couvertureJaune);
            session.setAttribute("ListcouvertureMagenta", couvertureMagenta);
            session.setAttribute("ListcouvertureBlanc", couvertureBlanc);
    }

    public void setListPixelInsession(List<Integer> pixelsNoir,
                                      List<Integer> pixelsCyan, List<Integer> pixelsCombinations,
                                      List<Integer> pixelsJaune, List<Integer> pixelsMagenta, List<Integer> pixelsBlanc, HttpServletRequest request){
        HttpSession session = request.getSession();
            session.setAttribute("Noir", pixelsNoir);
            session.setAttribute("Cyan", pixelsCyan);
            session.setAttribute("Combinations", pixelsCombinations);
            session.setAttribute("Jaune", pixelsJaune);
            session.setAttribute("Magenta", pixelsMagenta);
            session.setAttribute("Blanc", pixelsBlanc);

    }
    public void setTotalPixelInsession(Double pixelNoir,
                                          Double pixelMagenta,Double pixelCyan,Double pixelJaune,Double pixelCombinaison,Double blanc,HttpServletRequest request){
        HttpSession session = request.getSession();
            session.setAttribute("TotalpixelsNoir", pixelNoir);
            session.setAttribute("TotalpixelsCyan", pixelCyan);
            session.setAttribute("TotalpixelsCombinations", pixelCombinaison);
            session.setAttribute("TotalpixelsJaune", pixelJaune);
            session.setAttribute("TotalpixelsMagenta", pixelMagenta);
            session.setAttribute("Totalpixelsblanc", blanc);


    }
    public void  setTotalCouvertureInsession(Double noir,
                                          Double Magenta,Double Cyan,Double Jaune,Double Combinaison,double blanc,HttpServletRequest request){
        HttpSession session = request.getSession();
            session.setAttribute("TotalCouverturesNoir",noir );
            session.setAttribute("TotalCouverturesCyan", Cyan);
            session.setAttribute("TotalCouverturesCombinations", Combinaison);
            session.setAttribute("TotalCouverturesJaune", Jaune);
            session.setAttribute("TotalCouverturesMagenta", Magenta);
            session.setAttribute("TotalCouverturesblanc", blanc);
    }


    public void setInfoFichierInsession(String nom,int nbrPage,HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("nom", nom);
        session.setAttribute("nbrPage", nbrPage);

    }
    public void setFichierInsession(Part fichier,HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();
        // Lecture du fichier PDF (via un formulaire ou une méthode spécifique)
        InputStream pdfInputStream = fichier.getInputStream();
        // Convertir en tableau d'octets
        byte[] pdfData = pdfInputStream.readAllBytes();
        // Stocker le PDF dans la session
        session.setAttribute("pdfData", pdfData);

    }
    public void setEstimationInsession(double estimation1,double estimation2,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("estimation1", estimation1);
        session.setAttribute("estimation2", estimation2);
    }

}

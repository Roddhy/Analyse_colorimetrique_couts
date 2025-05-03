package services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Fichier;
import model.Page;

import java.util.List;

@Stateful
@LocalBean

public class Test {
     @PersistenceContext
     EntityManager em;
    public int sauvegardeFichierPdf(String nompdf,String cheminpdf,
                                    String dateAnalyse,String heureAnalyse,int nombredepage,double noir,double jaune
            ,double magenta,double cyan,double cout,double combinaison){

        try{
            Fichier f=new Fichier();
            f.setCout(cout);
            f.setChemin(cheminpdf);
            f.setCout(cout);
            f.setCouvertureCyan(cyan);
            f.setCouvertureJaune(jaune);
            f.setCouvertureNoir(noir);
            f.setCouvertureMagenta(magenta);
            f.setDateAnalyse(dateAnalyse);
            f.setHeureAnalyse(heureAnalyse);
            f.setNom(nompdf);
            f.setNbrPage(nombredepage);
            f.setCouvertureCombinaison(combinaison);
            em.persist(f);
            em.flush();

            return  f.getId();
        }
        catch(Exception e){
            return -1;
        }
    }
    public Boolean sauvegardeListPixel(List<Integer> pixelsNoir,
                                       List<Integer> pixelsCyan, List<Integer> pixelsCombinations,
                                       List<Integer> pixelsJaune, List<Integer> pixelsMagenta, int nbrPage, int idFichier){
        try {

            for(int i=0;i<nbrPage;i++){
                Page p=new Page();
                p.setNumeroPage(i+1);
                p.setIdPdf(idFichier);
                p.setPixelNoir(Float.valueOf(pixelsNoir.get(i)));
                p.setPixelCombinaison(Float.valueOf(pixelsCombinations.get(i)));
                p.setPixelJaune(Float.valueOf(pixelsJaune.get(i)));
                p.setPixelMagenta(Float.valueOf(pixelsMagenta.get(i)));
                p.setPixelCyan(Float.valueOf(pixelsCyan.get(i)));
                em.persist(p);
                em.flush();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

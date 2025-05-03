package services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Couvertures;
import model.Fichier;

import model.Page;
import model.TotalPixel;

import java.util.List;

@Stateless
@LocalBean

public class SauvegardeService {
    @PersistenceContext
    EntityManager em;

    public int sauvegardeFichierPdf(String nompdf,String cheminpdf,
                                        String dateAnalyse,String heureAnalyse,int nombredepage,double noir,double jaune
    ,double magenta,double cyan,double cout,double combinaison,double blanc,double coutNb){

        try{
            Fichier f=new Fichier();
            f.setCout(cout);
            f.setChemin(cheminpdf);
            f.setCout(cout);
            f.setBlanc(blanc);
            f.setCoutNb(coutNb);
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
                                       List<Integer> pixelsJaune, List<Integer> pixelsMagenta, int nbrPage, int idFichier,List<Integer> blanc){
        try {

            for(int i=0;i<nbrPage;i++){
                Page p=new Page();
                p.setNumeroPage(i+1);
                p.setBlanc(Float.valueOf(blanc.get(i)));
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

    public Boolean sauvegardeListCouverture(List<Double> couvertureNoir,
                                            List<Double> couvertureCyan, List<Double> couvertureCombinaison,
                                            List<Double> couvertureJaune, List<Double> couvertureMagenta,List<Double> blanc, int nbrPage, int idFichier){
        try {

            for(int i=0;i<nbrPage;i++){
                Couvertures c=new Couvertures();
               c.setCombinaison(couvertureCombinaison.get(i));
                c.setCyan(couvertureCyan.get(i));
                c.setJaune(couvertureJaune.get(i));
                c.setMagenta(couvertureMagenta.get(i));
                c.setNoir(couvertureNoir.get(i));
                c.setIdPdf(idFichier);
                c.setNumeroPage(i+1);
                c.setNumeroPage(i+1);
                c.setBlanc(blanc.get(i));
                em.persist(c);
                em.flush();
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public boolean sauvegardeTotalPixel(Double pixelNoir,
                                        Double pixelMagenta,Double pixelCyan,Double pixelJaune,Double pixelCombinaison,int id,double blanc ){
        try{
            TotalPixel totalPixel=new TotalPixel();
            totalPixel.setIdPdf(id);
            totalPixel.setCombinaison(pixelCombinaison);
            totalPixel.setCyan(pixelCyan);
            totalPixel.setJaune(pixelJaune);
            totalPixel.setMagenta(pixelMagenta);
            totalPixel.setNoir(pixelNoir);
            totalPixel.setBlanc(blanc);

            em.persist(totalPixel);
            em.flush();
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}

package services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Cout;
import model.Fichier;
import model.User;

import java.util.List;

@LocalBean
@Stateless
public class CoutService {
    @PersistenceContext
    EntityManager em;
    public  void update(Double noir,Double jaune,Double magenta,Double cyan,double coutfixepage){
        em.createNamedQuery("Cout.update", Cout.class)
                .setParameter("noir",noir).setParameter("jaune",jaune).setParameter("coutfixepage",coutfixepage)
                .setParameter("magenta",magenta).setParameter("cyan",cyan).executeUpdate();
    }
    public Cout getAll(){
        Cout cout =em.createNamedQuery("Cout.getAll", Cout.class).getSingleResult();
        if(cout==null){
            return null;
        }
        return cout;
    }

    public double calculerCoutImpressionCouleur(Fichier fichier,Cout cout) {
        // Calcul du coût d'impression par couleur
        double coutNoir = cout.getNoir() * fichier.getCouvertureNoir()* fichier.getNbrPage();
        double coutCyan= cout.getCyan()* fichier.getCouvertureCyan()* fichier.getNbrPage();
        double coutMagenta=cout.getMagenta()*fichier.getCouvertureMagenta()* fichier.getNbrPage();
        double coutJaune=cout.getJaune()*fichier.getCouvertureJaune()* fichier.getNbrPage();

        // Calcul du coût fixe total
        double coutFixeTotal = cout.getCoutFixePage()* fichier.getNbrPage();

        // Somme des coûts pour obtenir le coût total
        double coutTotal= coutCyan+coutMagenta+coutJaune+coutFixeTotal+coutNoir;
        // Retour du coût total
        return Math.round(coutTotal * 100.0) / 100.0; // Arrondi au centième
    }

    public double calculerCoutImpressionNoirBlanc(
            double couvertureNoir, double coutNoir, double coutFixeParPage, int nombrePages) {

        // Calcul du coût d'encre noire
        double coutEncreNoir = couvertureNoir * coutNoir * nombrePages;
        // Calcul du coût fixe total
        double coutFixeTotal = coutFixeParPage * nombrePages;

        // Coût total
        double coutTotal = coutEncreNoir + coutFixeTotal;

        // Arrondi au centième
        return Math.round(coutTotal * 100.0) / 100.0;
    }
}

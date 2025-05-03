package services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;

import java.awt.image.BufferedImage;

@Stateless
@LocalBean
public class AnalyseService {

    @PersistenceContext
    public double[] calculerCouvertureCMJN(BufferedImage image) {
        int largeur = image.getWidth();
        int hauteur = image.getHeight();
        long totalPixels = (long) largeur * hauteur;

        double totalCyan = 0, totalMagenta = 0, totalJaune = 0, totalNoir = 0;
        long totalBlanc = 0;

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                int rgb = image.getRGB(x, y);
                int rouge = (rgb >> 16) & 0xFF;
                int vert = (rgb >> 8) & 0xFF;
                int bleu = rgb & 0xFF;

                if (rouge == 255 && vert == 255 && bleu == 255) {
                    totalBlanc++;
                    continue;
                }

                double[] cmjn = convertirRVBEnCMJN(rouge, vert, bleu);
                totalCyan += cmjn[0];
                totalMagenta += cmjn[1];
                totalJaune += cmjn[2];
                totalNoir += cmjn[3];
            }
        }

        double pourcentageCyan = Math.round((totalCyan / totalPixels) * 10000) / 100.0;
        double pourcentageMagenta = Math.round((totalMagenta / totalPixels) * 10000) / 100.0;
        double pourcentageJaune = Math.round((totalJaune / totalPixels) * 10000) / 100.0;
        double pourcentageNoir = Math.round((totalNoir / totalPixels) * 10000) / 100.0;
        double pourcentageBlanc = Math.round(((double) totalBlanc / totalPixels) * 10000) / 100.0;

        return new double[]{pourcentageCyan, pourcentageMagenta, pourcentageJaune, pourcentageNoir, pourcentageBlanc};
    }

    public double[] convertirRVBEnCMJN(int rouge, int vert, int bleu) {
        double r = rouge / 255.0;
        double g = vert / 255.0;
        double b = bleu / 255.0;

        double k = 1 - Math.max(r, Math.max(g, b));
        double c = k < 1 ? (1 - r - k) / (1 - k) : 0;
        double m = k < 1 ? (1 - g - k) / (1 - k) : 0;
        double y = k < 1 ? (1 - b - k) / (1 - k) : 0;

        return new double[]{c, m, y, k};
    }

    public int[] comptagePixelsParCouleur(BufferedImage image) {
        int largeur = image.getWidth();
        int hauteur = image.getHeight();

        int totalNoir = 0, totalCyan = 0, totalMagenta = 0, totalJaune = 0;
        int totalCombinations = 0, totalBlanc = 0;

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                int rgb = image.getRGB(x, y);
                int rouge = (rgb >> 16) & 0xFF;
                int vert = (rgb >> 8) & 0xFF;
                int bleu = rgb & 0xFF;

                if (rouge == 255 && vert == 255 && bleu == 255) {
                    totalBlanc++;
                    continue;
                }

                double[] cmjn = convertirRVBEnCMJN(rouge, vert, bleu);

                if (cmjn[0] > 0.8) totalCyan++;
                if (cmjn[1] > 0.8) totalMagenta++;
                if (cmjn[2] > 0.8) totalJaune++;
                if (cmjn[3] > 0.8) totalNoir++;

                if (cmjn[0] > 0.3 && cmjn[1] > 0.3 && cmjn[2] > 0.3) {
                    totalCombinations++;
                }
            }
        }

        return new int[]{totalNoir, totalCyan, totalMagenta, totalJaune, totalCombinations, totalBlanc};
    }
}

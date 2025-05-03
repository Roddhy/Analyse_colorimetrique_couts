package model;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name="F.getAllField" ,query="SELECT f FROM Fichier f") ,
        @NamedQuery(name="F.FindFieldById",query="SELECT f FROM Fichier f where f.id =:id")
})
@Table(name = "fichier")
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFichier", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "chemin", nullable = false)
    private String chemin;

    @Column(name = "nbrPage", nullable = false)
    private Integer nbrPage;

    @Column(name = "couvertureNoir", nullable = false)
    private Double couvertureNoir;

    @Column(name = "couvertureJaune", nullable = false)
    private Double couvertureJaune;

    @Column(name = "couvertureCyan", nullable = false)
    private Double couvertureCyan;

    @Column(name = "CouvertureMagenta", nullable = false)
    private Double couvertureMagenta;

    @Column(name = "heureAnalyse", nullable = false)
    private String heureAnalyse;

    @Column(name = "dateAnalyse", nullable = false)
    private String dateAnalyse;

    @Column(name = "cout", nullable = false)
    private Double cout;

    @Column(name = "couvertureCombinaison", nullable = false)
    private Double couvertureCombinaison;

    @Column(name = "blanc", nullable = false)
    private Double blanc;

    @Column(name = "coutNb", nullable = false)
    private Double coutNb;

    public Double getCoutNb() {
        return coutNb;
    }

    public void setCoutNb(Double coutNb) {
        this.coutNb = coutNb;
    }

    public Double getBlanc() {
        return blanc;
    }

    public void setBlanc(Double blanc) {
        this.blanc = blanc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Integer getNbrPage() {
        return nbrPage;
    }

    public void setNbrPage(Integer nbrPage) {
        this.nbrPage = nbrPage;
    }

    public Double getCouvertureNoir() {
        return couvertureNoir;
    }

    public void setCouvertureNoir(Double couvertureNoir) {
        this.couvertureNoir = couvertureNoir;
    }

    public Double getCouvertureJaune() {
        return couvertureJaune;
    }

    public void setCouvertureJaune(Double couvertureJaune) {
        this.couvertureJaune = couvertureJaune;
    }

    public Double getCouvertureCyan() {
        return couvertureCyan;
    }

    public void setCouvertureCyan(Double couvertureCyan) {
        this.couvertureCyan = couvertureCyan;
    }

    public Double getCouvertureMagenta() {
        return couvertureMagenta;
    }

    public void setCouvertureMagenta(Double couvertureMagenta) {
        this.couvertureMagenta = couvertureMagenta;
    }

    public String getHeureAnalyse() {
        return heureAnalyse;
    }

    public void setHeureAnalyse(String heureAnalyse) {
        this.heureAnalyse = heureAnalyse;
    }

    public String getDateAnalyse() {
        return dateAnalyse;
    }

    public void setDateAnalyse(String dateAnalyse) {
        this.dateAnalyse = dateAnalyse;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public Double getCouvertureCombinaison() {
        return couvertureCombinaison;
    }

    public void setCouvertureCombinaison(Double couvertureCombinaison) {
        this.couvertureCombinaison = couvertureCombinaison;
    }


}
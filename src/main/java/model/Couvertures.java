package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name="c.findCouvertureByFiledId",query="SELECT c FROM  Couvertures c WHERE c.idPdf= :id")
@Table(name = "couvertures")
public class Couvertures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCouverture", nullable = false)
    private Integer id;

    @Column(name = "idPdf", nullable = false)
    private Integer idPdf;

    @Column(name = "numeroPage", nullable = false)
    private Integer numeroPage;

    @Column(name = "noir", nullable = false)
    private Double noir;

    @Column(name = "magenta", nullable = false)
    private Double magenta;

    @Column(name = "jaune", nullable = false)
    private Double jaune;

    @Column(name = "cyan", nullable = false)
    private Double cyan;

    @Column(name = "combinaison", nullable = false)
    private Double combinaison;

    @Column(name = "blanc", nullable = false)
    private Double blanc;

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

    public Integer getIdPdf() {
        return idPdf;
    }

    public void setIdPdf(Integer idPdf) {
        this.idPdf = idPdf;
    }

    public Integer getNumeroPage() {
        return numeroPage;
    }

    public void setNumeroPage(Integer numeroPage) {
        this.numeroPage = numeroPage;
    }

    public double getNoir() {
        return noir;
    }

    public void setNoir(Double noir) {
        this.noir = noir;
    }

    public double getMagenta() {
        return magenta;
    }

    public void setMagenta(Double magenta) {
        this.magenta = magenta;
    }

    public double  getJaune() {
        return jaune;
    }

    public void setJaune(Double jaune) {
        this.jaune = jaune;
    }

    public double  getCyan() {
        return cyan;
    }

    public void setCyan(Double cyan) {
        this.cyan = cyan;
    }

    public Double getCombinaison() {
        return combinaison;
    }

    public void setCombinaison(Double combinaison) {
        this.combinaison = combinaison;
    }


}
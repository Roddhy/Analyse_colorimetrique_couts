package model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name="c.findTotalPixelByFiledId",query="SELECT t FROM  TotalPixel t WHERE t.idPdf= :id")

@Table(name = "totalpixel")

public class TotalPixel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPixel", nullable = false)
    private Integer id;

    @Column(name = "idPdf", nullable = false)
    private Integer idPdf;

    @Column(name = "noir", nullable = false)
    private Double noir;

    @Column(name = "magenta", nullable = false)
    private Double magenta;

    @Column(name = "cyan", nullable = false)
    private Double cyan;

    @Column(name = "jaune", nullable = false)
    private Double jaune;

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

    public Double getNoir() {
        return noir;
    }

    public void setNoir(Double noir) {
        this.noir = noir;
    }

    public Double getMagenta() {
        return magenta;
    }

    public void setMagenta(Double magenta) {
        this.magenta = magenta;
    }

    public Double getCyan() {
        return cyan;
    }

    public void setCyan(Double cyan) {
        this.cyan = cyan;
    }

    public Double getJaune() {
        return jaune;
    }

    public void setJaune(Double jaune) {
        this.jaune = jaune;
    }

    public Double getCombinaison() {
        return combinaison;
    }

    public void setCombinaison(Double combinaison) {
        this.combinaison = combinaison;
    }

}
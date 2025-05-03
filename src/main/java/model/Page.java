package model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name="c.findPageByFiledId",query="SELECT p FROM  Page p WHERE p.idPdf= :id")
@Table(name = "page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPage", nullable = false)
    private Integer id;

    @Column(name = "idPdf", nullable = false)
    private Integer idPdf;

    @Column(name = "numeroPage", nullable = false)
    private Integer numeroPage;

    @Column(name = "pixelNoir", nullable = false)
    private Float pixelNoir;

    @Column(name = "pixelCyan", nullable = false)
    private Float pixelCyan;

    @Column(name = "pixelJaune", nullable = false)
    private Float pixelJaune;

    @Column(name = "pixelMagenta", nullable = false)
    private Float pixelMagenta;

    @Column(name = "pixelCombinaison", nullable = false)
    private Float pixelCombinaison;

    @Column(name = "blanc", nullable = false)
    private Float blanc;

    public Float getBlanc() {
        return blanc;
    }

    public void setBlanc(Float blanc) {
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

    public Float getPixelNoir() {
        return pixelNoir;
    }

    public void setPixelNoir(Float pixelNoir) {
        this.pixelNoir = pixelNoir;
    }

    public Float getPixelCyan() {
        return pixelCyan;
    }

    public void setPixelCyan(Float pixelCyan) {
        this.pixelCyan = pixelCyan;
    }

    public Float getPixelJaune() {
        return pixelJaune;
    }

    public void setPixelJaune(Float pixelJaune) {
        this.pixelJaune = pixelJaune;
    }

    public Float getPixelMagenta() {
        return pixelMagenta;
    }

    public void setPixelMagenta(Float pixelMagenta) {
        this.pixelMagenta = pixelMagenta;
    }

    public Float getPixelCombinaison() {
        return pixelCombinaison;
    }

    public void setPixelCombinaison(Float pixelCombinaison) {
        this.pixelCombinaison = pixelCombinaison;
    }

}
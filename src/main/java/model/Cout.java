package model;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cout.getAll",query="SELECT c FROM Cout c"),
        @NamedQuery(name="Cout.update",query="UPDATE Cout c SET c.cyan=:cyan , c.jaune=:jaune ," +
                "c.magenta=:magenta,c.noir=:noir,c.coutFixePage=:coutfixepage")
})
@Table(name = "cout")
public class Cout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCout", nullable = false)
    private Integer id;

    @Column(name = "noir", nullable = false)
    private Double noir;

    @Column(name = "magenta", nullable = false)
    private Double magenta;

    @Column(name = "jaune", nullable = false)
    private Double jaune;

    @Column(name = "cyan", nullable = false)
    private Double cyan;

    @Column(name = "coutFixePage", nullable = false)
    private Double coutFixePage;

    public Double getCoutFixePage() {
        return coutFixePage;
    }

    public void setCoutFixePage(Double coutFixePage) {
        this.coutFixePage = coutFixePage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getJaune() {
        return jaune;
    }

    public void setJaune(Double jaune) {
        this.jaune = jaune;
    }

    public Double getCyan() {
        return cyan;
    }

    public void setCyan(Double cyan) {
        this.cyan = cyan;
    }

}
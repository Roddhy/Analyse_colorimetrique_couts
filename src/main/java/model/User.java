package model;

import jakarta.persistence.*;
@NamedQuery(name = "User.findByData", query = "SELECT u FROM User u WHERE u.login =:login and u.mdp=:mdp")
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, length = 32)
    private String login;

    @Column(name = "mdp", nullable = false, length = 32)
    private String mdp;

    @Column(name = "role", nullable = false, length = 32)
    private String role;

    @Column(name = "nom", nullable = false, length = 32)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 32)
    private String prenom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
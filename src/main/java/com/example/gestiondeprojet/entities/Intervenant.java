package com.example.gestiondeprojet.entities;

public class Intervenant extends Users {

    public Intervenant(){
        super();
    }

    public Intervenant(int id, String nom, String prenom, String adresse, String mail, String telephone, String password, String role) {
        super(id, nom, prenom, adresse, mail, telephone, password, role);
    }

    public Intervenant(String nom, String prenom, String adresse, String mail, String telephone, String role) {
        super(nom, prenom, adresse, mail, telephone, role);
    }

    public Intervenant(int id, String nom, String prenom, String adresse, String mail, String telephone, String password) {
        super(id, nom, prenom, adresse, mail, telephone, password);
    }

    /*private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String telephone;
    private String password;

    public Intervenant() {
    }

    public Intervenant(int id, String nom, String prenom, String adresse, String mail, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.telephone = telephone;
        this.password = password;
    }

    public Intervenant(String nom, String prenom, String adresse, String mail, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.telephone = telephone;
        this.password = password;
    }

    public Intervenant(String nom, String prenom, String adresse, String mail, String telephone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.telephone = telephone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Intervenant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", mail='" + mail + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }*/
}

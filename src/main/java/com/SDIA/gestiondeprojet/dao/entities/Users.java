package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;

public class Users implements Serializable {
    private long ID;
    private String NOM;
    private String PRENOM;
    private String ADRESSE;
    private String MAIL;
    private String TELEPHONE;

    private String PASSWORD;
    private String ROLE;

    public Users() {
    }

    public Users(long id, String nom, String prenom, String adresse, String mail, String telephone, String password , String role) {
        this.ID = id;
        this.NOM = nom;
        this.PRENOM = prenom;
        this.ADRESSE = adresse;
        this.MAIL = mail;
        this.TELEPHONE = telephone;
        this.PASSWORD = password;
        this.ROLE = role;
    }

    public Users(String nom, String prenom, String adresse, String mail, String telephone, String role) {
        this.NOM = nom;
        this.PRENOM = prenom;
        this.ADRESSE = adresse;
        this.MAIL = mail;
        this.TELEPHONE = telephone;
        this.ROLE = role;
    }

    public Users(int id, String nom, String prenom, String adresse, String mail, String telephone, String password) {
        this.ID = id;
        this.NOM = nom;
        this.PRENOM = prenom;
        this.ADRESSE = adresse;
        this.MAIL = mail;
        this.TELEPHONE = telephone;
        this.PASSWORD = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public void setADRESSE(String ADRESSE) {
        this.ADRESSE = ADRESSE;
    }

    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + ID +
                ", nom='" + NOM + '\'' +
                ", prenom='" + PRENOM + '\'' +
                ", adresse='" + ADRESSE + '\'' +
                ", mail='" + MAIL + '\'' +
                ", telephone='" + TELEPHONE + '\'' +
                ", role='" + ROLE + '\'' +
                '}';
    }
}

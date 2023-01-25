package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.List;

public class Tache implements Serializable {
    private long ID;
    private String ETAT;
    private String CREATEUR;
    private String DESCRIPTION;

    private long ID_PROJET;

    private List<Materielle> listmaterielles;

    public long getID_PROJET() {
        return ID_PROJET;
    }

    public void setID_PROJET(long ID_PROJET) {
        this.ID_PROJET = ID_PROJET;
    }

    public List<Materielle> getListmaterielles() {
        return listmaterielles;
    }

    public void setListmaterielles(List<Materielle> listmaterielles) {
        this.listmaterielles = listmaterielles;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getETAT() {
        return ETAT;
    }

    public void setETAT(String ETAT) {
        this.ETAT = ETAT;
    }

    public String getCREATEUR() {
        return CREATEUR;
    }

    public void setCREATEUR(String CREATEUR) {
        this.CREATEUR = CREATEUR;
    }

    @Override
    public String toString() {
        return "Tache {" +
                "ID=" + ID +
                ", ETAT='" + ETAT + '\'' +
                ", CREATEUR='" + CREATEUR + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                ", ID_PROJET='" + ID_PROJET + '\'' +
                '}';
    }
}

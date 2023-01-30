package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.List;

public class Tache implements Serializable {
    private long ID;
    private String ETAT;
    private Long ID_CREATEUR;
    private String DESCRIPTION;

    private long ID_PROJET;

    private String PROJET_DESCRIPTION;
    private String CREATEUR_EMAIL;

    private List<Materielle> listmaterielles;

    public String getCREATEUR_EMAIL() {
        return CREATEUR_EMAIL;
    }

    public void setCREATEUR_EMAIL(String CREATEUR_EMAIL) {
        this.CREATEUR_EMAIL = CREATEUR_EMAIL;
    }

    public String getPROJET_DESCRIPTION() {
        return PROJET_DESCRIPTION;
    }

    public void setPROJET_DESCRIPTION(String PROJET_DESCRIPTION) {
        this.PROJET_DESCRIPTION = PROJET_DESCRIPTION;
    }

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

    public long getID_CREATEUR() {
        return ID_CREATEUR;
    }

    public void setID_CREATEUR(Long ID_CREATEUR) {
        this.ID_CREATEUR = ID_CREATEUR;
    }

    @Override
    public String toString() {
        return this.getDESCRIPTION();
    }
}

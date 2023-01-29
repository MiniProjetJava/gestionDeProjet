package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Materielle implements Serializable {
    private long ID;
    private String TYPE; //PC , Laptop , Souris, Imprimante, ..etc
    private String MARQUE;
    private String ETAT; //En service, En panne, Hors service

    private String PROJET_DESCRIPTION;

    private String GERANT_EMAIL;
    private List<Tache> listTaches;

    public String getPROJET_DESCRIPTION() {
        return PROJET_DESCRIPTION;
    }

    public void setPROJET_DESCRIPTION(String PROJET_DESCRIPTION) {
        this.PROJET_DESCRIPTION = PROJET_DESCRIPTION;
    }

    public String getGERANT_EMAIL() {
        return GERANT_EMAIL;
    }

    public void setGERANT_EMAIL(String GERANT_EMAIL) {
        this.GERANT_EMAIL = GERANT_EMAIL;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getMARQUE() {
        return MARQUE;
    }

    public void setMARQUE(String MARQUE) {
        this.MARQUE = MARQUE;
    }

    public String getETAT() {
        return ETAT;
    }

    public void setETAT(String ETAT) {
        this.ETAT = ETAT;
    }

    public List<Tache> getListTaches() {
        return listTaches;
    }

    public void setListTaches(List<Tache> listTaches) {
        this.listTaches = listTaches;
    }

    @Override
    public String toString() {
        return "Materielle{ " +
                "ID=" + ID +
                ", TYPE='" + TYPE + '\'' +
                ", MARQUE='" + MARQUE + '\'' +
                ", ETAT='" + ETAT + '\'' +
                ", listTaches=" + listTaches +
                '}';
    }
}

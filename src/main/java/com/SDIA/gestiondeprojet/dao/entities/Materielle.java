package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Materielle implements Serializable {
    private long ID;
    private String TYPE; //PC , Laptop , Souris, Imprimante, ..etc
    private String MARQUE;
    private String ETAT; //En service, En panne, Hors service
    private List<Tache> listTaches;

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

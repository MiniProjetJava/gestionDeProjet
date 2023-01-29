package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projet implements Serializable {

    private long ID;
    private String NOM;
    private  String ETAT;
    private long ID_RESPONSABLE;
    private String DESCRIPTION;
    private int NBR_INTERVENANTS;

    public Projet() {
    }

    public Projet(String NOM, String ETAT, long ID_RESPONSABLE, String DESCRIPTION, int NBR_INTERVENANTS) {
        this.NOM = NOM;
        this.ETAT = ETAT;
        this.ID_RESPONSABLE = ID_RESPONSABLE;
        this.DESCRIPTION = DESCRIPTION;
        this.NBR_INTERVENANTS = NBR_INTERVENANTS;
    }

    public String getETAT() {
        return ETAT;
    }

    public void setETAT(String ETAT) {
        this.ETAT = ETAT;
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

    public long getID_RESPONSABLE() {
        return ID_RESPONSABLE;
    }

    public void setID_RESPONSABLE(long ID_RESPONSABLE) {
        this.ID_RESPONSABLE = ID_RESPONSABLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String description) {
        DESCRIPTION = description;
    }

    public int getNBR_INTERVENANTS() {
        return NBR_INTERVENANTS;
    }

    public void setNBR_INTERVENANTS(int NBR_INTERVENANTS) {
        this.NBR_INTERVENANTS = NBR_INTERVENANTS;
    }

    @Override
    public String toString() {
        return  DESCRIPTION + " |" +
                " 'ETAT': " + ETAT + " |" +
                " 'ID_RESPONSABLE': " + ID_RESPONSABLE + " |" +
                " 'NBR_INTERVENANTS': " + NBR_INTERVENANTS;
    }
}


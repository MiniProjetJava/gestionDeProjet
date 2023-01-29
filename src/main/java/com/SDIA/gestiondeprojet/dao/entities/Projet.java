package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projet implements Serializable {

    private long ID;
    private  String ETAT;
    private long ID_RESPONSABLE;
    private String DESCRIPTION;
    private int NBR_INTERVENANTS;

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


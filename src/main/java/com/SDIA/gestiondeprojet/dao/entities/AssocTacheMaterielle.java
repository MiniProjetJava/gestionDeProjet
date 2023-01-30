package com.SDIA.gestiondeprojet.dao.entities;

import com.SDIA.gestiondeprojet.metier.MaterielleMetier;

import javax.crypto.SecretKey;
import java.io.Serializable;

public class AssocTacheMaterielle implements Serializable {
    private long ID;
    private long ID_TACHE;
    private long ID_MATERIELLE;
    private Long ID_GERANT;
    private Tache tache;

    private Materielle materielle;

    public AssocTacheMaterielle(Tache tache, Materielle materielle) {
        this.tache = tache;
        this.materielle = materielle;
    }

    public AssocTacheMaterielle() {
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Materielle getMaterielle() {
        return materielle;
    }

    public void setMaterielle(Materielle materielle) {
        this.materielle = materielle;
    }


    public Long getID_GERANT() {
        return ID_GERANT;
    }

    public void setID_GERANT(Long ID_GERANT) {
        this.ID_GERANT = ID_GERANT;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getID_TACHE() {
        return ID_TACHE;
    }

    public void setID_TACHE(long ID_TACHE) {
        this.ID_TACHE = ID_TACHE;
    }

    public long getID_MATERIELLE() {
        return ID_MATERIELLE;
    }

    public void setID_MATERIELLE(long ID_MATERIELLE) {
        this.ID_MATERIELLE = ID_MATERIELLE;
    }

    @Override
    public String toString() {
        return "AssocTacheMaterielle{ " +
                "ID=" + ID +
                ", ID_TACHE=" + ID_TACHE +
                ", ID_MATERIELLE=" + ID_MATERIELLE +
                '}';
    }
}

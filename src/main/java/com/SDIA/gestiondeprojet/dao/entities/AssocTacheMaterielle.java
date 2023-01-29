package com.SDIA.gestiondeprojet.dao.entities;

import javax.crypto.SecretKey;
import java.io.Serializable;

public class AssocTacheMaterielle implements Serializable {
    private long ID;
    private long ID_TACHE;
    private long ID_MATERIELLE;

    private Long ID_GERANT;

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

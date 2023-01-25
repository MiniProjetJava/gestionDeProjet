package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;

public class AssocTacheUser implements Serializable {
    private long ID;
    private long ID_TACHE;
    private long ID_USER;

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

    public long getID_USER() {
        return ID_USER;
    }

    public void setID_USER(long ID_USER) {
        this.ID_USER = ID_USER;
    }

    @Override
    public String toString() {
        return "AssocTacheMaterielle{ " +
                "ID=" + ID +
                ", ID_TACHE=" + ID_TACHE +
                ", ID_USER=" + ID_USER +
                '}';
    }
}

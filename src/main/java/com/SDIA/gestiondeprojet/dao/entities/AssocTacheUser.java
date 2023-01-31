package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssocTacheUser implements Serializable {
    private long ID;
    private long ID_TACHE;
    private long ID_USER;

    private Tache TACHE;

    private String USER_EMAIL;

    private Date DEADLINE;

    public Date getDEADLINE() {
        return DEADLINE;
    }

    public void setDEADLINE(Date DEADLINE) {
        this.DEADLINE = DEADLINE;
    }

    public LocalDate getLocalDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(DEADLINE.toString(), formatter);
        return localDate;
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

    public long getID_USER() {
        return ID_USER;
    }

    public void setID_USER(long ID_USER) {
        this.ID_USER = ID_USER;
    }

    public Tache getTACHE() {
        return TACHE;
    }

    public void setTACHE(Tache TACHE) {
        this.TACHE = TACHE;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
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

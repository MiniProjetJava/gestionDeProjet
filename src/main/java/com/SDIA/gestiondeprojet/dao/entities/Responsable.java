package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Responsable extends Users implements Serializable {

    public Responsable() {
    }

    public Responsable(int id, String nom, String prenom, String adresse, String mail, String telephone, String password, String role) {
        super(id, nom, prenom, adresse, mail, telephone, password, role);
    }

    public Responsable(String nom, String prenom, String adresse, String mail, String telephone, String role) {
        super(nom, prenom, adresse, mail, telephone, role);
    }
}

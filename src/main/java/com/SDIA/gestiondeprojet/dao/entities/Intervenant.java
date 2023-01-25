package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Intervenant extends Users implements Serializable {


 public Intervenant() {
 }

 public Intervenant(String nom, String prenom, String adresse, String mail, String telephone, String role, List<Tache> listTaches) {
  super(nom, prenom, adresse, mail, telephone, role);
 }
}

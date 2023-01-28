package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;

 public interface TacheMetier {

     List<Tache> selectAll();

     Tache selectTacheByID(long ID);

     boolean updateTache(Tache Element) throws SQLException;

     void insertTache(Tache Element);

     void delete(Tache tache) throws SQLException;

     List<Tache> selectTacheByCreateur(Users Createur);

     List<Tache> selectTacheByEtat(String Etat);

     List<Materielle> selectMateriellesDeTache(Tache tache);

     List<Tache> selectTacheByProjet(Projet projet);
     List<Tache> selectTacheByDesc(String desc);
}

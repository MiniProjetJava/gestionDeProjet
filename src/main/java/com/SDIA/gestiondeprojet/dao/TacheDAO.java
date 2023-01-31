package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;

public interface TacheDAO extends DAO<Tache>{

    List<Tache> findByCreateur(Users Createur);

    List<Tache> findByEtat(String Etat);

    List<Tache> findByProjet(Projet projet);
    List<Materielle> findAllMaterielles(Tache tache);

    Tache findByMaterielle(Materielle materielle);
    List<Tache> findByDescription(String desc);
    int nombreDeTache(Users users) throws SQLException;

}

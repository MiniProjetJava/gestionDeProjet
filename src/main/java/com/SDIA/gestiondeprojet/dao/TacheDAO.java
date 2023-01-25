package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.util.List;

public interface TacheDAO extends DAO<Tache>{

    List<Tache> findByCreateur(String Createur);

    List<Tache> findByEtat(String Etat);
    List<Materielle> findAllMaterielles(Tache tache);

}

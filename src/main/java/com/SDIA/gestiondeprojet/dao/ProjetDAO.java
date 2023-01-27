package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Responsable;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.util.List;

public interface ProjetDAO extends DAO<Projet> {

    List<Tache> findAllTasks(Projet projet);
    List<Projet> findByResponsable(Responsable responsable);

}

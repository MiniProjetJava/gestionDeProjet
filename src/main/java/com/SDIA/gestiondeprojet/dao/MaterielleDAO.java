package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.DAO;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.util.List;

public interface MaterielleDAO extends DAO<Materielle> {

    List<Materielle> findByType(String type);

    List<Materielle> findByEtat(String Etat);

    List<Tache> findAllTasks(Materielle materielle);


}

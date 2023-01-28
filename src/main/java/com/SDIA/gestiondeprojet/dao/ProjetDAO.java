package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;

import java.util.List;

public interface ProjetDAO extends DAO<Projet> {

    List<Tache> findAllTasks(Projet projet);
    List<Projet> findByResponsable(Users responsable);

}

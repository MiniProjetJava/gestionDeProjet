package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;

import java.sql.SQLException;
import java.util.List;

public interface ProjetDAO extends DAO<Projet> {

    List<Tache> findAllTasks(Projet projet);
    List<Projet> findByResponsable(long responsable);
    List<Projet> findByMotCle(long id, String mot) throws SQLException;

}

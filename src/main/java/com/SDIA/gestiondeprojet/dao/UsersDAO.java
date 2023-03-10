package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;

import java.sql.SQLException;
import java.util.List;


public interface UsersDAO extends DAO <Users>{

    List<Users> findByRole(String role);
    int findByUsers(Users users) throws SQLException;
    List<Users> findAllIntervenant() throws SQLException;
    List<Users> findAllResponsable();

    List<Tache> findAllTasks(Users user); //pour les "Responsable" et "Intervenant"

    List<Projet> findAllProjects(Users responsable); //Pour l'utilisateur avec le role "Responsable"

}

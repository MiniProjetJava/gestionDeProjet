package com.SDIA.gestiondeprojet.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T> {



    List<T> findAll();

    T findById(long X); //Retourne un seul element

    boolean update(T Element) throws SQLException; //Mettre à jour toute une occurence avec les valeurs des atts de Element

    void save(T Element);

    void delete(T p); //supprimer un ou plusieurs occurences


}


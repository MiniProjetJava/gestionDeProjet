package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProjetMetier {

    public List<Projet> selectAll();

    public Projet selectByID(long X);

    public boolean updateProjet(Projet Element) throws SQLException;

    public void insertProjet(Projet Element);

    public void deleteProjet(Projet p) throws SQLException;

    public List<Tache> selectAllTaskDeProjet(Projet projet);

    public List<Projet> selectByReponsable(Users responsable);
    public int nombreProjet(Users users) throws SQLException;
}

package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.AssocTacheMaterielleDAOImpl;
import com.SDIA.gestiondeprojet.dao.ProjetDAOImpl;
import com.SDIA.gestiondeprojet.dao.TacheDAOImpl;
import com.SDIA.gestiondeprojet.dao.UsersDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MaterielleMetier {
    public List<Materielle> selectAll();

    public Materielle selectMaterielleByID(long X);

    public boolean update(Materielle Element) throws SQLException;

    public void insert(Materielle Element);
    public void delete(Materielle materielle) throws SQLException;


    public List<Materielle> selectAllMaterielleByType(String type);

    public List<Materielle> selectAllMateriellEtat(String Etat);

    public List<Tache> selectAllTasksByMaterielle(Materielle materielle);
}

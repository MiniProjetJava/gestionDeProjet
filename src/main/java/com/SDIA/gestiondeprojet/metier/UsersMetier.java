package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;

public class UsersMetier implements IMetier<Users>{
    @Override
    public Users add(Users o) throws SQLException {
        return null;
    }

    @Override
    public List<Intervenant> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Users o) throws SQLException {

    }

    @Override
    public List<Users> findByMotCle(String mot) throws SQLException {
        return null;
    }

    @Override
    public Users findByMail(String mail) throws SQLException {
        return null;
    }

    @Override
    public Users update(Users o) throws SQLException {
        return null;
    }

    // Méthodes pour la vérification de l'utilisateur
    public void checkUsers(Users users){

    }
}

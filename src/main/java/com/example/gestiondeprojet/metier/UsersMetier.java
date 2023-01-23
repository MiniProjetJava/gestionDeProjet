package com.example.gestiondeprojet.metier;

import com.example.gestiondeprojet.dao.UsersDao;
import com.example.gestiondeprojet.entities.Intervenant;
import com.example.gestiondeprojet.entities.Users;

import java.sql.SQLException;
import java.util.List;

public class UsersMetier implements IMetier<Users>{
    public UsersMetier() {
    }

    @Override
    public Users add(Users users) throws SQLException {
        return new UsersDao().add(users);
    }

    @Override
    public List<Intervenant> getAll() throws SQLException {
        return new UsersDao().getAllIntervenant();
    }

    @Override
    public void delete(Users o) throws SQLException {

    }

    @Override
    public List<Users> findByMotClé(String mot) throws SQLException {
        return null;
    }

    @Override
    public Users findByMail(String mail) throws SQLException {
        return new UsersDao().findByMail(mail);
    }

    @Override
    public Users update(Users o) throws SQLException {
        return null;
    }
}

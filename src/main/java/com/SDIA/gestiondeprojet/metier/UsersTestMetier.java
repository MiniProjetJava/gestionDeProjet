package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.UsersTest;
import com.SDIA.gestiondeprojet.dao.UsersTestDao;

import java.sql.SQLException;
import java.util.List;

public class UsersTestMetier implements IMetier<UsersTest>{

    public UsersTestMetier() {
    }

    public int checkUsers(UsersTest usersTest) throws SQLException {
        UsersTestDao usersTestDao = new UsersTestDao();
        int resultat = usersTestDao.checkUsers(usersTest);
        if(resultat == 1) {
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public UsersTest add(UsersTest users) throws SQLException {
        return new UsersTestDao().add(users);
    }

    @Override
    public List<Intervenant> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(UsersTest o) throws SQLException {

    }

    @Override
    public List<UsersTest> findByMotClé(String mot) throws SQLException {
        return null;
    }

    @Override
    public UsersTest findByMail(String mail) throws SQLException {
        return null;
    }

    @Override
    public UsersTest update(UsersTest o) throws SQLException {
        return null;
    }

}

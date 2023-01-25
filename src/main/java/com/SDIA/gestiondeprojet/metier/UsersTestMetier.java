package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.UsersTest;
import com.SDIA.gestiondeprojet.dao.UsersTestDao;

import java.sql.SQLException;
import java.util.List;

public class UsersTestMetier {

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




}

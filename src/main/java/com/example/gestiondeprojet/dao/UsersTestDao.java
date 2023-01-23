package com.example.gestiondeprojet.dao;

import com.example.gestiondeprojet.entities.Intervenant;
import com.example.gestiondeprojet.entities.Responsable;
import com.example.gestiondeprojet.entities.UsersTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersTestDao implements DaoImpl<UsersTest>{
    @Override
    public UsersTest add(UsersTest users) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into users(role, password, mail) values (?,?,?)" );
        pst.setString(1, users.getRole());
        pst.setString(2, users.getPassword());
        pst.setString(3, users.getMail());

        pst.executeUpdate();
        return users;
    }

    @Override
    public UsersTest update(UsersTest o) throws SQLException {
        return null;
    }

    @Override
    public List<Intervenant> getAllIntervenant() throws SQLException {
        return null;
    }

    @Override
    public List<Responsable> getAllResponsable() throws SQLException {
        return null;
    }

    @Override
    public List<UsersTest> findByMotCle(String mot) throws SQLException {
        return null;
    }

    @Override
    public UsersTest findByMail(String mail) throws SQLException {
        return null;
    }


    @Override
    public void delete(int id) throws SQLException {

    }

    public int checkUsers(UsersTest usersTest) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select count(1), role from users where mail=? and password=?");
        pst.setString(1, usersTest.getMail());
        pst.setString(2, usersTest.getPassword());

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            if((rs.getInt(1) == 1) && rs.getString(2).equals("Responsable"))
            {
                return 1;
            }
            else return 0;
        }

        return 0;
    }
}

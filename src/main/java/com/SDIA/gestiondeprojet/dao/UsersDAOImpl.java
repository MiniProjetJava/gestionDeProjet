package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOImpl implements UsersDAO{

    private Connection connection = SignletonConnexion.getConnection();

    private Statement st = connection.createStatement();

    public UsersDAOImpl() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println("[EXCEPTION TRIGGERED FROM UsersDAOImpl.class]-> " + e.getMessage());
        }
    }

    @Override
    public List<Users> findAll() {
        List<Users> listUsers = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Users");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Users user = new Users();
                user.setID(rs.getInt("ID"));
                user.setNOM(rs.getString("NOM"));
                user.setPRENOM(rs.getString("PRENOM"));
                user.setADRESSE(rs.getString("ADRESSE"));
                user.setMAIL(rs.getString("EMAIL"));
                user.setTELEPHONE(rs.getString("TELEPHONE"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setROLE(rs.getString("ROLE"));

                listUsers.add(user);
            }
            if (listUsers.isEmpty()) {
                System.out.println("[INFO]-> The USERS TABLE IS EMPTY !!");
            }
            return listUsers;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-listUsers]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public Users findById(long X) {
        try {
            Users user = new Users();
            ResultSet rs = st.executeQuery("select * from Users where ID = "+X);
            //Mapping Occurrence to Object
            if(rs.next()) {
                user.setID(rs.getInt("ID"));
                user.setNOM(rs.getString("NOM"));
                user.setPRENOM(rs.getString("PRENOM"));
                user.setADRESSE(rs.getString("ADRESSE"));
                user.setMAIL(rs.getString("EMAIL"));
                user.setTELEPHONE(rs.getString("TELEPHONE"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setROLE(rs.getString("ROLE"));

                System.out.println("[INFO]-> The client identified by ID : " + user.getID() + " has been returned successfully!");
            }
            else {
                System.out.println("[INFO]-> The client identified by ID: ' " + user.getID() + " ' doesn't exist in the USERS table!");
            }
            return user;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-Users]-> " + e.getMessage());
            return null;
        }
    }

    public Users findByName(String nom) {
        List<Users> listUsers = new ArrayList<>();
        try {
            Users user = new Users();
            ResultSet rs = st.executeQuery("select * from Users");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Users user = new Users();
                user.setID(rs.getInt("ID"));
                user.setNOM(rs.getString("NOM"));
                user.setPRENOM(rs.getString("PRENOM"));
                user.setADRESSE(rs.getString("ADRESSE"));
                user.setMAIL(rs.getString("EMAIL"));
                user.setTELEPHONE(rs.getString("TELEPHONE"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setROLE(rs.getString("ROLE"));

                listUsers.add(user);
            }
            if (listUsers.isEmpty()) {
                System.out.println("[INFO]-> The USERS TABLE IS EMPTY !!");
            }
            return listUsers;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-listUsers]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Users Element) {
        return false;
    }

    @Override
    public void save(Users Element) {

    }

    @Override
    public void delete(Users p) {

    }

    @Override
    public Users findByRole(String role) {
        return null;
    }
}

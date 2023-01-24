package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.*;
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

                System.out.println("[INFO]-> The User identified by ID : " + user.getID() + " has been returned successfully!");
            }
            else {
                System.out.println("[INFO]-> The User identified by ID: ' " + user.getID() + " ' doesn't exist in the USERS table!");
            }
            return user;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-Users]-> " + e.getMessage());
            return null;
        }
    }

    public List<Users> findByName(String nom) {
        List<Users> listUsers = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Users where NOM like '%"+nom+"%'");
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
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByName]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Users Element) {
        try {
            int AffectedRow = st.executeUpdate("update Client " +
                    "set NOM = '" + Element.getNOM() + "', " +
                    "PRENOM = '" + Element.getPRENOM() + "', " +
                    "ADRESSE = '" + Element.getADRESSE() + "', " +
                    "MAIL = '" + Element.getMAIL() + "', " +
                    "TELEPHONE = '" + Element.getTELEPHONE() + "', " +
                    "ROLE = '" + Element.getROLE() + "' " +

                    "where ID = " + Element.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The user identified by 'Nom : " + Element.getNOM() + " | ROLE : " + Element.getROLE() + "' has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The user identified by 'Nom : " + Element.getNOM() + " | ROLE : " + Element.getROLE() + "' doesn't exist in the Client table!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / UPDATE-User]-> " + e.getMessage());
            return false;

        }
    }
    @Override
    public void save(Users Element) {
        try {
        PreparedStatement ps = connection.prepareStatement("insert into Client " +
                "values(null, ?, ?, ?, ?, ?, ?)");

        ps.setString(1, Element.getNOM());
        ps.setString(2, Element.getPRENOM());
        ps.setString(3, Element.getADRESSE());
        ps.setString(4, Element.getMAIL());
        ps.setString(5, Element.getTELEPHONE());
        ps.setString(6, Element.getROLE());



        int AffectedRow = ps.executeUpdate();

        if (AffectedRow > 0) {
            System.out.println("[INFO]-> the user [Name: " + Element.getNOM() + "| ROLE: " + Element.getROLE() + "] has been inserted successfully in table Client !");
        }
    } catch (SQLException e) {
        System.out.println("[EXCEPTION TRIGGERED / INSERT-User]-> " + e.getMessage());
     }

    }

    @Override
    public void delete(Users user) {
        try {
            int AffectedRow = st.executeUpdate("delete from USER where id = " + user.getID());
            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The user with Name: '" + user.getNOM() + "' | ROLE: '" + user.getROLE() + "' has been deleted successfully!");
            } else
                System.out.println("[INFO]-> The user with name Name: '" + user.getNOM() + "' | ROLE: '" + user.getROLE() + "' doesn't exist in the table user!");

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / DELETE-User]-> " + e.getMessage());
        }
    }

    @Override
    public List<Users> findByRole(String role) {
            List<Users> listUsers = new ArrayList<>();
            try {
                ResultSet rs = st.executeQuery("select * from Users where NOM = '"+role+"'");
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
                System.out.println("[EXCEPTION TRIGGERED / SELECT-findByRole]-> " + e.getMessage());
                return null;
            }
        }
}

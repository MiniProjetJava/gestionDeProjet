package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;

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
                user.setID(rs.getLong("ID"));
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
                user.setID(rs.getLong("ID"));
                user.setNOM(rs.getString("NOM"));
                user.setPRENOM(rs.getString("PRENOM"));
                user.setADRESSE(rs.getString("ADRESSE"));
                user.setMAIL(rs.getString("EMAIL"));
                user.setTELEPHONE(rs.getString("TELEPHONE"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setROLE(rs.getString("ROLE"));

                System.out.println("[INFO]-> The User identified by ID : " + X + " has been returned successfully!");
            }
            else {
                System.out.println("[INFO]-> The User identified by ID: ' " + X + " ' doesn't exist in the USERS table!");
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
                user.setID(rs.getLong("ID"));
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
        PreparedStatement ps = connection.prepareStatement("insert into users(NOM, PRENOM, ADRESSE, TELEPHONE, ROLE, MAIL, PASSWORD) " +
                "values(?, ?, ?, ?, ?, ?, ?)");

        ps.setString(1, Element.getNOM());
        ps.setString(2, Element.getPRENOM());
        ps.setString(3, Element.getADRESSE());
        ps.setString(4, Element.getTELEPHONE());
        ps.setString(5, Element.getROLE());
        ps.setString(6, Element.getMAIL());
        ps.setString(7, Element.getPASSWORD());


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
                    user.setID(rs.getLong("ID"));
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

    @Override
    public int findByUsers(Users users) throws SQLException {
        Connection con = SignletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select count(1), role from users where MAIL=? and PASSWORD=?");
        pst.setString(1, users.getMAIL());
        pst.setString(2, users.getPASSWORD());

        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            if ((rs.getInt(1) == 1) && rs.getString(2).equals("Responsable")){
                return 1;
            }
            else if((rs.getInt(1) == 1) && rs.getString(2).equals("Intervenant")){
                return 2;
            }

            else return 0;
        }
        return -1;
    }

    @Override
    public List<Users> findAllIntervenant() throws SQLException {
        List<Users> listUsers = new ArrayList<>();
        Connection con = SignletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from users where ROLE = 'Intervenant'");
        ResultSet rs = pst.executeQuery();

        try {
            //Mapping Occurrence to Object
            while (rs.next()) {
                Users user = new Users();
                user.setID(rs.getLong("ID"));
                user.setNOM(rs.getString("NOM"));
                user.setPRENOM(rs.getString("PRENOM"));
                user.setADRESSE(rs.getString("ADRESSE"));
                user.setMAIL(rs.getString("MAIL"));
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

        }
        return null;
    }

    public List<Tache> findAllTasks(Users user) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs_Taches = st.executeQuery("select ID_TACHE from AssocTacheUser where ID_USER = " + user.getID());
            while (rs_Taches.next()) {
                TacheDAO tacheDAO = new TacheDAOImpl();
                Tache tache = tacheDAO.findById(rs_Taches.getInt(1));
                listTaches.add(tache);
            }
            if( listTaches.isEmpty() ){
                System.out.println("[INFO]-> The material identified by ID: ' " + user.getID() + " ' doesn't have any task available yet!");
            }
            return listTaches;
        }catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAllTasks >  MaterielleDAOImpl.java ]-> " + e.getMessage());
        }
        return null;
    }

    @Override

    public List<Users> findAllResponsable() {
        return null;
    }

    public List<Projet> findAllProjects(Responsable responsable) {
        try {
            List<Projet> listProjets = new ProjetDAOImpl().findByResponsable(responsable);
            return listProjets;

        }catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAllProjects >  MaterielleDAOImpl.java ]-> " + e.getMessage());
            return null;
        }
    }
}


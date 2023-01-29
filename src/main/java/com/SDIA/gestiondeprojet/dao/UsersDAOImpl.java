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
                user.setMAIL(rs.getString("MAIL"));
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

    public Users findByMail(String mail) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("select * from users where MAIL=?");
        pst.setString(1,mail);
        ResultSet rs = pst.executeQuery();
        Users users = new Users();

        while (rs.next()){
            users.setID(rs.getLong("ID"));
            users.setNOM(rs.getString("NOM"));
            users.setPRENOM(rs.getString("PRENOM"));
            users.setADRESSE(rs.getString("ADRESSE"));
            users.setMAIL(rs.getString("MAIL"));
            users.setTELEPHONE(rs.getString("TELEPHONE"));
            users.setPASSWORD(rs.getString("PASSWORD"));
            users.setROLE(rs.getString("ROLE"));
        }
        return users;
    }

    @Override
    public boolean update(Users Element) throws SQLException {
        try {

            int AffectedRow = st.executeUpdate("update users " +
                    "set NOM = ' " + Element.getNOM() + " ', " +
                    "PRENOM = ' " + Element.getPRENOM() + "', " +
                    "ADRESSE = ' " + Element.getADRESSE() + "' " +
                    "MAIL = ' " + Element.getMAIL() + "' " +
                    "TELEPHONE = ' " + Element.getTELEPHONE() + "' " +
                    "PASSWORD = ' " + Element.getPASSWORD() + "' " +

                    "where ID = " + Element.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The users identified by [NOM: " + Element.getNOM() + "| PRENOM: " + Element.getPRENOM() + "] has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The task identified by [NOM: " + Element.getNOM() + "| PRENOM: " + Element.getPRENOM() + "] is not updated in the tache table!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / UPDATE-User]-> " + e.getMessage());
            return false;

        }

    }

    public Users updateUser(Users Element) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("update users set NOM = ?, PRENOM = ?, ADRESSE = ?, MAIL = ?, TELEPHONE = ?, PASSWORD = ? where ID = ?");
        pst.setString(1, Element.getNOM());
        pst.setString(2, Element.getPRENOM());
        pst.setString(3, Element.getADRESSE());
        pst.setString(4, Element.getMAIL());
        pst.setString(5, Element.getTELEPHONE());
        pst.setString(6, Element.getPASSWORD());
        pst.setLong(7, Element.getID());

        pst.executeUpdate();
        System.out.println(Element);
        return Element;

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
    public void delete(Users user) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("delete from users where ID=?");
        pst.setLong(1, user.getID());
        pst.executeUpdate();

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
                return null;
            }
        }

    @Override
    public int findByUsers(Users users) throws SQLException {
        Connection con = SignletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select role from users where MAIL=? and PASSWORD=?");
        pst.setString(1, users.getMAIL());
        pst.setString(2, users.getPASSWORD());

        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            if (rs.getString(1).equals("RESPONSABLE")){
                return 1;
            }
            if(rs.getString(1).equals("INTERVENANT")) {
                return 2;
            }
        }
        return -1;
    }

    @Override
    public List<Users> findAllIntervenant() throws SQLException {
        List<Users> listUsers = new ArrayList<>();
        Connection con = SignletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from users where ROLE = 'INTERVENANT'");
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

    public List<Projet> findAllProjects(Users responsable) {
        try {
            List<Projet> listProjets = new ProjetDAOImpl().findByResponsable(responsable.getID());
            return listProjets;

        }catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAllProjects >  MaterielleDAOImpl.java ]-> " + e.getMessage());
            return null;
        }
    }

    public List<Users> findbyMotCle(String mot) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("select * from users where ROLE = ? and NOM like ? or PRENOM like ? ");
        pst.setString(1,"INTERVENANT");
        pst.setString(2, "%"+mot+"%");
        pst.setString(3, "%"+mot+"%");

        ResultSet rs = pst.executeQuery();
        Users p = new Users();

        List<Users> users = new ArrayList<>();

        while (rs.next()){
            Users user  = new Users();
            user.setID(rs.getLong("ID"));
            user.setNOM(rs.getString("NOM"));
            user.setPRENOM(rs.getString("PRENOM"));
            user.setADRESSE(rs.getString("ADRESSE"));
            user.setMAIL(rs.getString("MAIL"));
            user.setTELEPHONE(rs.getString("TELEPHONE"));
            user.setPASSWORD(rs.getString("PASSWORD"));
            user.setROLE(rs.getString("ROLE"));

            users.add(user);

        }
        return users;
    }
}


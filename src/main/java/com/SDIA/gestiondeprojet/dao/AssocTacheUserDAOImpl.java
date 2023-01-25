package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.AssocTacheUser;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssocTacheUserDAOImpl implements AssocTacheUserDAO {
    Connection connection = SignletonConnexion.getConnection();
    Statement st = connection.createStatement();

    public AssocTacheUserDAOImpl() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println("[EXCEPTION TRIGGERED FROM AssocTacheUserDAOImpl.class]-> " + e.getMessage());
        }
    }

    @Override
    public List<AssocTacheUser> findByUser(Users user) {
        List<AssocTacheUser> listTaMa = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheUser where ID_USER = "+user.getID());
            //Mapping Occurrence to Object
            while(rs.next()) {
                AssocTacheUser ATU = new AssocTacheUser();
                ATU.setID(rs.getLong("ID"));
                ATU.setID_USER(rs.getLong("ID_USER"));
                ATU.setID_TACHE(rs.getLong("ID_TACHE"));

                listTaMa.add(ATU);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The user with ID+"+user.getID()+" doesn't exist in table AssocTacheUser !!");
            }
            return listTaMa;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByUser > AssocTacheUserDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<AssocTacheUser> findByTache(Tache tache) {
        List<AssocTacheUser> listTaMa = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheUser where ID_TACHE = "+tache.getID());
            //Mapping Occurrence to Object
            while(rs.next()) {
                AssocTacheUser ATU = new AssocTacheUser();
                ATU.setID(rs.getLong("ID"));
                ATU.setID_USER(rs.getLong("ID_USER"));
                ATU.setID_TACHE(rs.getLong("ID_TACHE"));

                listTaMa.add(ATU);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The task with ID+"+tache.getID()+" doesn't exist in table AssocTacheUser !!");
            }
            return listTaMa;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByTache > AssocTacheUserDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<AssocTacheUser> findAll() {
        List<AssocTacheUser> listTaMa = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheUser");
            //Mapping Occurrence to Object
            while(rs.next()) {
                AssocTacheUser ATU = new AssocTacheUser();
                ATU.setID(rs.getLong("ID"));
                ATU.setID_USER(rs.getLong("ID_USER"));
                ATU.setID_TACHE(rs.getLong("ID_TACHE"));

                listTaMa.add(ATU);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The table AssocTacheUser is EMPTY!!");
            }
            return listTaMa;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAll > AssocTacheUserDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public AssocTacheUser findById(long X) {
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheUser where ID = "+X);
            AssocTacheUser ATU = new AssocTacheUser();
            //Mapping Occurrence to Object
            if(rs.next()) {
                ATU.setID(rs.getLong("ID"));
                ATU.setID_USER(rs.getLong("ID_USER"));
                ATU.setID_TACHE(rs.getLong("ID_TACHE"));
            }
           else {
                System.out.println("[INFO]-> The occurence  with ID+"+X+" doesn't exist in table AssocTacheUser !!");
            }
            return ATU;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findById > AssocTacheUserDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(AssocTacheUser Element) {
        return false;
    }


    @Override
    public void save(Tache tache, Users user) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into AssocTacheUser " +
                    "values(null, ?, ?)");

            ps.setLong(1, tache.getID());
            ps.setLong(2, user.getID());
            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the new occurence  [ID_TACHE: " + tache.getID() + "| ID_USER: " + user.getID() + "] has been inserted successfully in table AssocTacheUser !");
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT > AssocTacheUserDAOImpl]-> " + e.getMessage());
        }

    }

    @Override
    public void delete(Tache tache, Users user) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from AssocTacheUser " +
                    "where ID_TACHE = "+tache.getID()+" "+
                    "and ID_USER = "+user.getID());

            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the old occurence  [ID_TACHE: " + tache.getID() + "| ID_USER: " + user.getID() + "] has been deleted successfully from table AssocTacheUser !");
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT > AssocTacheUserDAOImpl]-> " + e.getMessage());
        }

    }

    @Override
    public void save(AssocTacheUser Element) {

    }

    @Override
    public void delete(AssocTacheUser p) {

    }
}

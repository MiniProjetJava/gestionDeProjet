package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.AssocTacheMaterielle;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssocTacheMaterielleDAOImpl implements AssocTacheMaterielleDAO{
    Connection connection = SignletonConnexion.getConnection();
    Statement st = connection.createStatement();

    public AssocTacheMaterielleDAOImpl() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println("[EXCEPTION TRIGGERED FROM AssocTacheMaterielleDAOImpl.class]-> " + e.getMessage());
        }
    }

    @Override
    public List<AssocTacheMaterielle> findByMaterielle(Materielle materielle) {
        List<AssocTacheMaterielle> listTaMa = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheMaterielle where ID_MATERIELLE = "+materielle.getID());
            //Mapping Occurrence to Object
            while(rs.next()) {
                AssocTacheMaterielle ATM = new AssocTacheMaterielle();
                ATM.setID(rs.getLong("ID"));
                ATM.setID_MATERIELLE(rs.getLong("ID_MATERIELLE"));
                ATM.setID_TACHE(rs.getLong("ID_TACHE"));

                listTaMa.add(ATM);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The materiel with ID+"+materielle.getID()+" doesn't exist in table AssocTacheMaterielle !!");
            }
            return listTaMa;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByMaterielle > AssocTacheMaterielleDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<AssocTacheMaterielle> findByTache(Tache tache) {
        List<AssocTacheMaterielle> listTaMa = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheMaterielle where ID_TACHE = "+tache.getID());
            //Mapping Occurrence to Object
            while(rs.next()) {
                AssocTacheMaterielle ATM = new AssocTacheMaterielle();
                ATM.setID(rs.getLong("ID"));
                ATM.setID_MATERIELLE(rs.getLong("ID_MATERIELLE"));
                ATM.setID_TACHE(rs.getLong("ID_TACHE"));

                listTaMa.add(ATM);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The materiel with ID+"+tache.getID()+" doesn't exist in table AssocTacheMaterielle !!");
            }
            return listTaMa;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByTache > AssocTacheMaterielleDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<AssocTacheMaterielle> findAll() {
        List<AssocTacheMaterielle> listTaMa = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheMaterielle");
            //Mapping Occurrence to Object
            while(rs.next()) {
                AssocTacheMaterielle ATM = new AssocTacheMaterielle();
                ATM.setID(rs.getLong("ID"));
                ATM.setID_MATERIELLE(rs.getLong("ID_MATERIELLE"));
                ATM.setID_TACHE(rs.getLong("ID_TACHE"));

                listTaMa.add(ATM);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The table AssocTacheMaterielle is EMPTY!!");
            }
            return listTaMa;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAll > AssocTacheMaterielleDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public AssocTacheMaterielle findById(long X) {
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheMaterielle where ID_TACHE = "+X);
            AssocTacheMaterielle ATM = new AssocTacheMaterielle();
            //Mapping Occurrence to Object
            if(rs.next()) {
                ATM.setID(rs.getLong("ID"));
                ATM.setID_MATERIELLE(rs.getLong("ID_MATERIELLE"));
                ATM.setID_TACHE(rs.getLong("ID_TACHE"));
            }
           else {
                System.out.println("[INFO]-> The occurence  with ID+"+X+" doesn't exist in table AssocTacheMaterielle !!");
            }
            return ATM;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findById > AssocTacheMaterielleDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(AssocTacheMaterielle Element) {
        return false;
    }


    @Override
    public void save(Tache tache, Materielle materielle) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into AssocTacheMaterielle " +
                    "values(null, ?, ?)");

            ps.setLong(1, tache.getID());
            ps.setLong(2, materielle.getID());
            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the new occurence  [ID_TACHE: " + tache.getID() + "| ID_MATERIELLE: " + materielle.getID() + "] has been inserted successfully in table AssocTacheMaterielle !");
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT > AssocTacheMaterielleDAOImpl]-> " + e.getMessage());
        }

    }

    @Override
    public void delete(Tache tache, Materielle materielle) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from AssocTacheMaterielle " +
                    "where ID_TACHE = "+tache.getID()+" "+
                    "and ID_MATERIELLE = "+materielle.getID()+"");

            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the old occurence  [ID_TACHE: " + tache.getID() + "| ID_MATERIELLE: " + materielle.getID() + "] has been deleted successfully from table AssocTacheMaterielle !");
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT > AssocTacheMaterielleDAOImpl]-> " + e.getMessage());
        }

    }

    @Override
    public void save(AssocTacheMaterielle Element) {

    }

    @Override
    public void delete(AssocTacheMaterielle p) {

    }
}

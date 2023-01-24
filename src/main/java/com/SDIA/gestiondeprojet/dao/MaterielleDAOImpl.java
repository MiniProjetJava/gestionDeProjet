package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDAOImpl implements TacheDAO{
    Connection connection = SignletonConnexion.getConnection();
    Statement st = connection.createStatement();
    public TacheDAOImpl() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println("[EXCEPTION TRIGGERED FROM TacheDAOImpl.class]-> " + e.getMessage());
        }
    }


    @Override
    public List<Tache> findAll() {
        List<Tache> listTache = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Tache");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Tache tache = new Tache();
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setCREATEUR(rs.getString("CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                listTache.add(tache);
            }
            if (listTache.isEmpty()) {
                System.out.println("[INFO]-> The TacheS TABLE IS EMPTY !!");
            }
            return listTache;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-listTache]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public Tache findById(long X) {
        try {
            Tache tache = new Tache();
            ResultSet rs = st.executeQuery("select * from Tache where ID = "+X);
            //Mapping Occurrence to Object
            if(rs.next()) {
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setCREATEUR(rs.getString("CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));

                System.out.println("[INFO]-> The task identified by ID : " + tache.getID() + " has been returned successfully!");
            }
            else {
                System.out.println("[INFO]-> The task identified by ID: ' " + tache.getID() + " ' doesn't exist in the TacheS table!");
            }
            return tache;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-Tache]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Tache Element) {
        try {
            int AffectedRow = st.executeUpdate("update Tache " +
                    "set ETAT = '" + Element.getETAT() + "', " +
                    "CREATEUR = '" + Element.getCREATEUR() + "', " +
                    "DESCRIPTION = '" + Element.getDESCRIPTION() + "' " +

                    "where ID = " + Element.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The task identified by [DESCRIPTION: " + Element.getDESCRIPTION() + "| ETAT: " + Element.getETAT() + "] has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The task identified by [DESCRIPTION: " + Element.getDESCRIPTION() + "| ETAT: " + Element.getETAT() + "] is not updated in the tache table!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / UPDATE-User]-> " + e.getMessage());
            return false;

        }
    }

    @Override
    public void save(Tache Element) {
        try {
        PreparedStatement ps = connection.prepareStatement("insert into Tache " +
                "values(null, ?, ?, ?)");

        ps.setString(1, Element.getETAT());
        ps.setString(2, Element.getCREATEUR());
        ps.setString(3, Element.getDESCRIPTION());
        int AffectedRow = ps.executeUpdate();

        if (AffectedRow > 0) {
            System.out.println("[INFO]-> the task [DESCRIPTION: " + Element.getDESCRIPTION() + "| ETAT: " + Element.getETAT() + "] has been inserted successfully in table tache !");
        }
    } catch (SQLException e) {
        System.out.println("[EXCEPTION TRIGGERED / INSERT-Tache]-> " + e.getMessage());
    }

    }

    @Override
    public void delete(Tache tache) {
        try {
            int AffectedRow = st.executeUpdate("delete from Tache where id = " + tache.getID());
            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The task with DESCRIPTION: '" + tache.getDESCRIPTION() + "' | CREATOR: '" + tache.getCREATEUR() + "' has been deleted successfully!");
            } else
                System.out.println("[INFO]-> The task with name DESCRIPTION: '" + tache.getDESCRIPTION() + "' | CREATOR: '" + tache.getCREATEUR() + "' doesn't exist in the table tache!");

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / DELETE-Task]-> " + e.getMessage());
        }
    }

    @Override
    public List<Tache> findByCreateur(String Createur) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Tache where CREATEUR = '"+Createur+"'");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Tache tache = new Tache();
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setCREATEUR(rs.getString("CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));

                listTaches.add(tache);
            }
            if (listTaches.isEmpty()) {
                System.out.println("[INFO]-> The Task TABLE IS EMPTY !!");
            }
            return listTaches;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByEtat]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tache> findByEtat(String Etat) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Tache where ETAT = '"+Etat+"'");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Tache tache = new Tache();
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setCREATEUR(rs.getString("CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));

                listTaches.add(tache);
            }
            if (listTaches.isEmpty()) {
                System.out.println("[INFO]-> The Task TABLE IS EMPTY !!");
            }
            return listTaches;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByEtat]-> " + e.getMessage());
            return null;
        }
    }
}

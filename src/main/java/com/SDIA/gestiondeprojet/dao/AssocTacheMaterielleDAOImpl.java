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
                ATM.setID_GERANT(rs.getLong("ID_GERANT"));

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
    public Long findByTacheMaterielle(Tache tache, Materielle materielle) {
        try {
            ResultSet rs = st.executeQuery("select * from AssocTacheMaterielle where ID_MATERIELLE = "+materielle.getID()+" and ID_TACHE = "+tache.getID());

            if(!rs.next()){
                System.out.println(" >> the materiel [ID: "+materielle.getID()+" ] is not attached to any task yet!");
                return null;
            }
            return rs.getLong(1);

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByTacheMaterielle > AssocTacheMaterielleDAOImpl.class]-> " + e.getMessage());
        }

        return null;
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
                ATM.setID_GERANT(rs.getLong("ID_GERANT"));

                listTaMa.add(ATM);
            }
            if (listTaMa.isEmpty()) {
                System.out.println("[INFO]-> The task with ID+"+tache.getID()+" doesn't exist in table AssocTacheMaterielle !!");
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
                ATM.setID_GERANT(rs.getLong("ID_GERANT"));
                //Ajout de tache
                ATM.setTache(new TacheDAOImpl().findById(ATM.getID_TACHE()));
                //Ajout de materielle
                ATM.setMaterielle(new MaterielleDAOImpl().findById(ATM.getID_MATERIELLE()));

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
            ResultSet rs = st.executeQuery("select * from AssocTacheMaterielle where ID = "+X);
            AssocTacheMaterielle ATM = new AssocTacheMaterielle();
            //Mapping Occurrence to Object
            if(rs.next()) {
                ATM.setID(rs.getLong("ID"));
                ATM.setID_MATERIELLE(rs.getLong("ID_MATERIELLE"));
                ATM.setID_TACHE(rs.getLong("ID_TACHE"));
                ATM.setID_GERANT(rs.getLong("ID_GERANT"));
                ATM.setTache(new TacheDAOImpl().findById(ATM.getID_TACHE()));
                ATM.setMaterielle(new MaterielleDAOImpl().findById(ATM.getID_MATERIELLE()));
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
    public boolean update(AssocTacheMaterielle ATM) {
        try {
            int AffectedRow = st.executeUpdate("update AssocTacheMaterielle " +
                    "set ID_MATERIELLE = '" + ATM.getID_MATERIELLE()+ "' " +

                    "where ID = " + ATM.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The material identified by [TYPE: " + ATM.getMaterielle().getTYPE() + "| ETAT: " + ATM.getMaterielle().getETAT() + "] has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The material identified by [TYPE: " + ATM.getMaterielle().getTYPE() + "| ETAT: " + ATM.getMaterielle().getETAT() + "] is not updated in the materielle table!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / UPDATE-User]-> " + e.getMessage());
            return false;

        }
    }


    @Override
    public AssocTacheMaterielle save(Tache tache, Materielle materielle) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into AssocTacheMaterielle " +
                    "values(null, ?, ? ,?)");

            ps.setLong(1, tache.getID());
            ps.setLong(2, materielle.getID());
            ps.setLong(3, tache.getID_CREATEUR());
            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the new occurence  [ID_TACHE: " + tache.getID() + "| ID_MATERIELLE: " + materielle.getID() + "] has been inserted successfully in table AssocTacheMaterielle !");
                long ID_Row = this.findByTacheMaterielle(tache, materielle);
                AssocTacheMaterielle assTaMa = this.findById(ID_Row);
                return assTaMa;
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT > AssocTacheMaterielleDAOImpl]-> " + e.getMessage());
        }
            return null;
    }

    @Override
    public void delete(AssocTacheMaterielle Element) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from AssocTacheMaterielle " +
                    "where ID = "+Element.getID());

            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the old occurence  [ID_TACHE: " + Element.getID_TACHE() + "| ID_MATERIELLE: " + Element.getID_MATERIELLE() + "] has been deleted successfully from table AssocTacheMaterielle !");
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT > AssocTacheMaterielleDAOImpl]-> " + e.getMessage());
        }

    }

    @Override
    public void save(AssocTacheMaterielle Element) {

    }

}

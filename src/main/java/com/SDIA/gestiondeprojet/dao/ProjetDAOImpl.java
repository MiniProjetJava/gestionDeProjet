package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Projet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDAOImpl implements ProjetDAO{

    Connection connection = SignletonConnexion.getConnection();

    Statement st = connection.createStatement();


    public ProjetDAOImpl() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println("[EXCEPTION TRIGGERED FROM ProjetDAOImpl.class]-> " + e.getMessage());
        }
    }


    @Override
    public List<Projet> findAll() {
        List<Projet> listProjet = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Projet");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Projet projet = new Projet();
                projet.setID(rs.getLong("ID"));
                projet.setID_RESPONSABLE(rs.getLong("ID_RESPONSABLE"));
                projet.setDESCRIPTION(rs.getString("DESCRIPTION"));
                projet.setNBR_INTERVENANTS(rs.getInt("ID_RESPONSABLE"));

                listProjet.add(projet);
            }
            if (listProjet.isEmpty()) {
                System.out.println("[INFO]-> The Project TABLE IS EMPTY !!");
            }
            return listProjet;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAll() > ProjetDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public Projet findById(long X) {
        try {
            Projet projet = new Projet();
            ResultSet rs = st.executeQuery("select * from Projet where ID = "+X);
            //Mapping Occurrence to Object
            if(rs.next()) {
                projet.setID(rs.getLong("ID"));
                projet.setETAT(rs.getString("ETAT"));
                projet.setID_RESPONSABLE(rs.getLong("ID_RESPONSABLE"));
                projet.setDESCRIPTION(rs.getString("DESCRIPTION"));
                projet.setNBR_INTERVENANTS(rs.getInt("NBR_INTERVENANTS"));

            }
            else{
                System.out.println("[INFO]-> The project identified by ID: ' " + X + " ' doesn't exist in the Projet table!");
            }
            return projet;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAll() > ProjetDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Projet Element){
        try {
            int AffectedRow = st.executeUpdate("update Projet " +
                    "set DESCRIPTION = '" + Element.getDESCRIPTION() + "', " +
                    "NBR_INTERVENANTS = " + Element.getNBR_INTERVENANTS() + " " +
                    "ETAT = '" + Element.getETAT() + "' " +

                    "where ID = " + Element.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The project identified by [ID: " + Element.getID() + "| ID_RESPONSABLE: " + Element.getID_RESPONSABLE() + "] has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The project identified by [ID: " + Element.getID() + "| ID_RESPONSABLE: " + Element.getID_RESPONSABLE() + "] is not updated in the project table!");
                return false;
            }

    } catch (SQLException e) {
        System.out.println("[EXCEPTION TRIGGERED / UPDATE-Project]-> " + e.getMessage());
        return false;

    }
}

    @Override
    public void save(Projet Element) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into Materielle " +
                    "values(null, ?, ?, ?)");

            ps.setString(1, Element.getETAT());
            ps.setLong(2, Element.getID_RESPONSABLE());
            ps.setString(3, Element.getDESCRIPTION());
            ps.setInt(4, Element.getNBR_INTERVENANTS());
            int AffectedRow = ps.executeUpdate();

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the project [DESCRIPTION: " + Element.getDESCRIPTION() + "| ID_RESPONSABLE: " + Element.getID_RESPONSABLE() + "] has been inserted successfully in table project !");
            }
        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / INSERT-Project]-> " + e.getMessage());
        }

    }

    @Override
    public void delete(Projet p) {
        try {
            int AffectedRow = st.executeUpdate("delete from Projet where ID = "+p.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> the project [ID: " + p.getID() + "| ID_RESPONSABLE: " + p.getID_RESPONSABLE() + "] has been deleted successfully from table Project !");
            }
            else
                System.out.println("[INFO]-> the project [ID: " + p.getID() + "| ID_RESPONSABLE: " + p.getID_RESPONSABLE() + " doesn't exist in the table Projet!");

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / DELETED-Project]-> " + e.getMessage());
        }

    }

    @Override
    public List<Tache> findAllTasks(Projet projet){
        try {
            TacheDAO tacheDAO = new TacheDAOImpl();
            List<Tache> listTaches = tacheDAO.findByProjet(projet);
            if (listTaches.isEmpty()) {
                System.out.println("[INFO]-> The Project [ID: "+projet.getID()+" doesn't has any task yet !");
            }
            return listTaches;
        }catch (SQLException e){
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAllTasks() > ProjetDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Projet> findByResponsable(Responsable responsable) {
        try {
            List<Projet> listProjets = new ArrayList<>();
            ResultSet rs = st.executeQuery("select * from Projet where ID_RESPONSABLE = "+responsable.getID());
            //Mapping Occurrence to Object
            while(rs.next()) {
                Projet projet = new Projet();
                projet.setID(rs.getLong("ID"));
                projet.setETAT(rs.getString("ETAT"));
                projet.setID_RESPONSABLE(rs.getLong("ID_RESPONSABLE"));
                projet.setDESCRIPTION(rs.getString("DESCRIPTION"));
                projet.setNBR_INTERVENANTS(rs.getInt("NBR_INTERVENANTS"));

                listProjets.add(projet);
            }

            if(listProjets.isEmpty()){
                System.out.println("[INFO]-> The project identified by ID_RESPONSABLE: ' " + responsable.getID() + " ' doesn't exist in the Projet table!");
            }
            return listProjets;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAll() > ProjetDAOImpl.class]-> " + e.getMessage());
            return null;
        }
    }
}
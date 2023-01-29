package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterielleDAOImpl implements MaterielleDAO {
    Connection connection = SignletonConnexion.getConnection();
    Statement st = connection.createStatement();
    public MaterielleDAOImpl() throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println("[EXCEPTION TRIGGERED FROM MaterielleDAOImpl.class]-> " + e.getMessage());
        }
    }


    @Override
    public List<Materielle> findAll() {
        List<Materielle> listMaterielle = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Materielle");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Materielle materielle = new Materielle();
                materielle.setID(rs.getLong("ID"));
                materielle.setTYPE(rs.getString("TYPE"));
                materielle.setMARQUE(rs.getString("MARQUE"));
                materielle.setETAT(rs.getString("ETAT"));
                //new add v1.0
                Tache tache = new TacheDAOImpl().findByMaterielle(materielle);
                Users gerantMaterielle = new UsersDAOImpl().findById(new AssocTacheMaterielleDAOImpl().findByTacheMaterielle(tache , materielle));
                materielle.setGERANT_EMAIL(gerantMaterielle.getMAIL());
                materielle.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                //Partie de chargement des taches utilisant ce materiel :
                List<Tache> listTaches = new ArrayList<>();
                listTaches = this.findAllTasks(materielle);
                materielle.setListTaches(listTaches);
                listMaterielle.add(materielle);
            }
            if (listMaterielle.isEmpty()) {
                System.out.println("[INFO]-> The MaterielleS TABLE IS EMPTY !!");
            }
            return listMaterielle;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-listMaterielle]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public Materielle findById(long X) {
        try {
            Materielle materielle = new Materielle();
            ResultSet rs = st.executeQuery("select * from Materielle where ID = "+X);
            //Mapping Occurrence to Object
            if(rs.next()) {
                materielle.setID(rs.getLong("ID"));
                materielle.setTYPE(rs.getString("TYPE"));
                materielle.setMARQUE(rs.getString("MARQUE"));
                materielle.setETAT(rs.getString("ETAT"));
                //new add v1.0
                Tache tache = new TacheDAOImpl().findByMaterielle(materielle);
                Users gerantMaterielle = new UsersDAOImpl().findById(new AssocTacheMaterielleDAOImpl().findByTacheMaterielle(tache , materielle));
                materielle.setGERANT_EMAIL(gerantMaterielle.getMAIL());
                materielle.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                //Partie de chargement des taches utilisant ce materiel :
                List<Tache> listTaches = new ArrayList<>();
                listTaches = this.findAllTasks(materielle);

                materielle.setListTaches(listTaches);
            }
            else {
                System.out.println("[INFO]-> The material identified by ID: ' " + X + " ' doesn't exist in the Materielle table!");
            }
            return materielle;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-Materielle]-> " + e.getMessage());
            return null;
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean update(Materielle Element) {
        try {
            int AffectedRow = st.executeUpdate("update Materielle " +
                    "set TYPE = '" + Element.getTYPE() + "', " +
                    "MARQUE = '" + Element.getMARQUE() + "', " +
                    "ETAT = '" + Element.getETAT() + "' " +

                    "where ID = " + Element.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The material identified by [TYPE: " + Element.getTYPE() + "| ETAT: " + Element.getETAT() + "] has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The material identified by [TYPE: " + Element.getTYPE() + "| ETAT: " + Element.getETAT() + "] is not updated in the materielle table!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / UPDATE-User]-> " + e.getMessage());
            return false;

        }
    }

    @Override
    public void save(Materielle Element) {
        try {
        PreparedStatement ps = connection.prepareStatement("insert into Materielle " +
                "values(null, ?, ?, ?)");

        ps.setString(1, Element.getTYPE());
        ps.setString(2, Element.getMARQUE());
        ps.setString(3, Element.getETAT());
        int AffectedRow = ps.executeUpdate();

        if (AffectedRow > 0) {
            System.out.println("[INFO]-> the material [TYPE: " + Element.getTYPE() + "| ETAT: " + Element.getETAT() + "] has been inserted successfully in table materielle !");
        }
    } catch (SQLException e) {
        System.out.println("[EXCEPTION TRIGGERED / INSERT-Materielle]-> " + e.getMessage());
    }

    }

    @Override
    public void delete(Materielle materielle) {
        try {
            int AffectedRow = st.executeUpdate("delete from Materielle where id = " + materielle.getID());
            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The material with TYPE: '" + materielle.getTYPE() + "' | ETAT: '" + materielle.getETAT() + "' has been deleted successfully!");
            } else
                System.out.println("[INFO]-> The material with name TYPE: '" + materielle.getTYPE() + "' | ETAT: '" + materielle.getETAT() + "' doesn't exist in the table materielle!");

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / DELETE-Task]-> " + e.getMessage());
        }
    }


    @Override
    public List<Materielle> findByType(String type) {
        try {
            List<Materielle> listMaterielles = new ArrayList<>();
            Materielle materielle = new Materielle();
            ResultSet rs = st.executeQuery("select * from Materielle where TYPE = "+type);
            //Mapping Occurrence to Object
            while (rs.next()) {
                materielle.setID(rs.getLong("ID"));
                materielle.setTYPE(rs.getString("TYPE"));
                materielle.setMARQUE(rs.getString("MARQUE"));
                materielle.setETAT(rs.getString("ETAT"));
                //new add v1.0
                Tache tache = new TacheDAOImpl().findByMaterielle(materielle);
                Users gerantMaterielle = new UsersDAOImpl().findById(new AssocTacheMaterielleDAOImpl().findByTacheMaterielle(tache , materielle));
                materielle.setGERANT_EMAIL(gerantMaterielle.getMAIL());
                materielle.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                //Partie de chargement des taches utilisant ce materiel :
                List<Tache> listTaches = new ArrayList<>();
                listTaches = this.findAllTasks(materielle);
                materielle.setListTaches(listTaches);
                listMaterielles.add(materielle);
            }
            if( listMaterielles.isEmpty() ){
                System.out.println("[INFO]-> The material identified by TYPE: ' " + materielle.getTYPE() + " ' doesn't exist in the Materielle table!");
            }
            return listMaterielles;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByType > MaterielleDAOImpl.java]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Materielle> findByEtat(String Etat) {
        try {
            List<Materielle> listMaterielles = new ArrayList<>();
            Materielle materielle = new Materielle();
            ResultSet rs = st.executeQuery("select * from Materielle where ETAT = "+Etat);
            //Mapping Occurrence to Object
            while (rs.next()) {
                materielle.setID(rs.getLong("ID"));
                materielle.setTYPE(rs.getString("TYPE"));
                materielle.setMARQUE(rs.getString("MARQUE"));
                materielle.setETAT(rs.getString("ETAT"));
                //new add v1.0
                Tache tache = new TacheDAOImpl().findByMaterielle(materielle);
                Users gerantMaterielle = new UsersDAOImpl().findById(new AssocTacheMaterielleDAOImpl().findByTacheMaterielle(tache , materielle));
                materielle.setGERANT_EMAIL(gerantMaterielle.getMAIL());
                materielle.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                //Partie de chargement des taches utilisant ce materiel :
                List<Tache> listTaches = new ArrayList<>();
                listTaches = this.findAllTasks(materielle);

                materielle.setListTaches(listTaches);
                listMaterielles.add(materielle);
            }
            if( listMaterielles.isEmpty() ){
                System.out.println("[INFO]-> The material identified by TYPE: ' " + materielle.getTYPE() + " ' doesn't exist in the Materielle table!");
            }
            return listMaterielles;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByEtat > MaterielleDAOImpl.java]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tache> findAllTasks(Materielle materielle) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs_Taches = connection.createStatement().executeQuery("select ID_TACHE from AssocTacheMaterielle where ID_MATERIELLE = " + materielle.getID());
            while (rs_Taches.next()) {
                Tache tache = new Tache();
                ResultSet rs = connection.createStatement().executeQuery("select * from Tache where ID = " + rs_Taches.getInt(1));
                if(rs.next()){
                    tache.setID(rs.getLong("ID"));
                    tache.setETAT(rs.getString("ETAT"));
                    tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                    tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                    tache.setID_PROJET(rs.getLong("ID_PROJET"));

                }
                listTaches.add(tache);
            }
            if( listTaches.isEmpty() ){
                System.out.println("[INFO]-> The material identified by ID: ' " + materielle.getID() + " ' doesn't have any task available yet!");
            }
            return listTaches;
        }catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAllTasks >  MaterielleDAOImpl.java ]-> " + e.getMessage());
            return null;
        }
    }
}

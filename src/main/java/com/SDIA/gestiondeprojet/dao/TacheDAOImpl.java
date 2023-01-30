package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.*;
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
                tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setID_PROJET(rs.getLong("ID_PROJET"));
                tache.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                tache.setCREATEUR_EMAIL(new UsersDAOImpl().findById(tache.getID_CREATEUR()).getMAIL());
                //Partie de chargement des materielles utilisées par cette tache :
                List<Materielle> listMaterielles = new ArrayList<>();
                listMaterielles = this.findAllMaterielles(tache);

                tache.setListmaterielles(listMaterielles);
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
                tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setID_PROJET(rs.getLong("ID_PROJET"));
                tache.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                tache.setCREATEUR_EMAIL(new UsersDAOImpl().findById(tache.getID_CREATEUR()).getMAIL());
                //Partie de chargement des materielles utilisées par cette tache :
                List<Materielle> listMaterielles = new ArrayList<>();
                listMaterielles = this.findAllMaterielles(tache);

                tache.setListmaterielles(listMaterielles);

                //System.out.println("[INFO]-> The task identified by ID : " + X + " has been returned successfully!");
            }
            else {
                System.out.println("[INFO]-> The task identified by ID: ' " + X + " ' doesn't exist in the TacheS table!");
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
                "values(null, ?, ?, ?, ?)");

        ps.setString(1, Element.getETAT());
        ps.setLong(2, Element.getID_CREATEUR());
        ps.setString(3, Element.getDESCRIPTION());
        ps.setLong(4, Element.getID_PROJET());
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
                System.out.println("[INFO]-> The task with DESCRIPTION: '" + tache.getDESCRIPTION() + "' | CREATOR: '" + tache.getID_CREATEUR() + "' has been deleted successfully!");
            } else
                System.out.println("[INFO]-> The task with name DESCRIPTION: '" + tache.getDESCRIPTION() + "' | CREATOR: '" + tache.getID_CREATEUR() + "' doesn't exist in the table tache!");

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / DELETE-Task]-> " + e.getMessage());
        }
    }

    @Override
    public List<Tache> findByCreateur(Users Createur) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Tache where ID_CREATEUR = "+Createur.getID()+"");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Tache tache = new Tache();
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setID_PROJET(rs.getLong("ID_PROJET"));
                tache.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                tache.setCREATEUR_EMAIL(new UsersDAOImpl().findById(tache.getID_CREATEUR()).getMAIL());
                //Partie de chargement des materielles utilisées par cette tache :
                List<Materielle> listMaterielles = new ArrayList<>();
                listMaterielles = this.findAllMaterielles(tache);

                tache.setListmaterielles(listMaterielles);
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
                tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setID_PROJET(rs.getLong("ID_PROJET"));
                tache.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                tache.setCREATEUR_EMAIL(new UsersDAOImpl().findById(tache.getID_CREATEUR()).getMAIL());
                //Partie de chargement des materielles utilisées par cette tache :
                List<Materielle> listMaterielles = new ArrayList<>();
                listMaterielles = this.findAllMaterielles(tache);

                tache.setListmaterielles(listMaterielles);
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
    public List<Materielle> findAllMaterielles(Tache tache) {
        List<Materielle> listMaterielles = new ArrayList<>();
        try {
            ResultSet rs_materielles = connection.createStatement().executeQuery("select ID_MATERIELLE from AssocTacheMaterielle where ID_TACHE = " + tache.getID());
            while (rs_materielles.next()) {
                Materielle materielle = new Materielle();
                ResultSet rs = connection.createStatement().executeQuery("select * from materielle where ID = " + rs_materielles.getInt(1));
                if (rs.next()) {
                    materielle.setID(rs.getLong("ID"));
                    materielle.setTYPE(rs.getString("TYPE"));
                    materielle.setMARQUE(rs.getString("MARQUE"));
                    materielle.setETAT(rs.getString("ETAT"));

                    Users gerantMaterielle = new UsersDAOImpl().findById(tache.getID_CREATEUR());
                    materielle.setGERANT_EMAIL(gerantMaterielle.getMAIL());
                    materielle.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                    listMaterielles.add(materielle);
                }
            }
            if( listMaterielles.isEmpty() ){
                System.out.println("[INFO]-> The task identified by ID: ' " + tache.getID() + " ' doesn't have any material available yet!");
            }
            return listMaterielles;
        }catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findAllTasks >  TacheDAOImpl.java ]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public Tache findByMaterielle(Materielle materielle) {
        try {
            ResultSet rs_materielles = st.executeQuery("select ID_TACHE from AssocTacheMaterielle where ID_MATERIELLE = " + materielle.getID());
            if(rs_materielles.next()) {
                Tache tache = this.findById(rs_materielles.getLong(1));
                return tache;
            }
        }catch (SQLException e){
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByMaterielle >  TacheDAOImpl.java ]-> " + e.getMessage());
            return null;
        }
        return null;
    }

    public List<Tache> findByProjet(Projet projet) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Tache where ID_PROJET = '"+projet.getID()+"'");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Tache tache = new Tache();
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setID_PROJET(rs.getLong("ID_PROJET"));
                tache.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                tache.setCREATEUR_EMAIL(new UsersDAOImpl().findById(tache.getID_CREATEUR()).getMAIL());
                //Partie de chargement des materielles utilisées par cette tache :
                List<Materielle> listMaterielles;
                listMaterielles = this.findAllMaterielles(tache);

                tache.setListmaterielles(listMaterielles);
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

    public List<Tache> findByDescription(String desc) {
        List<Tache> listTaches = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Tache where DESCRIPTION like '%"+desc+"%'");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Tache tache = new Tache();
                tache.setID(rs.getLong("ID"));
                tache.setETAT(rs.getString("ETAT"));
                tache.setID_CREATEUR(rs.getLong("ID_CREATEUR"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setID_PROJET(rs.getLong("ID_PROJET"));
                tache.setPROJET_DESCRIPTION(new ProjetDAOImpl().findById(tache.getID_PROJET()).getDESCRIPTION());
                tache.setCREATEUR_EMAIL(new UsersDAOImpl().findById(tache.getID_CREATEUR()).getMAIL());
                //Partie de chargement des materielles utilisées par cette tache :
                List<Materielle> listMaterielles = new ArrayList<>();
                listMaterielles = this.findAllMaterielles(tache);

                tache.setListmaterielles(listMaterielles);
                listTaches.add(tache);
            }
            if (listTaches.isEmpty()) {
                System.out.println("[INFO]-> The Task TABLE IS EMPTY !!");
            }
            return listTaches;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByDesc]-> " + e.getMessage());
            return null;
        }
    }
}

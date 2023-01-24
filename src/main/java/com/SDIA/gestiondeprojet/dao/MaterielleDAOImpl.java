package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

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

                //Partie de chargement des taches utilisant ce materiel :
                List<Tache> listTaches = new ArrayList<>();
                    try {
                        ResultSet rs_Taches = st.executeQuery("select ID_TACHE from AssocTacheMaterielle where ID_MATERIELLE = " + materielle.getID());
                        while (rs_Taches.next()) {
                            TacheDAO tacheDAO = new TacheDAOImpl();
                            Tache tache = tacheDAO.findById(rs_Taches.getInt(1));
                            listTaches.add(tache);
                        }
                    }catch (SQLException e) {
                        System.out.println("[EXCEPTION TRIGGERED / SELECT-listTaches in file MaterielleDAOImpl ( findall() )]-> " + e.getMessage());
                    }
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
                materielle.setETAT(rs.getString("ETAT"));
                materielle.setCREATEUR(rs.getString("CREATEUR"));
                materielle.setDESCRIPTION(rs.getString("DESCRIPTION"));

                System.out.println("[INFO]-> The task identified by ID : " + materielle.getID() + " has been returned successfully!");
            }
            else {
                System.out.println("[INFO]-> The task identified by ID: ' " + materielle.getID() + " ' doesn't exist in the MaterielleS table!");
            }
            return materielle;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-Materielle]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Materielle Element) {
        try {
            int AffectedRow = st.executeUpdate("update Materielle " +
                    "set ETAT = '" + Element.getETAT() + "', " +
                    "CREATEUR = '" + Element.getCREATEUR() + "', " +
                    "DESCRIPTION = '" + Element.getDESCRIPTION() + "' " +

                    "where ID = " + Element.getID());

            if (AffectedRow > 0) {
                System.out.println("[INFO]-> The task identified by [DESCRIPTION: " + Element.getDESCRIPTION() + "| ETAT: " + Element.getETAT() + "] has been updated successfully!");
                return true;
            } else {
                System.out.println("[INFO]-> The task identified by [DESCRIPTION: " + Element.getDESCRIPTION() + "| ETAT: " + Element.getETAT() + "] is not updated in the materielle table!");
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

        ps.setString(1, Element.getETAT());
        ps.setString(2, Element.getCREATEUR());
        ps.setString(3, Element.getDESCRIPTION());
        int AffectedRow = ps.executeUpdate();

        if (AffectedRow > 0) {
            System.out.println("[INFO]-> the task [DESCRIPTION: " + Element.getDESCRIPTION() + "| ETAT: " + Element.getETAT() + "] has been inserted successfully in table materielle !");
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
                System.out.println("[INFO]-> The task with DESCRIPTION: '" + materielle.getDESCRIPTION() + "' | CREATOR: '" + materielle.getCREATEUR() + "' has been deleted successfully!");
            } else
                System.out.println("[INFO]-> The task with name DESCRIPTION: '" + materielle.getDESCRIPTION() + "' | CREATOR: '" + materielle.getCREATEUR() + "' doesn't exist in the table materielle!");

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / DELETE-Task]-> " + e.getMessage());
        }
    }

    @Override
    public List<Materielle> findByCreateur(String Createur) {
        List<Materielle> listMaterielles = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Materielle where CREATEUR = '"+Createur+"'");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Materielle materielle = new Materielle();
                materielle.setID(rs.getLong("ID"));
                materielle.setETAT(rs.getString("ETAT"));
                materielle.setCREATEUR(rs.getString("CREATEUR"));
                materielle.setDESCRIPTION(rs.getString("DESCRIPTION"));

                listMaterielles.add(materielle);
            }
            if (listMaterielles.isEmpty()) {
                System.out.println("[INFO]-> The Task TABLE IS EMPTY !!");
            }
            return listMaterielles;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByEtat]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Materielle> findByType(String Createur) {
        return null;
    }

    @Override
    public List<Materielle> findByEtat(String Etat) {
        List<Materielle> listMaterielles = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("select * from Materielle where ETAT = '"+Etat+"'");
            //Mapping Occurrence to Object
            while(rs.next()) {
                Materielle materielle = new Materielle();
                materielle.setID(rs.getLong("ID"));
                materielle.setETAT(rs.getString("ETAT"));
                materielle.setCREATEUR(rs.getString("CREATEUR"));
                materielle.setDESCRIPTION(rs.getString("DESCRIPTION"));

                listMaterielles.add(materielle);
            }
            if (listMaterielles.isEmpty()) {
                System.out.println("[INFO]-> The Task TABLE IS EMPTY !!");
            }
            return listMaterielles;

        } catch (SQLException e) {
            System.out.println("[EXCEPTION TRIGGERED / SELECT-findByEtat]-> " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Materielle> findAllTasks(Materielle materielle) {
        return null;
    }
}

package com.SDIA.gestiondeprojet;

import com.SDIA.gestiondeprojet.dao.MaterielleDAOImpl;
import com.SDIA.gestiondeprojet.dao.SignletonConnexion;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.metier.TacheMetier;
import com.SDIA.gestiondeprojet.metier.TacheMetierImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.util.List;

public class HelloController {
    public static void main(String[] args) {
        Connection cnx = SignletonConnexion.getConnection();
        if( cnx == null){
            System.out.println(">>>> Connection failed !");
            System.exit(-9999);
        }

        System.out.println(">>>> Connection success !");

        try{
            List<Materielle> lM = new TacheMetierImpl().selectMateriellesDeTache(new TacheMetierImpl().selectTacheByID(1)) ;

            Materielle t = lM.get(0);
            System.out.println("*** "+t.getGERANT_EMAIL()+"| "+t.getPROJET_DESCRIPTION());



        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
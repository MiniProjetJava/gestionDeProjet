package com.SDIA.gestiondeprojet;

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
            TacheMetier tm = new TacheMetierImpl();
            List<Tache> lT = tm.selectAll();

                for(Materielle m :tm.selectMateriellesDeTache(lT.get(0)) ){
                    System.out.println(m);

            }



        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
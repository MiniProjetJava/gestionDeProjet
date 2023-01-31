package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.ProjetMetierImpl;
import com.SDIA.gestiondeprojet.metier.TacheMetierImpl;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AcceuilResponsableControllers implements Initializable {
    @FXML
    private Label nom_responsable;
    @FXML
    private Label nombre_projet;
    @FXML
    private Label nombre_tache;
    SingletonData data = SingletonData.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Users users = null;
        try {
            users = new UsersMetier().findByMail(data.getMail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nom_responsable.setText(users.getPRENOM() + " " + users.getNOM());
        try {
            int nombre = new ProjetMetierImpl().nombreProjet(users);
            nombre_projet.setText(nombre + "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            int tache = new TacheMetierImpl().nombreTache(users);
            nombre_tache.setText(tache + "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

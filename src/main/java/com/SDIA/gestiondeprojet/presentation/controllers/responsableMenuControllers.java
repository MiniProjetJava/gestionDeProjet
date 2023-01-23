package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.HelloApplication;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class responsableMenuControllers implements Initializable {
    @FXML
    private AnchorPane anchorPanePage;

    @FXML
    private Label lab;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //UsersMetier usersMetier = new UsersMetier();
        Users users = new Users();
        System.out.println(users.getNom());
        /*loginControllers login = new loginControllers();
        try {
            Users users = login.showPersonnalInformation();
            lab.setText(users.getNom());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }
    public void gestionDesIntervenants(ActionEvent actionEvent) throws IOException {
        anchorPanePage.getScene().getWindow().hide();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("responsableProjet/gestionIntervenantInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Gestion des Intervenants");
        stage.setScene(scene);
        stage.show();
    }


    public void personnalInformation(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("responsableProjet/gestionInformationPersonnel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Gestion des Informations personnels");
        stage.setScene(scene);
        stage.show();
    }
}

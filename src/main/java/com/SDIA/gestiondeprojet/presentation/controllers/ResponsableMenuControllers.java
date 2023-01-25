package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResponsableMenuControllers implements Initializable {
    @FXML
    private AnchorPane anchorPaneMenuResponsable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void gestionDesIntervenants(ActionEvent actionEvent) throws IOException {
        //anchorPaneMenuResponsable.getScene().getWindow().hide();
        //anchorPaneMenuResponsable.getScene().getWindow().hide();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("responsableProjet/gestionIntervenantInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Gestion des Intervenants");
        stage.setScene(scene);
        stage.show();
    }
}

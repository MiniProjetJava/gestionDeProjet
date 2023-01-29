package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.MainApplication;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DeconnexionControllers {
    public void deconnection(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION MESSAGE");
        alert.setHeaderText("Vous voulez vraiment vous déconnectez ?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Intervenant/intervenantAcceuil.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("Intervenant");
            stage.setScene(scene);
            stage.show();

        }
    }
}

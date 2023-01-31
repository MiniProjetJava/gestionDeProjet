package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResponsableMenuControllers implements Initializable {

    @FXML
    private BorderPane borderPaneAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void deconnection(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION MESSAGE");
        alert.setHeaderText("Vous voulez vraiment vous déconnectez ?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            borderPaneAll.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Connection/login.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("LOGIN");
            stage.setScene(scene);
            stage.show();

        }
    }
}

package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.MainApplication;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginControllers implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private Button Btn_cancel;
    @FXML
    private Button Btn_login;
    @FXML
    private PasswordField password;
    @FXML
    private BorderPane borderPaneLOGIN;

    public void loginButton(ActionEvent actionEvent) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(email.getText().isEmpty() || password.getText().isEmpty()){
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : the fields of 'EMAIL', 'PASSWORD' are empty.");
            alert.setContentText("Solution : Please fill the fields above with the correct data and then press the button [LOGIN].");
            alert.showAndWait();
        }
        else {
            String mail_text = email.getText();
            String password_text = password.getText();
            Users user = new Users(mail_text, password_text);
            UsersMetier usersMetier = new UsersMetier();

            if (usersMetier.checkUsers(user) == 1) {
                borderPaneLOGIN.getScene().getWindow().hide();
                String mail = email.getText();
                SingletonData data = SingletonData.getInstance();
                data.setMail(mail);

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("responsableProjet/responsable.fxml"));


                Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                stage.setTitle("Responsable");
                stage.setScene(scene);
                stage.show();
            } else if (usersMetier.checkUsers(user) == 2) {
                borderPaneLOGIN.getScene().getWindow().hide();
                String mail = email.getText();
                SingletonData data = SingletonData.getInstance();
                data.setMail(mail);

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Intervenant/intervenantAcceuil.fxml"));


                Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                stage.setTitle("Intervenant");
                stage.setScene(scene);
                stage.show();
            } else{
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText("ERROR : Email ou mot de passe sont invalide.");
                alert.showAndWait();
            }
        }
    }

    public void cancelButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION MESSAGE");
        alert.setHeaderText("Are you sure you want to leave? Press [OK]");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            borderPaneLOGIN.getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

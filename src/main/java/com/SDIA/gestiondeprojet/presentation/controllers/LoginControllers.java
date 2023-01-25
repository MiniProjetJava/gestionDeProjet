package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.HelloApplication;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginControllers implements Initializable {
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane anchorPaneInterface;

    public void loginButton(ActionEvent actionEvent) throws SQLException, IOException {
        String mail_text = mail.getText();
        String password_text = password.getText();
        Users users = new Users(mail_text,password_text);
        UsersMetier usersMetier = new UsersMetier();
        usersMetier.checkUsers(users);
        if (usersMetier.checkUsers(users) == 1){
            anchorPaneInterface.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("responsableProjet/responsable.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("Responsable");
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

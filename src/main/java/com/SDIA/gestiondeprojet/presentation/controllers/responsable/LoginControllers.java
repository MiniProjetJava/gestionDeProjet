package com.SDIA.gestiondeprojet.presentation.controllers.responsable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllers implements Initializable {
    @FXML
    private AnchorPane anchorPaneInterface;

    @FXML
    private Button login;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButton(ActionEvent actionEvent) {
        String mail_text = mail.getText();
        String password_text = password.getText();
    }
}

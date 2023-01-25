package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.HelloApplication;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.dao.entities.UsersTest;
import com.SDIA.gestiondeprojet.metier.UsersTestMetier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginControllers implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private AnchorPane anchorPaneInterface;
    @FXML
    private Label lab;

    public loginControllers() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Users users = null;
        try {
            //users = showPersonnalInformation(nom.getText());
            //System.out.println(users.getNom());
            /*if(users.getNom() != null){
                lab.setText(users.getNom());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(users);
    }

    public void loginButton(ActionEvent actionEvent) throws SQLException, IOException {
        String nom_text = nom.getText();
        String password_text = password.getText();
        UsersTest usersTest = new UsersTest(nom_text, password_text);

        UsersTestMetier usersTestMetier = new UsersTestMetier();
        int resultat = usersTestMetier.checkUsers(usersTest);

        if(resultat == 1){
            anchorPaneInterface.getScene().getWindow().hide();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("responsableProjet/responsable.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Users users = showPersonnalInformation(nom_text);
            //System.out.println(users);

    //        ResponsableInfoPersonnelControllers responsable = fxmlLoader.getController();
//            System.out.println(responsable.seeUsers(users));

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        }
    }

    public Users showPersonnalInformation(String mail) throws SQLException {
        UsersMetier usersMetier = new UsersMetier();
        return usersMetier.findByMail(mail);
    }

    //public void
}

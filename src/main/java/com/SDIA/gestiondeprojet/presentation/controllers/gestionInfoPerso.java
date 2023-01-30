package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class gestionInfoPerso implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField mail;
    @FXML
    private TextField telephone;
    @FXML
    private TextField password;
    @FXML
    private Label id;

    SingletonData data = SingletonData.getInstance();

    public gestionInfoPerso() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Users users = new UsersMetier().findByMail(data.getMail());
            id.setText(String.valueOf(users.getID()));
            nom.setText(users.getNOM());
            prenom.setText(users.getPRENOM());
            adresse.setText(users.getADRESSE());
            telephone.setText(users.getTELEPHONE());
            password.setText(users.getPASSWORD());
            //System.out.println(users);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mail.setText(data.getMail());
    }

    public void editInfo(ActionEvent actionEvent) throws SQLException {
        Users users = new Users();
        users.setNOM(nom.getText());
        users.setPRENOM(prenom.getText());
        users.setADRESSE(adresse.getText());
        users.setTELEPHONE(telephone.getText());
        users.setMAIL(mail.getText());
        users.setPASSWORD(password.getText());

        Users user = new UsersMetier().findByMail(data.getMail());
        users.setID(user.getID());

        new UsersMetier().update(users);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMATION MESSAGE");
        alert.setHeaderText("");
        alert.setContentText("INFORMATION MODIFIÉE AVEC SUCCES");
        alert.showAndWait();
    }

}

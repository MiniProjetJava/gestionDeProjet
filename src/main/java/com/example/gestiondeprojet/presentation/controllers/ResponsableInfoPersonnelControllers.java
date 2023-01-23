package com.example.gestiondeprojet.presentation.controllers;

import com.example.gestiondeprojet.entities.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResponsableInfoPersonnelControllers implements Initializable {
    @FXML
    private Label lab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Users seeUsers(Users users){
        //System.out.println(users);
        lab.setText(users.getNom());
        return users;
    }
}

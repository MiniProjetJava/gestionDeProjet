package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class gestionTacheControllers implements Initializable {

    @FXML
    Button addBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    TextField tacheEtat;
    @FXML
    TextArea tacheDesc;
    @FXML
    TextField textField;
    @FXML
    private TableView<Tache> tableCategory;
    @FXML
    private ObservableList<Tache> observableTache = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC
    @FXML
    private TableColumn<Tache, Long> col_ID;
    @FXML
    private TableColumn<Tache, String> col_ETAT;
    @FXML
    private TableColumn<Tache, Long> col_ID_CREATEUR;
    @FXML
    private TableColumn<Tache, String> col_DESCRIPTION;
    @FXML
    private TableColumn<Tache, Long> col_ID_PROJET;
    @FXML
    private ComboBox<Projet> combBoxProjet;
    @FXML
    private ObservableList<Projet> observableProjet = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

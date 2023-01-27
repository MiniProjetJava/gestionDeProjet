package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.ProjetDAO;
import com.SDIA.gestiondeprojet.dao.ProjetDAOImpl;
import com.SDIA.gestiondeprojet.dao.TacheDAO;
import com.SDIA.gestiondeprojet.dao.TacheDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.metier.ProjetMetier;
import com.SDIA.gestiondeprojet.metier.ProjetMetierImpl;
import com.SDIA.gestiondeprojet.metier.TacheMetier;
import com.SDIA.gestiondeprojet.metier.TacheMetierImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
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
    TextField searchField;
    @FXML
    private TableView<Tache> tableTache;
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
    private ComboBox<Projet> comboBoxProjet;
    @FXML
    private ObservableList<Projet> observableProjet = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC

    private TacheMetier tacheMetier = new TacheMetierImpl();
    private ProjetMetier projetMetier = new ProjetMetierImpl();

    public gestionTacheControllers() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                //Rendre tableView éditable
                tableTache.setEditable(true);
                //Ajouter observableList to tableView
                tableTache.setItems(observableTache);
                comboBoxProjet.setItems(observableProjet);
                //Definition des attributs à afficher pour chaque colonne
                col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                col_ETAT.setCellValueFactory(new PropertyValueFactory<>("ETAT"));
                col_ID_CREATEUR.setCellValueFactory(new PropertyValueFactory<>("ID_CREATEUR"));
                col_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION"));
                col_ID_PROJET.setCellValueFactory(new PropertyValueFactory<>("ID_PROJET"));
                //Remplir TableView par une liste des taches à partir ObservableList
                observableTache.addAll(tacheMetier.selectAll());
                //Charger comBox
                //Methode 1: combBoxCate.getItems().addAll(categoryService.selectListCategory());
                observableProjet.addAll(projetMetier.selectAll());
                //Definir le traitement de bouton searchBtn
                searchField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        observableTache.clear();
                        observableTache.addAll(tacheMetier.selectTacheByCreateur(newValue));
                    }
                });

                //remplir les champs avec la ligne slectionnée
                tableTache.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tache>() {
                    @Override
                    public void changed(ObservableValue<? extends Tache> observableValue, Tache oldObject, Tache newObject) {
                        if (newObject != null) {
                            {
                                tacheEtat.setText(newObject.getETAT());
                                tacheDesc.setText(newObject.getDESCRIPTION());
                                //pour charger comBox
                                comboBoxProjet.setPromptText(projetMetier.selectByID(newObject.getID_PROJET()).getDESCRIPTION());

                            }
                        }
                    }
                });
    }
}

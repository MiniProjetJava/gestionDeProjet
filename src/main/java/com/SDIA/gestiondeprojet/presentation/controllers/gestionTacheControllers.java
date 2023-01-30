package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.ProjetDAO;
import com.SDIA.gestiondeprojet.dao.ProjetDAOImpl;
import com.SDIA.gestiondeprojet.dao.TacheDAO;
import com.SDIA.gestiondeprojet.dao.TacheDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.*;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class gestionTacheControllers implements Initializable {

    private String email = SingletonData.getInstance().getMail();
    private TacheMetier tacheMetier = new TacheMetierImpl();
    private ProjetMetier projetMetier = new ProjetMetierImpl();
    private Users responsable = new UsersMetier().findByMail(email);
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
    private TableColumn<Tache, String> col_ID_CREATEUR;
    @FXML
    private TableColumn<Tache, String> col_DESCRIPTION;
    @FXML
    private TableColumn<Tache, String> col_ID_PROJET;
    @FXML
    private ComboBox<Projet> comboBoxProjet;
    @FXML
    private ObservableList<Projet> observableProjet = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC


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
        //col_ID_CREATEUR.setCellValueFactory(new PropertyValueFactory<>("ID_CREATEUR"));
        col_ID_CREATEUR.setCellValueFactory(new PropertyValueFactory<>("CREATEUR_EMAIL"));
        col_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION"));
        //col_ID_PROJET.setCellValueFactory(new PropertyValueFactory<>("PROJET_DESCRIPTION"));
        col_ID_PROJET.setCellValueFactory(new PropertyValueFactory<>("PROJET_DESCRIPTION"));
        //Remplir TableView par une liste des taches à partir ObservableList
        observableTache.addAll(tacheMetier.selectTacheByCreateur(responsable));
        //Charger comBox
        //Methode 1: combBoxCate.getItems().addAll(categoryService.selectListCategory());
        observableProjet.addAll(projetMetier.selectByReponsable(responsable));

        //Definir le traitement de bouton searchBtn
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                observableTache.clear();
                observableTache.addAll(tacheMetier.selectTacheByDesc(newValue));
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
                        comboBoxProjet.setValue(projetMetier.selectByID(newObject.getID_PROJET()));
                    }
                }
            }
        });
    }


    public void addTache() {

        if (comboBoxProjet.getValue() == null || tacheEtat.getText().isEmpty() || tacheDesc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : the fields of 'ETAT', 'DESCRIPTION' and 'Projet' are empty.");
            alert.setContentText("Solution : Please fill the fields above and then press the button [Add].");
            alert.showAndWait();
        } else {
            Tache tache = new Tache();
            tache.setETAT(tacheEtat.getText());
            tache.setDESCRIPTION(tacheDesc.getText());
            tache.setID_CREATEUR(responsable.getID());  //Charger ID_CREATEUR de cette tache qui est le responsable de projet
            Projet projet = comboBoxProjet.getValue();
            tache.setID_PROJET(projet.getID());

            tacheMetier.insertTache(tache);
            observableTache.add(tache);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ADD MESSAGE");
            alert.setContentText("You have successfully added new task");
            alert.show();
        }

    }

    public void updateTache() {

        if (tacheEtat.getText().isEmpty() || tacheDesc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : the fields of 'ETAT', 'DESCRIPTION' and 'Projet' are empty.");
            alert.setContentText("Solution : Please select the task you want to update ,then fill the fields above and then press the button [Add].");
            alert.showAndWait();
        } else {
            Tache tache = tableTache.getSelectionModel().getSelectedItem();
            tache.setETAT(tacheEtat.getText());
            tache.setDESCRIPTION(tacheDesc.getText());
            tache.setID_PROJET(comboBoxProjet.getValue().getID());
            //tache.setID_CREATEUR(responsable.getID());  //Charger ID_CREATEUR de cette tache qui est le responsable de projet

            try {
                tacheMetier.updateTache(tache);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            int indexOldTask = observableTache.indexOf(tableTache.getSelectionModel().getSelectedItem());
            observableTache.set(indexOldTask, tache);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UPDATE MESSAGE");

            alert.setContentText("You have successfully updated the task [ID :" + tache.getID() + " ]");
            alert.show();
        }

    }

    public void deleteTache() {

        if (tableTache.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : No occurrence selected from the table.");
            alert.setContentText("Solution : Please select the task you want to delete from the table below and press [delete].");
            alert.showAndWait();
        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION OF DELETING TASK");
            alert.setHeaderText("You are trying to delete the task [ID :" + tableTache.getSelectionModel().getSelectedItem().getID() + " ]");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                try {
                    tacheMetier.delete(tableTache.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                observableTache.remove(tableTache.getSelectionModel().getSelectedItem());
            }
        }

    }

    public void refreshTable(){
        observableTache.clear();
        observableTache.addAll(tacheMetier.selectAll());
    }
}


package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.AssocTacheMaterielleDAO;
import com.SDIA.gestiondeprojet.dao.AssocTacheMaterielleDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.*;
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

public class gestionMaterielleControllers implements Initializable {

    private String email = SingletonData.getInstance().getMail();
    private TacheMetier tacheMetier = new TacheMetierImpl();
    private MaterielleMetier materielleMetier = new MaterielleMetierImpl();
    private AssocTacheMaterielleDAO TaMaMetier = new AssocTacheMaterielleDAOImpl();
    private ProjetMetier projetMetier = new ProjetMetierImpl();
    private Users responsable = new UsersMetier().findByMail(email);

    @FXML
    TextField projetNom;
    @FXML
    TextField searchField;
    @FXML
    private TableView<AssocTacheMaterielle> tableTacheMaterielle;
    @FXML
    private ObservableList<AssocTacheMaterielle> observableTM = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC
    @FXML
    private TableColumn<AssocTacheMaterielle, Long> col_ID;
    @FXML
    private TableColumn<AssocTacheMaterielle, String> col_TACHE;
    @FXML
    private TableColumn<AssocTacheMaterielle, String> col_MATERIELLE;
    @FXML
    private ComboBox<Materielle> comboBoxMaterielle;
    @FXML
    private ObservableList<Materielle> observableMaterielle = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC
    @FXML
    private ComboBox<Tache> comboBoxTache;
    @FXML
    private ObservableList<Tache> observableTache = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC



    public gestionMaterielleControllers() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Rendre tableView éditable
        tableTacheMaterielle.setEditable(true);
        //Ajouter observableList to tableView
        tableTacheMaterielle.setItems(observableTM);
        comboBoxMaterielle.setItems(observableMaterielle);
        comboBoxTache.setItems(observableTache);
        //Definition des attributs à afficher pour chaque colonne
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_TACHE.setCellValueFactory(new PropertyValueFactory<>("tache"));
        col_MATERIELLE.setCellValueFactory(new PropertyValueFactory<>("materielle"));
        //col_ID_PROJET.setCellValueFactory(new PropertyValueFactory<>("PROJET_DESCRIPTION"));
        //col_ID_PROJET.setCellValueFactory(new PropertyValueFactory<>("PROJET_DESCRIPTION"));
        //Remplir TableView par une liste des taches à partir ObservableList
        observableTM.addAll(TaMaMetier.findAll());
        //Charger comBox
        //Methode 1: combBoxCate.getItems().addAll(categoryService.selectListCategory());
        observableMaterielle.addAll(materielleMetier.selectAll());
        observableTache.addAll(tacheMetier.selectAll());

        //Definir le traitement de bouton searchBtn
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                observableTM.clear();
                //observableTM.addAll(TaMaMetier.f);
            }
        });

        //remplir les champs avec la ligne slectionnée
        tableTacheMaterielle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AssocTacheMaterielle>() {
            @Override
            public void changed(ObservableValue<? extends AssocTacheMaterielle> observableValue, AssocTacheMaterielle oldObject, AssocTacheMaterielle newObject) {
                if (newObject != null) {
                    {
                        Tache tache = tableTacheMaterielle.getSelectionModel().getSelectedItem().getTache();

                        comboBoxTache.setValue(tache);
                        projetNom.setText(tacheMetier.selectTacheByID(newObject.getID_TACHE()).getPROJET_DESCRIPTION());
                        //pour charger comBox
                        comboBoxMaterielle.setValue(newObject.getMaterielle());

                    }
                }
            }
        });
    }


    public void addMaterielle() {

        if (comboBoxMaterielle.getSelectionModel().getSelectedItem() == null || comboBoxTache.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : the fields of 'Tache' and 'Materielle' are empty.");
            alert.setContentText("Solution : Please fill the fields above and then press the button [Add].");
            alert.showAndWait();
        } else {
            Tache tache = comboBoxTache.getSelectionModel().getSelectedItem();
            Materielle materielle = comboBoxMaterielle.getSelectionModel().getSelectedItem();

            AssocTacheMaterielle assTaMa = TaMaMetier.save(tache, materielle);
            if(assTaMa == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText("ERROR : You have selected the same task & material that already exist in the table !");
                alert.setContentText("Solution : Check if the new occurrence you will create doesn't exist and then press the button [Add].");
                alert.show();
            }
            else {
                observableTM.add(assTaMa);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD MESSAGE");
                alert.setContentText("You have successfully added new task");
                alert.show();
            }
        }

    }

    public void updateMaterielle() {

        if (comboBoxMaterielle.getSelectionModel().getSelectedItem() == null || comboBoxTache.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : the fields of 'Tache' and 'Materielle' are empty.");
            alert.setContentText("Solution : Please select the occurrence you want to modify and then press the button [Update].");
            alert.showAndWait();
        } else {
            Tache tache = comboBoxTache.getSelectionModel().getSelectedItem();
            Materielle materielle = comboBoxMaterielle.getSelectionModel().getSelectedItem();
            AssocTacheMaterielle assTaMa = tableTacheMaterielle.getSelectionModel().getSelectedItem();
            assTaMa.setID_MATERIELLE(materielle.getID());
            try {
                TaMaMetier.update(assTaMa);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            int indexOldAssTaMa = observableTM.indexOf(tableTacheMaterielle.getSelectionModel().getSelectedItem());
            observableTM.set(indexOldAssTaMa, assTaMa);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ADD MESSAGE");
            alert.setContentText("You have successfully updated the materiel");
            alert.show();
        }
    }

    public void deleteMaterielle() {
        if (tableTacheMaterielle.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : No occurrence selected from the table.");
            alert.setContentText("Solution : Please select the task you want to delete from the table below and press [delete].");
            alert.showAndWait();
        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION OF DELETING the row");
            alert.setHeaderText("You are trying to delete the row [ID :" + tableTacheMaterielle.getSelectionModel().getSelectedItem().getID() + " ]");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                try {
                    TaMaMetier.delete(tableTacheMaterielle.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                observableTM.remove(tableTacheMaterielle.getSelectionModel().getSelectedItem());
            }
        }

    }

    public void refreshTable(){
        observableTM.clear();
        observableTM.addAll(TaMaMetier.findAll());
        observableTache.clear();
        observableTache.addAll(tacheMetier.selectAll());
        observableMaterielle.clear();
        observableMaterielle.addAll(materielleMetier.selectAll());
    }
}


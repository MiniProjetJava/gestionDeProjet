package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.AssocTacheUserDAO;
import com.SDIA.gestiondeprojet.dao.AssocTacheUserDAOImpl;
import com.SDIA.gestiondeprojet.dao.UsersDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.AssocTacheUser;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class gestionTacheUserControllers implements Initializable {
    private String email = SingletonData.getInstance().getMail();
    private TacheMetier tacheMetier = new TacheMetierImpl();
    private UsersMetier usersMetier = new UsersMetier();
    private Users responsable = new UsersMetier().findByMail(email);

    private AssocTacheUserDAO tacheUserMetier = new AssocTacheUserDAOImpl();
    @FXML
    private TableColumn<AssocTacheUser, Long> col_ID;

    @FXML
    private TableColumn<AssocTacheUser, Date> col_DEADLINE;

    @FXML
    private TableColumn<AssocTacheUser, Tache> col_TACHE;

    @FXML
    private TableColumn<AssocTacheUser, String> col_USER_EMAIL;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField projetNom;

    @FXML
    private ComboBox<Materielle> comboBoxMaterielle;
    private ObservableList<Materielle> observableMaterielle = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC

    @FXML
    private ComboBox<Users> comboBoxIntervenants;
    private ObservableList<Users> observableIntervenants = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC

    @FXML
    private TableView<AssocTacheUser> tableTacheUser;
    private ObservableList<AssocTacheUser> observableTU = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC

    @FXML
    private ComboBox<Tache> comboBoxTache;
    private ObservableList<Tache> observableTache = FXCollections.observableArrayList(); //Modele qui appartient à la couche MVC

    public gestionTacheUserControllers() throws SQLException {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Ajouter observableList to tableView
        tableTacheUser.setItems(observableTU);
        comboBoxTache.setItems(observableTache);
        comboBoxMaterielle.setItems(observableMaterielle);
        comboBoxIntervenants.setItems(observableIntervenants);
        //Definition des attributs à afficher pour chaque colonne
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_TACHE.setCellValueFactory(new PropertyValueFactory<>("TACHE"));
        col_USER_EMAIL.setCellValueFactory(new PropertyValueFactory<>("USER_EMAIL"));
        col_DEADLINE.setCellValueFactory(new PropertyValueFactory<>("DEADLINE"));
        //Remplir TableView par une liste des taches à partir ObservableList
        observableTU.addAll(tacheUserMetier.findAll());
        //Charger comBox
        //Methode 1: combBoxCate.getItems().addAll(categoryService.selectListCategory());
        observableTache.addAll(tacheMetier.selectTacheByCreateur(responsable));
        try {
            observableIntervenants.addAll(usersMetier.getAllIntervenant());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //remplir les champs avec la ligne slectionnée
        tableTacheUser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AssocTacheUser>() {
            @Override
            public void changed(ObservableValue<? extends AssocTacheUser> observableValue, AssocTacheUser oldObject, AssocTacheUser newObject) {
                if (newObject != null) {
                    {
                        comboBoxTache.setValue(newObject.getTACHE());
                        try {
                            comboBoxIntervenants.setValue(usersMetier.findByMail((newObject.getUSER_EMAIL())));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        observableMaterielle.clear();
                        List<Materielle> lMaterielles = tacheMetier.selectMateriellesDeTache(newObject.getTACHE());
                        if(lMaterielles.isEmpty()){
                            comboBoxMaterielle.setPromptText("*** AUCUNE MATERIELLE AFFECTE A CET TACHE");
                        }
                        else
                            observableMaterielle.addAll(lMaterielles);
                        observableMaterielle.addAll();
                        //pour charger comBox
                        datePicker.setValue(newObject.getLocalDate());
                    }
                }
            }
        });
    }

    public void refreshTable(){
        observableTU.clear();
        observableTU.addAll(tacheUserMetier.findAll());
        observableMaterielle.clear();
    }

}

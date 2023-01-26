package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class gestionIntervenantControllers implements Initializable {

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
    private Button buttonAjouter;

    @FXML
    private TextField search;

    @FXML
    private TableView<Users> tableViewIntervenant;

    @FXML
    private TableColumn<Users, String> colNom;

    @FXML
    private TableColumn<Users, String> colPrenom;

    @FXML
    private TableColumn<Users, String> colAdresse;

    @FXML
    private TableColumn<Users, String> colMail;

    @FXML
    private TableColumn<Users, String> colTelephone;

    @FXML
    private TableColumn<Users, Integer> colId;

    @FXML
    private TextField password;

    ObservableList<Users> intervenantObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewIntervenant.setItems(intervenantObservableList);

        colId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("NOM"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("PRENOM"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("ADRESSE"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("MAIL"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("TELEPHONE"));

        /*search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    new ProduitService().findByMotClé(t1);
                    produitObservableList.clear();
                    produitObservableList.addAll(new ProduitService().findByMotClé(t1));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });*/


        try {
            loadIntervenant();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void EnregistrerIntervenant(ActionEvent event) throws SQLException {
        int id = (int) ModifierIntervenant(event);
        String nom_text = nom.getText();
        String prenom_text = prenom.getText();
        String adresse_text = adresse.getText();
        String mail_text = mail.getText();
        String telephone_text = telephone.getText();
        String password_text = password.getText();

        Users users = new Users(id,nom_text,prenom_text, adresse_text,mail_text,telephone_text,password_text);

        UsersMetier usersMetier = new UsersMetier();
        usersMetier.update(users);
        loadIntervenant();

    }

    @FXML
    long ModifierIntervenant(ActionEvent event) {
        Users users = tableViewIntervenant.getSelectionModel().getSelectedItem();
        nom.setText(users.getNOM());
        prenom.setText(users.getPRENOM());
        adresse.setText(users.getADRESSE());
        mail.setText(users.getMAIL());
        telephone.setText(users.getTELEPHONE());
        password.setText(users.getPASSWORD());
        long id = users.getID();

        return id;
    }


    @FXML
    void supprimerIntervenant(ActionEvent event) {

    }



    public void ajouterIntervenant(ActionEvent actionEvent) throws SQLException {
        Users users = new Users();
        users.setNOM(nom.getText());
        users.setPRENOM(prenom.getText());
        users.setADRESSE(adresse.getText());
        users.setMAIL(mail.getText());
        users.setROLE("Intervenant");
        users.setTELEPHONE(telephone.getText());
        users.setPASSWORD(password.getText());

        new UsersMetier().add(users);
        loadIntervenant();
    }

    private void loadIntervenant() throws SQLException {
        intervenantObservableList.clear();
        intervenantObservableList.addAll(new UsersMetier().findIntervenant());
    }
}

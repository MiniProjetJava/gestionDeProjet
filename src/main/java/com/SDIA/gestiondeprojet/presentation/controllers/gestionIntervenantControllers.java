package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.UsersTest;
import com.SDIA.gestiondeprojet.metier.IntervenantMetier;
import com.SDIA.gestiondeprojet.metier.UsersMetier;
import com.SDIA.gestiondeprojet.metier.UsersTestMetier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField password;

    @FXML
    private TextField search;

    @FXML
    private TableView<Intervenant> tableViewIntervenant;

    @FXML
    private TableColumn<Intervenant, String> colNom;

    @FXML
    private TableColumn<Intervenant, String> colPrenom;

    @FXML
    private TableColumn<Intervenant, String> colAdresse;

    @FXML
    private TableColumn<Intervenant, String> colMail;

    @FXML
    private TableColumn<Intervenant, String> colTelephone;

    @FXML
    private TableColumn<Intervenant, Integer> colId;


    ObservableList<Intervenant> intervenantObservableList = FXCollections.observableArrayList();

    //IntervenantMetier intervenantMetier = new IntervenantMetier();
    UsersMetier usersMetier = new UsersMetier();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewIntervenant.setItems(intervenantObservableList);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    new IntervenantMetier().findByMotClé(t1);
                    intervenantObservableList.clear();
                    intervenantObservableList.addAll(new IntervenantMetier().findByMotClé(t1));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        /*try {
            categoryCombo.getItems().addAll(categoryService.getAll());
            // categoryCombo.getItems().add(categoryService.getAll().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            loadIntervenant();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void ajouterIntervenant(ActionEvent actionEvent) throws SQLException {
        Intervenant intervenant = new Intervenant();
        UsersMetier usersMetier = new UsersMetier();

        //IntervenantMetier intervenantMetier = new IntervenantMetier();
        UsersTestMetier usersTestMetier = new UsersTestMetier();
        UsersTest users = new UsersTest();

        intervenant.setNom(nom.getText());
        intervenant.setPrenom(prenom.getText());
        intervenant.setAdresse(adresse.getText());
        intervenant.setMail(mail.getText());
        intervenant.setTelephone(telephone.getText());
        intervenant.setPassword(password.getText());
        intervenant.setRole("Intervenant");

        users.setMail(mail.getText());
        users.setPassword(password.getText());
        users.setRole("Intervenant");

        usersMetier.add(intervenant);
        usersTestMetier.add(users);

        loadIntervenant();

    }

    public int ModifierIntervenant(ActionEvent actionEvent) throws SQLException {
        Intervenant intervenant = tableViewIntervenant.getSelectionModel().getSelectedItem();
        nom.setText(intervenant.getNom());
        prenom.setText(intervenant.getPrenom());
        adresse.setText(intervenant.getAdresse());
        mail.setText(intervenant.getMail());
        telephone.setText(intervenant.getTelephone());

        return intervenant.getId();

    }

    public void EnregistrerIntervenant(ActionEvent actionEvent) throws SQLException {

        String nom_text = nom.getText();
        String prenom_text = prenom.getText();
        String adresse_text = adresse.getText();
        String mail_text = mail.getText();
        String telephone_text = telephone.getText();
        String password_text = password.getText();

        Intervenant intervenant = new Intervenant(nom_text, prenom_text, adresse_text,mail_text, telephone_text, password_text);
        int new_id = ModifierIntervenant(actionEvent);
        intervenant.setId(new_id);

        new IntervenantMetier().update(intervenant);

        loadIntervenant();

        nom.setText(" ");
        prenom.setText(" ");
        adresse.setText(" ");
        mail.setText(" ");
        telephone.setText(" ");

    }


    private void loadIntervenant() throws SQLException {
        intervenantObservableList.clear();
        intervenantObservableList.addAll(usersMetier.getAll());
    }

    public void supprimerIntervenant(ActionEvent actionEvent) throws SQLException {
        Intervenant intervenant = tableViewIntervenant.getSelectionModel().getSelectedItem();
        IntervenantMetier intervenantMetier = new IntervenantMetier();
        intervenantMetier.delete(intervenant);
        loadIntervenant();
    }
}

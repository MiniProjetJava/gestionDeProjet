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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    new UsersMetier().findByMotCle(t1);
                    intervenantObservableList.clear();
                    intervenantObservableList.addAll(new UsersMetier().findByMotCle(t1));
                    if(t1.equals("")){
                        intervenantObservableList.clear();
                        intervenantObservableList.addAll(new UsersMetier().findIntervenant());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Ce eveneement sert à remplir le champ des TextFields une fois qu'on appuie sur l'un d'eux
        tableViewIntervenant.getSelectionModel().selectedItemProperty().addListener((observableValue, oldObject, newObject) -> {
            if (newObject != null) {
                {
                    nom.setText(newObject.getNOM());
                    prenom.setText(newObject.getPRENOM());
                    adresse.setText(String.valueOf(newObject.getADRESSE()));
                    mail.setText(String.valueOf(newObject.getMAIL()));
                    telephone.setText(String.valueOf(newObject.getTELEPHONE()));
                    password.setText(String.valueOf(newObject.getPASSWORD()));

                }
            }
        });


        try {
            loadIntervenant();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void ModifierIntervenant(ActionEvent event) throws SQLException {
        Users users = tableViewIntervenant.getSelectionModel().getSelectedItem();

        if(users != null) {
            if(!nom.getText().isEmpty() && !prenom.getText().isEmpty() && !adresse.getText().isEmpty() && !mail.getText().isEmpty()
                    && !telephone.getText().isEmpty() && !password.getText().isEmpty()) {

                users.setNOM(nom.getText());
                users.setPRENOM(prenom.getText());
                users.setADRESSE(adresse.getText());
                users.setMAIL(mail.getText());
                users.setTELEPHONE(telephone.getText());
                users.setPASSWORD(password.getText());
                users.setID(users.getID());

                new UsersMetier().update(users);
                loadIntervenant();

                nom.setText("");
                prenom.setText("");
                adresse.setText("");
                mail.setText("");
                telephone.setText("");
                password.setText("");

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText("ERROR : there is a field empty.");
                alert.setContentText("Solution : Please fill all the fields and then press the button [Update].");
                Optional<ButtonType> result = alert.showAndWait();
                //if(optional.get()==ButtonType.OK) {

            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : there is no selected product from the table below.");
            alert.setContentText("Solution : Please try to select a product and then press the button [Update].");
            Optional<ButtonType> result = alert.showAndWait();

        }

    }


    @FXML
    void supprimerIntervenant(ActionEvent event) throws SQLException {
        Users users = tableViewIntervenant.getSelectionModel().getSelectedItem();

        if(users != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION MESSAGE");
            alert.setHeaderText("The user with id : "+users.getID()+" | Name : "+users.getNOM()+" will be deleted. Press [OK]");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                new UsersMetier().delete(users);
                //loadIntervenant();
                intervenantObservableList.remove(users);

            }
        }

        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("ERROR : there is no selected product from the table below.");
            alert.setContentText("Solution : Please try to select a product and then press the button [Delete].");
            alert.showAndWait();
        }


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

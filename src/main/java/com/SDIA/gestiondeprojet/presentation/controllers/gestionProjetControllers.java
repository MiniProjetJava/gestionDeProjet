package com.SDIA.gestiondeprojet.presentation.controllers;

import com.SDIA.gestiondeprojet.dao.ProjetDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Users;
import com.SDIA.gestiondeprojet.metier.ProjetMetierImpl;
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

public class gestionProjetControllers implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField etat;
    @FXML
    private TextField nombre_intervenant;
    @FXML
    private TextField search;
    @FXML
    private TableView<Projet> projetTableView;
    @FXML
    private TableColumn<Projet, Integer> colID;
    @FXML
    private TableColumn<Projet, String> colNom;
    @FXML
    private TableColumn<Projet, String> colDescription;
    @FXML
    private TableColumn<Projet, String> colEtat;
    @FXML
    private TableColumn<Projet, Integer> colNbreIntervenant;

    ObservableList<Projet> projetObservableList = FXCollections.observableArrayList();

    SingletonData data = SingletonData.getInstance();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projetTableView.setItems(projetObservableList);


        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("NOM"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("ETAT"));
        colNbreIntervenant.setCellValueFactory(new PropertyValueFactory<>("NBR_INTERVENANTS"));

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Users users = null;
                try {
                    users = new UsersMetier().findByMail(data.getMail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                long id_responsable = users.getID();

                try {
                    new UsersMetier().findByMotCle(t1);
                    projetObservableList.clear();
                    projetObservableList.addAll(new ProjetMetierImpl().findByMot(id_responsable,t1));
                    if(t1.equals("")){
                        //projetObservableList.clear();
                        //projetObservableList.addAll(new ProjetMetierImpl().findByMot(id_responsable,t1));
                        loadProjet();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Ce eveneement sert à remplir le champ des TextFields une fois qu'on appuie sur l'un d'eux
        projetTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldObject, newObject) -> {
            if (newObject != null) {
                {
                    nom.setText(newObject.getNOM());
                    description.setText(newObject.getDESCRIPTION());
                    etat.setText(String.valueOf(newObject.getETAT()));
                    nombre_intervenant.setText(String.valueOf(newObject.getNBR_INTERVENANTS()));

                }
            }
        });


        try {
            loadProjet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void ajouterProjet(ActionEvent actionEvent) throws SQLException {
        Users users = new UsersMetier().findByMail(data.getMail());
        long id_responsable = users.getID();
        String nom_text = nom.getText();
        String description_text = description.getText();
        String etat_text = etat.getText();
        int nombre_intervenant_text = Integer.parseInt(nombre_intervenant.getText());

        Projet projet = new Projet(nom_text,etat_text,id_responsable,description_text,nombre_intervenant_text);

        new ProjetMetierImpl().insertProjet(projet);
        loadProjet();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("");
        alert.setContentText("Ajout reussie");
        Optional<ButtonType> result = alert.showAndWait();
    }

    private void loadProjet() throws SQLException {
        Users users = new UsersMetier().findByMail(data.getMail());
        long id = users.getID();
        projetObservableList.clear();
        projetObservableList.addAll(new ProjetMetierImpl().findByIdResponsable(id));
    }

    public void modifierProjet(ActionEvent actionEvent) throws SQLException {
        Projet projet = projetTableView.getSelectionModel().getSelectedItem();

        if(projet != null) {
            if(!nom.getText().isEmpty() && !description.getText().isEmpty() && !etat.getText().isEmpty() && !nombre_intervenant.getText().isEmpty()) {

                projet.setNOM(nom.getText());
                projet.setDESCRIPTION(description.getText());
                projet.setETAT(etat.getText());
                projet.setNBR_INTERVENANTS(Integer.parseInt(nombre_intervenant.getText()));

                new ProjetMetierImpl().updateProjet(projet);

                loadProjet();

                nom.setText("");
                description.setText("");
                etat.setText("");
                nombre_intervenant.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("");
                alert.setContentText("Modification reussie");
                Optional<ButtonType> result = alert.showAndWait();

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText("ERROR : there is a field empty.");
                alert.setContentText("Solution : Please fill all the fields and then press the button [Update].");
                Optional<ButtonType> result = alert.showAndWait();

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

    public void supprimerProjet(ActionEvent actionEvent) throws SQLException {
        Projet projet = projetTableView.getSelectionModel().getSelectedItem();

        if(projet != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION MESSAGE");
            alert.setHeaderText("The user with id : "+projet.getID()+" | Name : "+ projet.getNOM()+" will be deleted. Press [OK]");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                new ProjetDAOImpl().delete(projet);
                projetObservableList.remove(projet);

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
}

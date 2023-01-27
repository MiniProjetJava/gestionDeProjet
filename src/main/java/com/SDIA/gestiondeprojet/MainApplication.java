package com.SDIA.gestiondeprojet;

import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.metier.TacheMetier;
import com.SDIA.gestiondeprojet.metier.TacheMetierImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Connection/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        //Set styleSheet below :
        //scene.getStylesheets().add("com.OussezMVC/Presentation/Vue/StyleCSS.css");
        stage.setTitle("                                                                   LOGIN GUI | v1.0");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
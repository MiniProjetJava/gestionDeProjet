package com.SDIA.gestiondeprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
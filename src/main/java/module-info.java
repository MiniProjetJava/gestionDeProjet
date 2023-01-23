module com.example.gestiondeprojet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestiondeprojet to javafx.fxml;
    opens com.example.gestiondeprojet.presentation.controllers to javafx.fxml;
    opens com.example.gestiondeprojet.entities to javafx.fxml, javafx.base;

    exports com.example.gestiondeprojet.presentation.controllers to javafx.fxml;
    exports com.example.gestiondeprojet.entities to javafx.fxml, javafx.base;
    exports com.example.gestiondeprojet;
}
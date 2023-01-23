module com.example.gestiondeprojet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.SDIA.gestiondeprojet to javafx.fxml;
    opens com.SDIA.gestiondeprojet.presentation.controllers to javafx.fxml;
    opens com.SDIA.gestiondeprojet.dao.entities to javafx.fxml, javafx.base;

    exports com.SDIA.gestiondeprojet.presentation.controllers to javafx.fxml;
    exports com.SDIA.gestiondeprojet.dao.entities to javafx.fxml, javafx.base;
    exports com.SDIA.gestiondeprojet;
    exports com.SDIA.gestiondeprojet.dao to javafx.base, javafx.fxml;
    opens com.SDIA.gestiondeprojet.dao to javafx.base, javafx.fxml;
}
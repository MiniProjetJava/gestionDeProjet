package com.SDIA.gestiondeprojet.dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnexion {
    private static final String HOST = "localhost";
    private static final String DB_NAME = "gestiondeprojet";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String PORT = "3306";
    private static final String URL ="jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }
        catch (Exception e){
            System.out.println("[ERROR DE CONNEXION à BDD]--> "+e.getMessage());
        }

        return connection;
    }
}

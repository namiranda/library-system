package com.biblioteca.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static DBConexion instance;
    private Connection connection;

    private DBConexion() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DBConexion getInstance() throws SQLException {
        if (instance == null) {
            // Patrón Double-Checked Locking
            synchronized (DBConexion.class) {
                if (instance == null) {
                    instance = new DBConexion();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

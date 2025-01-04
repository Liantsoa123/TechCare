package org.example.techcare.model.utils;

import org.example.techcare.model.customers.Customers;
import org.example.techcare.model.customers.CustomersDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectionBdd {
    // Database URL, username, and password
    private final String URL = "jdbc:postgresql://localhost:5432/tech_care";
    private final String USER = "postgres";
    private final String PASSWORD = "liantsoa";

    public Connection getConnection() {
        Connection conn = null;
        try {
            // load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return conn;
    }
}

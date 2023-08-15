package com.portfolio.empmanapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTester {

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver"; // e.g., "com.mysql.cj.jdbc.Driver"
        String url = "jdbc:mysql://localhost:3306/database_student";       // e
        // .g., "jdbc:mysql://localhost:3306/mydatabase"
        String username = "student";
        String password = "student";

        Connection connection = null;

        try {
            // Load the database driver (optional for JDBC 4.0+)
            Class.forName(driver);

            // Create the database connection
            connection = DriverManager.getConnection(url, username, password);

            // If we reach this point, the connection is successfully established
            System.out.println("Connection established!");

            // Do your database operations here...

        } catch (ClassNotFoundException e) {
            // Driver class not found, check your driver library import or classpath
            e.printStackTrace();
        } catch (SQLException e) {
            // Connection could not be established, check your database URL, username, and password
            e.printStackTrace();
        } finally {
            // Close the connection in the finally block (good practice)
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
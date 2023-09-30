package com.portfolio.empmanapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTester {
    public static void main(String[] args) {
        Connection connection = getRemoteConnection();
        if (connection != null) {
            System.out.println("Connection to the database established successfully!");
            // Здесь вы можете выполнять операции с базой данных
            // Например, вы можете использовать объект connection для выполнения SQL-запросов.
        } else {
            System.err.println("Failed to establish a connection to the database.");
        }
    }

    private static Connection getRemoteConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbName = System.getProperty("databasestudent");
            String userName = System.getProperty("admin");
            String password = System.getProperty("kolobok0202");
            String hostname = System.getProperty("RDS_HOSTNAME");
            String port = System.getProperty("RDS_PORT");
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password + "&useSSL=false";

            System.out.println("Getting remote connection with connection string from environment variables.");
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Remote connection successful.");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


//connection with AWS RDS
//          String driver = "com.mysql.cj.jdbc.Driver";
//                  String url = databasestudent.cc8xhb89tlgo.us-east-2.rds.amazonaws.com
//                  String username = "admin";
//                  String password = "kolobok0202";










//////////////////////////////////////////////////////////////////////////////

//package com.portfolio.empmanapp;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionTester {
//
//    public static void main(String[] args) {
//            // connection with local mysql database:
//        String driver = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/database_student";
//        String username = "student";
//        String password = "student";
//
//          Connection connection = null;
//
//        try {
//            // Load the database driver (optional for JDBC 4.0+)
//            Class.forName(driver);
//
//            // Create the database connection
//            connection = DriverManager.getConnection(url, username, password);
//
//            // If we reach this point, the connection is successfully established
//            System.out.println("Connection established!");
//
//            // Do your database operations here...
//
//        } catch (ClassNotFoundException e) {
//            // Driver class not found, check your driver library import or classpath
//            e.printStackTrace();
//        } catch (SQLException e) {
//            // Connection could not be established, check your database URL, username, and password
//            e.printStackTrace();
//        } finally {
//            // Close the connection in the finally block (good practice)
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
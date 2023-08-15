package com.portfolio.empmanapp;

import java.sql.*;

public class JDBCPreparedStatement {

    public void runSample(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; //index in ResultSet start from 1 but not from 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/database_student", "student", "student");

            String firstName = args[0];
            String lastName = args[1];
            String ssn = args[2];
            String dept = args[3];
            String room = args[4];
            String phone = args[5];

            String insertString = "INSERT INTO employees (first_name, last_name, ssn, dept, room, phone) " + "VALUES (?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, ssn);
            preparedStatement.setString(4, dept);
            preparedStatement.setString(5, room);
            preparedStatement.setString(6, phone);

            int rowsInserted = preparedStatement.executeUpdate();

            if(rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully!");
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println("New employee ID is " + id);

                    preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE emp_id = ?");
                    preparedStatement.setInt(1, id);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String newFirstName = resultSet.getString("first_name");
                        String newLastName = resultSet.getString("last_name");
                        String newSSN = resultSet.getString("ssn");
                        String newDept = resultSet.getString("dept");
                        String newRoom = resultSet.getString("room");
                        String newPhone = resultSet.getString("phone");

                        System.out.println("Newly inserted employee:");
                        System.out.println("First Name: " + newFirstName);
                        System.out.println("Last Name: " + newLastName);
                        System.out.println("SSN: " + newSSN);
                        System.out.println("Department: " + newDept);
                        System.out.println("Room Number: " + newRoom);
                        System.out.println("Phone Number: " + newPhone);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException classNotFound) {
            classNotFound.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {


                if (connection != null) { //checks if the object of Connection was created
                    connection.close(); // if it is true it will invoke method close()
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     *  The main program for the JDBCPreparedStatement class
     *
     *@param  args  The command line arguments
     *
     *@since
     */

    public static void main(String[] args) {

        JDBCPreparedStatement employee = new JDBCPreparedStatement();

        employee.runSample(args);

    }
}






//package java112.project4;
//
//import java.io.*;
//import java.sql.*;
//
//public class JDBCPreparedStatement {
//
//    public void runSample(String[] args) {
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null; //index in ResultSet start from 1 but not from 0;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost/database_student", "student", "student");
//
//            String firstName = args[0];
//            String lastName = args[1];
//            String ssn = args[2];
//            String dept = args[3];
//            String room = args[4];
//            String phone = args[5];
//
//            String insertString = "INSERT INTO employees (first_name, last_name, ssn, dept, room, phone) " + "VALUES (?, ?, ?, ?, ?, ?)";
//
//            preparedStatement = connection.prepareStatement(insertString,Statement.RETURN_GENERATED_KEYS);
//
//
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setString(3, ssn);
//            preparedStatement.setString(4, dept);
//            preparedStatement.setString(5, room);
//            preparedStatement.setString(6, phone);
//
//            int rowsInserted = preparedStatement.executeUpdate();
//
//            //////////////////////////for deleting rows from the employees table by id number///////////////////////
////            String deleteString = "DELETE FROM employees WHERE emp_id IN (?,?)";
////            preparedStatement = connection.prepareStatement(deleteString);
////            preparedStatement.setInt(1, 166);
////            preparedStatement.setInt(2, 167);
////            int rowsDeleted = preparedStatement.executeUpdate();
////
////            if (rowsDeleted > 0) {
//            /////////////////////////////////////////////////////////////////////////////////////////////
//
//                if(rowsInserted > 0) {
//                System.out.println("A new employee was inserted successfully!");
//                resultSet = preparedStatement.getGeneratedKeys();
//                if (resultSet.next()) {
//                    int id = resultSet.getInt(1);
//                    System.out.println("New employee ID is " + id);
//                }
//            }
//
//        } catch (ClassNotFoundException | SQLException classNotFound) {
//            classNotFound.printStackTrace();
//        } catch (Exception exception) {
//            System.err.println("General Error");
//            exception.printStackTrace();
//        } finally {
//            try {
//
//
//                if (connection != null) { //checks if the object of Connection was created
//                    connection.close(); // if it is true it will invoke method close()
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     *  The main program for the JDBCPreparedStatement class
//     *
//     *@param  args  The command line arguments
//     *
//     *@since
//     */
//
//    public static void main(String[] args) {
//
//        JDBCPreparedStatement employee = new JDBCPreparedStatement();
//
//        employee.runSample(args);
//
//    }
//}

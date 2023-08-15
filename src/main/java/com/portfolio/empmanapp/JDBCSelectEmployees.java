package com.portfolio.empmanapp;

import java.sql.*;

/**
 *
 * @author Eric Knapp
 *
 *         practices for creating a database connection
 */

public class JDBCSelectEmployees {

    public void runSample() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // first step: Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // driver for MySQL.
            // second step: Define the connection URL
            // third step: Establish the connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/database_student", "student", "student");
            // fourth step: Create a Statement object.
            statement = connection.createStatement();

            String name = "Smith";
            String queryString = "SELECT emp_id, first_name, last_name"
                    + " FROM employees " + "WHERE last_name like '"
                    + name + "%'";

            System.out.println("queryString: " + queryString);
            // Fifth step: Execute a query or update.
            resultSet = statement.executeQuery(queryString);

            System.out.println();
            // Sixth step: Process the results
            while (resultSet.next()) {
                String employeeId = resultSet.getString("emp_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println(" Row: " + employeeId + " "
                        + firstName + " " + lastName);
            }

            System.out.println();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columns = resultSetMetaData.getColumnCount();
            String nameOne = resultSetMetaData.getColumnName(1);
            String typeOne = resultSetMetaData.getColumnTypeName(1);
            String labelOne = resultSetMetaData.getColumnLabel(1);
            System.out.println(" Column count : " + columns);
            System.out.println(" Column 1 name : " + nameOne);
            System.out.println(" Column 1 type : " + typeOne);
            System.out.println(" Column 1 label name : " + labelOne);

            System.out.println();

        } catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            // Seventh step: Close the connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * The main program for the JDBCSelectWhereExample class
     *
     * @param args The command line arguments
     *
     * @since
     *
     */
    public static void main(String[] args) {

        JDBCSelectEmployees employees = new JDBCSelectEmployees();

        employees.runSample();

    }
}
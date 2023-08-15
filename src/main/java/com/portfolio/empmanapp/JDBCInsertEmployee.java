package com.portfolio.empmanapp;

import java.sql.*;

/**
 *
 * The JDBCInsertEmployee class inserts a new employee into the Employees table
 *
 */
public class JDBCInsertEmployee {

    public void runSample(String firstName, String lastName, String ssn, String dept, String room, String phone) {


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/database_student", "student", "student");

            statement = connection.createStatement();




            String insertString = "INSERT INTO employees (first_name, last_name, ssn, dept, room, phone)"
                    + " VALUES ('" + firstName + "', '" + lastName + "', '" + ssn + "', '"
                    + dept + "', '" + room + "', '" + phone + "')";



            int rowsAffected = statement.executeUpdate(insertString);

            if (rowsAffected == 1) {
                System.out.println("New data was added successfully(this message from JDBCInsertEmployee)");

                String selectString = "SELECT emp_id, first_name, last_name, ssn, dept, room, phone"
                        + " FROM employees WHERE ssn = '" + ssn + "'";
                resultSet = statement.executeQuery(selectString);

                if (resultSet.next()) {
                    String employeeId = resultSet.getString("emp_id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    String social_security_number = resultSet.getString("ssn");
                    String department = resultSet.getString("dept");
                    String room_number = resultSet.getString("room");
                    String phone_number = resultSet.getString("phone");

                    System.out.println("Newly inserted employee:");
                    System.out.println("ID: " + employeeId);
                    System.out.println("First Name: " + first_name);
                    System.out.println("Last Name: " + last_name);
                    System.out.println("SSN: " + social_security_number);
                    System.out.println("Department: " + department);
                    System.out.println("Room Number: " + room_number);
                    System.out.println("Phone Number: " + phone_number);
                } else {
                    System.out.println("Could not retrieve newly inserted employee record");
                }

            } else {
                System.out.println("Data was not added to the database");
            }

        } catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
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
     *  The main program for the JDBCSelectWhereExample class
     *
     *@param  args  The command line arguments
     *
     *@since
     *
     */
    public static void main(String[] args) {

        JDBCInsertEmployee employees = new JDBCInsertEmployee();

//        employees.runSample("Peter", "Sokor", "000-00-0000", "IT", "000", "000-0000");
        employees.runSample(args[0], args[1], args[2], args[3], args[4], args[5]);

    }
}

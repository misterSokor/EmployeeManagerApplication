package employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeDirectory {

    private Properties properties;

    public EmployeeDirectory(Properties properties) {
        this.properties = properties;
    }

    /**
     * The createConnection() method that establishes a database connection
     * using the properties provided in the properties file(project4.properties)
     * It loads the database driver class, creates a connection, and returns it
     * with url, username and password for the database
     *
     * @return
     * @throws SQLException
     */
    private Connection createConnection() throws SQLException {
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }
//////////////////////////////////// ADDING  EMPLOYEES /////////////////////////////////////////////////

    /**
     * The addEmployee() method is used to add an employee to the database.
     * It takes employee details as parameters and inserts them into the "employees" table using
     * a prepared statement.
     *
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param department
     * @param roomNumber
     * @param phoneNumber
     * @return
     */
    public String addEmployee(String firstName, String lastName,
                              String socialSecurityNumber, String department, String roomNumber,
                              String phoneNumber) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();
            String selectStatement = "SELECT * FROM employees WHERE ssn = ?";
            String insertStatement = "INSERT INTO employees " +
                    "(first_name, last_name, ssn, dept, room, phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            // Check if an employee with the same Social Security Number already exists
            preparedStatement = connection.prepareStatement(selectStatement);
            preparedStatement.setString(1, socialSecurityNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // An employee with the same Social Security Number already exists
                return "This employee is already registered.";
            } else {
               // Insert the new employee into the database
                preparedStatement = connection.prepareStatement(insertStatement);

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, socialSecurityNumber);
                preparedStatement.setString(4, department);
                preparedStatement.setString(5, roomNumber);
                preparedStatement.setString(6, phoneNumber);

                preparedStatement.executeUpdate();

                // Return the name of the added employee
                return "The employee were successfully added.";

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error adding employee. Please try again later.";
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

//////////////////////////////////////  SEARCHING EMPLOYEES //////////////////////////////////////////////////

    /**
     * The searchEmployeesByTerm() method is a private helper method used to search
     * for employees based on a search term and SQL query. It executes the query
     * with the provided search term, retrieves the result set, and populates a Search object
     * with the found employees.
     *
     * @param search
     * @param sql
     * @param searchTerm
     */
    private void searchEmployeesByTerm(employee.Search search, String sql, String searchTerm) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, searchTerm);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee.Employee employee = new employee.Employee();

                employee.setId(resultSet.getString("emp_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setSsn(resultSet.getString("ssn"));
                employee.setDept(resultSet.getString("dept"));
                employee.setRoomNumber(resultSet.getString("room"));
                employee.setPhoneNumber(resultSet.getString("phone"));

                search.addFoundEmployee(employee);
            }

            if (search.getResults().size() > 0) {
                search.setFound(true);
            } else {
                search.setFound(false);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * The searchEmployees() method is the main method for searching employees.
     * It takes a search type and search term as parameters.
     * Based on the search type, it determines the SQL query to be used and calls the
     * searchEmployeesByTerm() method to perform the search.
     * It returns a Search object containing the search results.
     *
     * @param searchType
     * @param searchTerm
     * @return search (Search object)
     */
    public employee.Search searchEmployees(String searchType, String searchTerm) {

        employee.Search search = new employee.Search();
        String sql = null;

        if (searchType.equals("emp_id")) {
            sql = "SELECT * FROM employees WHERE emp_id = ?";
        } else if (searchType.equals("firstName")) {
            sql = "SELECT * FROM employees WHERE first_name = ?";
        } else if (searchType.equals("lastName")) {
            sql = "SELECT * FROM employees WHERE last_name = ?";
        }

        searchEmployeesByTerm(search, sql, searchTerm);

        return search;
    }
/////////////////////////////////REMOVE EMPLOYEES////////////////////////
    /**
     * The removeEmployeeBySSN() method is used to remove an employee from the database based on their SSN (Social Security Number).
     *
     * @param socialSecurityNumber The SSN of the employee to be removed
     * @return Message indicating the success or failure of the operation
     */
    public String removeEmployeeBySSN(String socialSecurityNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();
            String selectStatement = "SELECT * FROM employees WHERE ssn = ?";
            String deleteStatement = "DELETE FROM employees WHERE ssn = ?";

            // Check if an employee with the provided Social Security Number exists
            preparedStatement = connection.prepareStatement(selectStatement);
            preparedStatement.setString(1, socialSecurityNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Employee found, proceed with deletion
                preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setString(1, socialSecurityNumber);
                preparedStatement.executeUpdate();
                return "Employee with SSN " + socialSecurityNumber + " has been successfully removed.";
            } else {
                // Employee with the provided SSN not found
                return "Employee with SSN " + socialSecurityNumber + " was not found.";
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error removing employee. Please try again later.";
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }



///////////////////////////// show hall list of employees///////////////////
    /**
     * The showEmployeeList() method is used to retrieve and return a list of all employees from the database.
     * It executes a SQL query to retrieve all employees and populates a List of Employee objects with the result.
     *
     * @return List of all employees
     */
    public List<employee.Employee> showEmployeeList() {
        List<employee.Employee> employeeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();
            String selectStatement = "SELECT * FROM employees";
            preparedStatement = connection.prepareStatement(selectStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee.Employee employee = new employee.Employee();
                employee.setId(resultSet.getString("emp_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setSsn(resultSet.getString("ssn"));
                employee.setDept(resultSet.getString("dept"));
                employee.setRoomNumber(resultSet.getString("room"));
                employee.setPhoneNumber(resultSet.getString("phone"));
                employeeList.add(employee);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return employeeList;
    }
}

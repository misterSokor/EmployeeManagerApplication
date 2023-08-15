package com.portfolio.empmanapp;

import employee.Employee;
import employee.EmployeeDirectory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Servlet to display the hall list of employees
 */
@WebServlet(name = "ShowEmployeeListServlet", urlPatterns = {"/show-employee-list"})
public class ShowEmployeeListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load properties from a properties file
        // This part can be done in the init() method or better using a configuration manager.
        // For simplicity, you can directly create the EmployeeDirectory instance here.
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("project4.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create an instance of EmployeeDirectory using the loaded properties
        EmployeeDirectory employeeDirectory = new EmployeeDirectory(properties);

        // Call the showEmployeeList() method to get the list of all employees
        List<Employee> employeeList = employeeDirectory.showEmployeeList();

        // Set the employeeList as an attribute in the request to be used in the JSP
        request.setAttribute("employeeList", employeeList);

        // Forward the request to the JSP for displaying the list
        String url = "/showHallList.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}

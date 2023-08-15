package com.portfolio.empmanapp;

import employee.EmployeeDirectory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "RemoveEmployeeBySSNServlet", urlPatterns = {"/remove-employee-by-ssn"})
public class RemoveEmployeeBySSNServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the removeEmployee.jsp page for the user to enter the SSN
        String url = "/removeEmployee.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load properties from a properties file
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("project4.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create an instance of EmployeeDirectory using the loaded properties
        EmployeeDirectory employeeDirectory = new EmployeeDirectory(properties);

        String ssn = request.getParameter("ssn");

        // Call the removeEmployeeBySSN() method to remove the employee
        String result = employeeDirectory.removeEmployeeBySSN(ssn);

        // Set the result as an attribute in the request to be used in the JSP
        request.setAttribute("result", result);

        // Forward the request to the removeEmployeeResult.jsp to display the result
        String url = "/removeEmployeeResult.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}


//package com.portfolio.empmanapp;
//
//import employee.EmployeeDirectory;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
///**
// * Servlet to remove an employee by SSN
// */
//@WebServlet(name = "RemoveEmployeeBySSNServlet", urlPatterns = {"/remove-employee-by-ssn"})
//public class RemoveEmployeeBySSNServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Display the removeEmployee.jsp page for the user to enter the SSN
//        String url = "/removeEmployee.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Load properties from a properties file
//        Properties properties = new Properties();
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("project4.properties")) {
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        // Create an instance of EmployeeDirectory using the loaded properties
//        EmployeeDirectory employeeDirectory = new EmployeeDirectory(properties);
//
//        String ssn = request.getParameter("ssn");
//
//        // Call the removeEmployeeBySSN() method to remove the employee
//        String result = employeeDirectory.removeEmployeeBySSN(ssn);
//
//        // Set the result as an attribute in the request to be used in the JSP
//        request.setAttribute("result", result);
//
//        // Forward the request to the removeEmployeeResult.jsp to display the result
//        String url = "/removeEmployee.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
//    }
//}
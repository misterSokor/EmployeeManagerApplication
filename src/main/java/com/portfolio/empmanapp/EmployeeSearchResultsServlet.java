package com.portfolio.empmanapp;


import employee.EmployeeDirectory;
import employee.Search;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * This servlet handles the search functionality, interacts with the EmployeeDirectory object
 * to perform the search, stores the results in the session, and forwards the request to the JSP page
 * for displaying the search results
 * @author Peter Sokor
 */


@WebServlet(
        name = "employeeSearchResultsServlet",
        urlPatterns = {"/employee-search-servlet"}
)
public  class EmployeeSearchResultsServlet extends HttpServlet {
    /**
     * The doGet method is overridden, which is the method that will be invoked
     * when a GET request is sent to this servlet.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();

        /*
        The EmployeeDirectory object is retrieved from the ServletContext using the attribute
        name "empDirectory". This object was previously set in the
        application's context (class ApplicationStartup)
        and it contains the logic for searching employees.
        */
        EmployeeDirectory directory = (EmployeeDirectory) context.getAttribute("empDirectory");

        // These parameters are sent from the HTML form
        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");

        // Searching for the employees by calling a method in the EmployeeDirectory instance
        // The searchEmployees method is called on the EmployeeDirectory object,
        // passing the search type and search term as arguments.
        // This method performs the actual search operation and returns a Search object with results.
        Search searchResults = directory.searchEmployees(searchType, searchTerm);

        // Placing the Search object into the session to be accessible in the app.
        HttpSession session = request.getSession();
        session.setAttribute("searchResults", searchResults);

        // Check if the search results contain any employees
        if (searchResults.getResults().isEmpty()) {
            // No employees found, set a message in the session
            session.setAttribute("noEmployeeMessage", "There is no such employee");
        } else {
            // Clear any previous message from the session
            session.removeAttribute("noEmployeeMessage");
        }

        // Forward the request and response to the Employee Search Results JSP page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/employeeSearchResults.jsp");
        dispatcher.forward(request, response);
    }
}










///**
// * this servlet handles the search functionality, interacts with the EmployeeDirectory object
// * to perform the search, stores the results in the session, and forwards the request to the JSP page
// * for displaying the search results
// * @author Peter Sokor
// */
//
//
//import java112.employee.EmployeeDirectory;
//import java112.employee.Search;
//
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//@WebServlet(
//        name = "employeeSearchResultsServlet",
//        urlPatterns = {"/employee-search-servlet"}
//)
//public  class EmployeeSearchResultsServlet extends HttpServlet {
//    /**
//     * The doGet method is overridden, which is the method that will be invoked
//     * when a GET request is sent to this servlet.
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        ServletContext context = getServletContext();
///*
//The EmployeeDirectory object is retrieved from the ServletContext using the attribute
//  name "empDirectory". This object was previously set in the
//  application's context (class ApplicationStartup)
//  and it contains the logic for searching employees.
// */
//        EmployeeDirectory directory = (EmployeeDirectory) context.getAttribute("empDirectory");
//
//        // These parameters are sent from the HTML form
//        String searchType = request.getParameter("searchType");
//        String searchTerm = request.getParameter("searchTerm");
//
//        // Searching for the employees by calling  method in the EmployeeDirectory instance
//        //The searchEmployees method is called on the EmployeeDirectory object,
//        // passing the search type and search term as arguments.
//        // This method performs the actual search operation and returns a Search object with results.
//        //
//        Search searchResults = directory.searchEmployees(searchType, searchTerm);
//
//
//        // Placing the Search object into the session to be accessible in the app.
//        HttpSession session = request.getSession();
//        session.setAttribute("searchResults", searchResults);
//
//        // Forward the request and response to the Employee Search Results JSP page
//        RequestDispatcher dispatcher = getServletContext()
//                .getRequestDispatcher("/employeeSearchResults.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//

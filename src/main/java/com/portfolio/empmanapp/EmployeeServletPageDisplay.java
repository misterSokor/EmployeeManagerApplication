package com.portfolio.empmanapp;

/**
 * controller
 * This servlet class is responsible for displaying the "employeeSearch.jsp"
 *
 * It handles a GET request and forwards the request to the "employeeSearch.jsp" page
 * for further processing and generating the response.
 *
 * @author Peter Sokor
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;


@WebServlet(
        name = "EmployeeServletPageDisplay",
        urlPatterns = { "/employee-servlet" }
)
public class EmployeeServletPageDisplay extends HttpServlet {

    /**
     * this method is overriden method from the HttpServlet class
     * and is forwards the request and response objects to the specified resource
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/employeeSearch.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
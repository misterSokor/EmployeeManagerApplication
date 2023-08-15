package com.portfolio.empmanapp;

import employee.EmployeeDirectory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * this servlet handles the form submission for adding a new employee.
 * It retrieves the employee information, validates it, calls the addEmployee() method
 * of the employeeDirectory object to add the employee,
 * and stores the success message in the session
 *
 * @author Peter Sokor
 */
@WebServlet(name="EmployeeAddActionServlet", urlPatterns = {"/employeeAddAction"})
public class EmployeeAddActionServlet extends HttpServlet {

    /*
    The employeeDirectory instance variable of type EmployeeDirectory
    is declared to hold the reference to the EmployeeDirectory object.
    This object is used to perform the addition of the employee.
     */
    private EmployeeDirectory employeeDirectory;

    /**
     * The init() method is overridden to initialize the employeeDirectory object
     * by retrieving it from the servlet context
     *
     * @throws ServletException
     */
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        employeeDirectory = (EmployeeDirectory) context.getAttribute("empDirectory");
    }

    /**
     * The doPost() method is overridden to handle the HTTP POST request
     * sent by the form submission.
     * inside of this method, the employee information is extracted from the
     * request parameters using request.getParameter().
     * Those values are retrieved based on the input field names specified in the HTML form.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String ssn = request.getParameter("ssn");
        String department = request.getParameter("department");
        String roomNumber = request.getParameter("roomNumber");
        String phoneNumber = request.getParameter("phoneNumber");

    /*
    The addEmployee() method of the employeeDirectory object is called with
    the extracted employee information to add the employee.
    The returned message is stored in the variable "message".
    */
        String message = employeeDirectory.addEmployee(firstName, lastName, ssn, department, roomNumber, phoneNumber);

        // Store the returned message in the session attribute
        HttpSession session = request.getSession();
        session.setAttribute("project4AddMessage", message);

        // Redirect back to the EmployeeAdd.jsp
        response.sendRedirect(request.getContextPath() + "/EmployeeAdd.jsp");
    }

}


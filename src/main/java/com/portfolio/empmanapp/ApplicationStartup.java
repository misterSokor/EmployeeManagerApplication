package com.portfolio.empmanapp;

import employee.EmployeeDirectory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/application-startup" },
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);


        try {
            Properties properties = new Properties();


//            File propertiesFile = new File("project4.properties"); // this
//            way the application can't find the project4.properties file is
//            because you're trying to create a File object with a relative
//            path "project4.properties". The application attempts to locate
//            this file in the working directory rather than within the project's
//            resources.

            //This code retrieves the resource "project4.properties"
            // from the classpath and creates a File object for it.
            ClassLoader classLoader = getClass().getClassLoader();
            File propertiesFile = new File(classLoader.getResource("project4.properties").getFile());


            properties.load(new FileReader(propertiesFile));
            getServletContext().setAttribute("project4Properties", properties);

            EmployeeDirectory employeeDirectory = new EmployeeDirectory(properties);
            getServletContext().setAttribute("empDirectory", employeeDirectory);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
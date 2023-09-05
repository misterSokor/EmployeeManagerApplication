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


            File propertiesFile = new File("/Users/mac/IdeaProjects/EmployeeManagerApp/empManApp/src/main/resources/project4.properties");


            properties.load(new FileReader(propertiesFile));
            getServletContext().setAttribute("project4Properties", properties);

            EmployeeDirectory employeeDirectory = new EmployeeDirectory(properties);
            getServletContext().setAttribute("empDirectory", employeeDirectory);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
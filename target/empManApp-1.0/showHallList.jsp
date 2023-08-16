<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%@ page import="java.util.List" %>
<%@ page import="employee.Employee" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Hall List of Employees</title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <link rel="stylesheet" type="text/css" href="css/showHallList.css">
</head>
<body>
<main>
  <img src="images/LogoApp1.png" alt="name of app">

    <a href="index.jsp">
        <img src="images/home.png" alt="home page"
             style="width:45px;height:45px;">
    </a>

  <h1>Hall List of Employees</h1>
  <table border="1">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>SSN</th>
        <th>Department</th>
        <th>Room Number</th>
        <th>Phone Number</th>
    </tr>

      <%
        // Get the employeeList attribute set by ShowEmployeeListServlet
        List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");

        // Loop through the employeeList and display each employee's details in a row
        for (Employee employee : employeeList) {
       %>
    <tr>
        <td><%= employee.getId() %></td>
        <td><%= employee.getFirstName() %></td>
        <td><%= employee.getLastName() %></td>
        <td><%= employee.getSsn() %></td>
        <td><%= employee.getDept() %></td>
        <td><%= employee.getRoomNumber() %></td>
        <td><%= employee.getPhoneNumber() %></td>
    </tr>
  <%
    }
  %>
  </table>
<br>
<!-- Corrected link to ShowEmployeeListServlet using the GET method -->
<a href="show-employee-list">Show Hall List of Employees</a>
  <img src="images/YPshadow1.png" alt="pencil">
  </main>
</body>
</html>


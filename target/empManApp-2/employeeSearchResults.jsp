<%@ page import="employee.Employee" %>
<%@ page import="employee.Search" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--This JSP page is responsible for displaying the search results of employees--%>
<%--It retrieves the search results from the session, checks if there are any results, --%>
<%--and dynamically generates an HTML table to display the employee information.--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employee Search Results</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/employeeSearchResults.css">
</head>
<body>
<main>
<img src="images/LogoApp1.png" alt="name of app">

    <a href="index.jsp">
        <img src="images/home.png" alt="home page"
             style="width:45px;height:45px;">
    </a>
            <h1>Employee Search Results</h1>

            <% Search searchResults = (Search) session.getAttribute("searchResults"); %>

            <c:if test="${not empty searchResults}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>SSN</th>
                        <th>Department</th>
                        <th>Room Number</th>
                        <th>Phone Number</th>
                    </tr>
                    </thead>
                    <tbody>
<%--                    this snippet of code is not working because of problemle with actual
jstl liblary, it requires version 1.2.5 or 1.2.6, which is not present in the official
maven repository, this is why I am using scriplets instead of jstl--%>
<%--                    <c:forEach var="employee" items="${searchResults.results}">--%>
<%--                        <tr>--%>
<%--                            <td>${employee.id}</td>--%>
<%--                            <td>${employee.firstName}</td>--%>
<%--                            <td>${employee.lastName}</td>--%>
<%--                            <td>${employee.ssn}</td>--%>
<%--                            <td>${employee.dept}</td>--%>
<%--                            <td>${employee.roomNumber}</td>--%>
<%--                            <td>${employee.phoneNumber}</td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
                    <tbody>
                    <% for (Employee employee : searchResults.getResults()) { %>
                    <tr>
                        <td><%= employee.getId() %></td>
                        <td><%= employee.getFirstName() %></td>
                        <td><%= employee.getLastName() %></td>
                        <td><%= employee.getSsn() %></td>
                        <td><%= employee.getDept() %></td>
                        <td><%= employee.getRoomNumber() %></td>
                        <td><%= employee.getPhoneNumber() %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${not empty sessionScope.noEmployeeMessage}">
                <h1>${sessionScope.noEmployeeMessage}</h1>
            </c:if>
    <img src="images/YPshadow1.png" alt="pencil">
</main>
</body>
</html>




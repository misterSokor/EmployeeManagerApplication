<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="employee.Employee"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employee Search</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/employeeSearch.css">
</head>
<body>
<main>
    <img src="images/LogoApp1.png" alt="">

    <a href="index.jsp">
        <img src="images/home.png" alt="home page"
             style="width:45px;height:45px;">
    </a>
    <h1>Employee Search</h1>

 <form  method="GET"
              action="${pageContext.request.contextPath}/employee-search-servlet">
        <%--@declare id="searchtype"--%><label for="searchTerm">Search Term:</label>
       <br><br>

        <input type="text" name="searchTerm" id="searchTerm" required>
        <br> <br>
        <label for="searchType">Search By:</label>
        <br><br>
<%--       value="emp_id" sets the value associated with the radio button. --%>
<%--       When the form is submitted, the selected radio button's value --%>
<%--       will be sent to the server with the parameter name "searchType".--%>

<%--       checked means that the button with the ID "searchById" will be selected --%>
<%--       by default when the page is loaded.--%>
        <input type="radio" name="searchType" id="searchById" value="emp_id" checked>
        <label for="searchById">ID</label>

        <input type="radio" name="searchType" id="searchByLastName" value="lastName">
        <label for="searchByLastName">Last Name</label>

        <input type="radio" name="searchType" id="searchByFirstName" value="firstName">
        <label for="searchByFirstName">First Name</label>
            <br><br>
            <button type="submit" class="button">Submit</button>
 </form>
    <img src="images/YPshadow1.png" alt="">
</main>
</body>
</html>



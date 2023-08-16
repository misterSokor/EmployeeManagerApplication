<%--This is a JSP file which represents an HTML form --%>
<%--for adding a new employee.--%>

<%--directive that imports the JSTL core tag library--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Add</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/employeeAdd.css">
</head>
    <body>
        <main>
         <img src="images/LogoApp1.png" alt="name of app">

            <a href="index.jsp">
                <img src="images/home.png" alt="home page"
                     style="width:45px;height:45px;">
            </a>

        <h1>Add New Employee</h1>
        <c:if test="${not empty project4AddMessage}">
        <h3>${project4AddMessage}</h3>  </c:if>

        <%
            session.removeAttribute("project4AddMessage");
        %>

        <%--  When the form is submitted, the data is sent to the "/employeeAddAction" URL for processing--%>
<form action="${pageContext.request.contextPath}/employeeAddAction"
      method="post">
    <label for="firstName">First Name:</label> <input type="text"
                                                      name="firstName" id="firstName" required><br><br>
    <label for="lastName">Last Name:</label> <input type="text"
                                                    name="lastName" id="lastName" required><br><br>
    <label for="ssn">Social Security Number:</label> <input type="text"
                                                            name="ssn"
                                                            id="ssn"
                                                            pattern="\d{3}-\d{2}-\d{4}"
                                                            title="Please enter your number in this format: ###-##-####"
                                                            required><br><br>
    <label for="department">Department:</label> <input type="text"
                                                       name="department" id="department" required><br><br>
    <label for="roomNumber">Room Number:</label> <input type="text"
                                                        name="roomNumber"
                                                        id="roomNumber"
                                                        required><br><br>
    <label for="phoneNumber">Phone Number:</label> <input type="text"
                                                          name="phoneNumber"
                                                          id="phoneNumber"
                                                          pattern="\d{3}-\d{4}"
                                                          title="Please enter a valid phone number in the format ###-####"
                                                          required><br><br>
    <button type="submit" class="button">Add Employee</button>

</form>
    <img src="images/YPshadow1.png" alt="pencil">
</main>
</body>
</html>

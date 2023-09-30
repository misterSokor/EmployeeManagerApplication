<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Remove Employee Result</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/removeEmployeeResults.css">
</head>
<body>
    <main>
        <img src="images/LogoApp1.png" alt="name of app">

        <a href="index.jsp">
            <img src="images/home.png" alt="home page"
                 style="width:45px;height:45px;">
        </a>
        <h1>Remove Employee Result</h1>
        <%
        // Get the result attribute set by RemoveEmployeeBySSNServlet
        String result = (String) request.getAttribute("result");
        %>
          <h3><%= result %></h3>
        <img src="images/YPshadow1.png" alt="pencil">
    </main>
</body>
</html>


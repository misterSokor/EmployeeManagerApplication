<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Remove Employee</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
  <link rel="stylesheet" type="text/css" href="css/removeEmployee.css">
</head>
<body>
<main>
    <img src="images/LogoApp1.png" alt="name of app">

    <a href="index.jsp">
        <img src="images/home.png" alt="home page"
             style="width:45px;height:45px;">
    </a>
        <h1>Remove Employee</h1>
        <form action="remove-employee-by-ssn" method="post">
        <label for="ssn">Enter SSN:</label>
        <input type="text" id="ssn" name="ssn" pattern="\d{3}-\d{2}-\d{4}" title="Please enter the number in this format: ###-##-####">
            <br><br>
        <button type="submit" class="button">Remove</button>
        </form>
    <img src="images/YPshadow1.png" alt="pencil">
</main>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Changed Successfully</title>
    <link rel="stylesheet" href="css/resetsuccess-style.css"> <!-- Adjust the path based on your project structure -->
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    <div class="container">
        <div class="success-message">
            <h1>Password Changed Successfully</h1>
            <p>Your password has been updated successfully.</p>
            <a href="login.jsp">Login</a> <!-- Redirect to login page or any other page as needed -->
        </div>
    </div>
</body>
</html>

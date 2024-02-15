
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link rel="stylesheet" href="./css/resetpassword-style.css">
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <form action="ResetPasswordServlet" method="post">
        <h2>Reset Password</h2>
        <p style="color: red" class="text-danger">${mess}</p>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>  
        <input type="submit" value="Reset Password">
        <input type="hidden" value="${auth.userId}" name="userId">      
    </form>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link rel="stylesheet" href="./css/resetpassword-style.css">
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    <form action="ResetPasswordServlet" method="post">
        <h2>Reset Password</h2>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <input type="hidden" name="userId" value="${auth.userId}">
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>

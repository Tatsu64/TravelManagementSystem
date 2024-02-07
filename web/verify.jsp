
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verify OTP</title>
    <link rel="stylesheet" href="./css/verify-style.css">
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form action="VerifyOTPServlet" method="post">
        <h2>Enter OTP to Verify</h2>
        <label for="otp">OTP:</label>
        <input type="text" id="otp" name="otp" required>
        <input type="hidden" name="userId" value="${auth.userId}">
        <input type="submit" value="Verify">
    </form>
</body>
</html>

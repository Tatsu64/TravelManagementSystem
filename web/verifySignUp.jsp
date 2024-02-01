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
        <h2>Enter OTP to Sign Up</h2>
        <label for="otp">OTP:</label>
        <input type="text" id="otp" name="otp" required>
        
        
        <input type="hidden" value="${auth.name}" name="name">
        <input type="hidden" name="email" value="${auth.email}">
        <input type="hidden" name="password" value="${auth.password}">
        <input type="hidden" value="${auth.phone}" name="phone">
        <input type="hidden" value="${auth.address}" name="address">
        <input type="hidden" value="${auth.role}" name="role">
        
        <input type="submit" value="Verify">
    </form>
</body>
</html>
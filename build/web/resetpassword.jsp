<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Reset Password</title>
    <link rel="stylesheet" href="./css/verify-style.css">
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <!-- Main css -->
    <link rel="stylesheet" href="css/style_1.css">
    <%@include file="includes/header.jsp" %>
</head>
<body>
<div class="main">
    <!-- form -->
    <div class="container">
        <div class="signup-content">
            <div class="signup-form">
                <h2 class="form-title">Reset Password</h2>
                <form action="ResetPasswordServlet" method="post">
                    <div class="form-group">
                        <p style="color: red" class="text-danger">${mess}</p>
                        <input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="New Password" required>
                    </div>
                    <div class="form-group">
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm Password" required>
                        <input type="hidden" value="${auth.userId}" name="userId">      
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" class="form-submit" value="Reset Password"/>
                    </div>
                </form>
            </div>
            <div class="signup-image">
                <figure><img src="images/signup-image.jpg" alt="sign up image"></figure>
            </div>
        </div>
    </div>
</div>
</body>
</html>

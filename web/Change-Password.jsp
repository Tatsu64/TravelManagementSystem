<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Change Password</title>
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
                <h2 class="form-title">Change Password</h2>
                <form action="ChangePassword" method="post">
                    <div class="form-group">
                        <p style="color: red" class="text-danger">${mess}</p>
                        <input type="password" name="opass" class="form-control" placeholder="Current password">
                        <input type="hidden" name="email" value="${auth.email}"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="New password">
                    </div>
                    <div class="form-group">
                        <input type="password" name="rpass" class="form-control" placeholder="Repeat new password">
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" class="form-submit" value="Change Password"/>
                        <a href="index.jsp" style="color: black;" class="btn btn-default">Back to Home Page</a>
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

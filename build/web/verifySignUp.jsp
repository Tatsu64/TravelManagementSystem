<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Verify OTP</title>
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
                        <h2 class="form-title">Enter OTP to Sign Up</h2>
                        <form action="VerifyOTPServlet" method="post" class="register-form" id="register-form">

                            <div class="form-group">
                                <label for="otp"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="otp" id="otp" placeholder="OTP"required/>
                                <span id="email-error" class="text-danger"></span>
                            </div>
                            <input type="hidden" value="${auth.name}" name="name">
                            <input type="hidden" value="${auth.email}" name="email" >
                            <input type="hidden" value="${auth.password}" name="password" >
                            <input type="hidden" value="${auth.phone}" name="phone">
                            <input type="hidden" value="${auth.address}" name="address">
                            <input type="hidden" value="${auth.role}" name="role">

                            <div class="form-group form-button">
                                <input type="submit" name="otp" id="otp" class="form-submit" value="Verify"/>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="images/signup-image.jpg" alt="sign up image"></figure>
                    </div>
                </div>
            </div>
        </body>
</html>
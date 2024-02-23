<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Forgot Password</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style_1.css">
    <%@include file="includes/header.jsp" %>
</head>
<body>
<br>
<!-- Forgot Password Form -->
<section class="forgot-password">
    <div class="container">
        <div class="forgot-password-content">
            <div class="forgot-password-form">
                <br>
                <h2 class="form-title">Forgot Password</h2>
                <!-- Change method to POST and action to SendEmailServlet -->
                <form action="SendEmailServlet" method="post" class="forgot-password-form" id="forgot-password-form">
                    <div class="form-group">
                        <label for="forgot_email"><i class="zmdi zmdi-email"></i></label>
                        <!-- Change input name to match parameter name in servlet -->
                        <input type="email" name="email" id="forgot_email" placeholder="Your Email"/>
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" name="forgot_password_submit" id="forgot_password_submit" class="form-submit" value="Reset Password"/>
                        <a type="submit" href="login.jsp" style="color: black;" class="btn btn-default" >Back To Login</a>
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>

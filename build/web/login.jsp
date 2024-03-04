<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    
    <!-- Main css -->
    <link rel="stylesheet" href="css/style_1.css">
    <%@include file="includes/header.jsp" %>
</head>

<body>
    <div class="main">
        <!-- Sign in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="images/signin-image.jpg" alt="sign up image"></figure>
                        <a href="Sign-up.jsp" class="signup-image-link" id="show-signup">Create an account</a>
                        <br>
                        <a href="forgot-password.jsp" class="signup-image-link" id="show-forgot-password">Forgot Password</a>
                        <br>
                        <a href="index.jsp" class="signup-image-link">Back to Home Page</a>
                    </div>                 
                    <div class="signin-form">
                        <h2 class="form-title">Login</h2>
                        <p style="color: red" class="text-danger">${mess}</p>
                        <form action="UserServlet" method="post">
                            <div class="form-group">
                                <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="email" id="your_email" placeholder="Your Email"/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="your_pass" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                            </div>
                        </form>
                        <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>                           
                        </div>
                    </div>
                </div>
            </div>
        </section>        
    </div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
<script>
    $(document).ready(function(){
        // Function to hide both sign-in and sign-up sections
        function hideBothSections() {
            $(".signup").hide();
            $(".forgot-password").hide(); // Ẩn cả phần forgot password
        }

        // Initially hide the signup section
        hideBothSections();
        $(".sign-in").show(); // Show the login section by default

        // Show sign-up section when "Create an account" is clicked
        $("#show-signup").click(function(){
            hideBothSections(); // Hide both sections before showing the signup section
            $(".signup").show();
        });

        // Show sign-in section when "I am already a member" is clicked
        $("#show-signin").click(function(){
            hideBothSections(); // Hide both sections before showing the sign-in section
            $(".sign-in").show();
        });

        // Show forgot password section and hide sign-in section when "Forgot Password" is clicked
        $("#show-forgot-password").click(function(){
            hideBothSections(); // Ẩn cả hai phần tử trước khi hiển thị phần tử forgot password
            $(".forgot-password").show();
        });

        // Remember Me functionality
        const rememberMeCheckbox = $("#remember-me");

        // Check if login details are saved in localStorage
        const savedEmail = localStorage.getItem('email');
        const savedPassword = localStorage.getItem('password');

        if (savedEmail && savedPassword) {
            $("#your_email").val(savedEmail);
            $("#your_pass").val(savedPassword);
            rememberMeCheckbox.prop('checked', true);
        }

        // Capture submit event of login form
        $("#signin").click(function(){
            // Check if "Remember me" is checked
            if (rememberMeCheckbox.prop('checked')) {
                // Save login details to localStorage
                localStorage.setItem('email', $("#your_email").val());
                localStorage.setItem('password', $("#your_pass").val());
            } else {
                // Remove login details from localStorage if "Remember me" is not checked
                localStorage.removeItem('email');
                localStorage.removeItem('password');
            }
        });
        
    });
</script>
</body><!-- This template was made by Colorlib (https://colorlib.com) -->
</html>

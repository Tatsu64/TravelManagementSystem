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
</head>

<body>
    
    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form action="SignUpServlet" method="post" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email"/>
                                <span id="email-error" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" placeholder="Your Password"/>
                            </div>
                            <div class="form-group">
                                <label for="phone"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="phone" id="phone" placeholder="Your Phone"/>
                                <span id="phone-error" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label for="address"><i class="zmdi zmdi-address"></i></label>
                                <input type="text" name="address" id="address" placeholder="Your Address"/>
                            </div>
                            <div class="form-group">
                                <input type="hidden" name="role" id="role" value="User"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Sign Up"/>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="images/signup-image.jpg" alt="sign up image"></figure>
                        <a href="#" class="signup-image-link" id="show-signin">I am already a member</a>
                    </div>
                </div>
            </div>
        </section>

        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="images/signin-image.jpg" alt="sign up image"></figure>
                        <a href="#" class="signup-image-link" id="show-signup">Create an account</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Login</h2>
                        <p style="color: red" class="text-danger">${mess}</p>
                        <form action="UserServlet" method="get">
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
            $(".sign-in").hide();
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

        // Validation function for email
        function validateEmail(email) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(email);
        }

        // Validation function for phone
        function validatePhone(phone) {
            const phoneRegex = /^\d{10}$/; // Assuming a 10-digit phone number
            return phoneRegex.test(phone);
        }

        // Capture submit event of sign-up form
        $("#register-form").submit(function(event){
            // Validate email
            const email = $("#email").val();
            if (!validateEmail(email)) {
                $("#email-error").text("Invalid email address");
                event.preventDefault();
            } else {
                $("#email-error").text("");
            }

            // Validate phone
            const phone = $("#phone").val();
            if (!validatePhone(phone)) {
                $("#phone-error").text("Invalid phone number");
                event.preventDefault();
            } else {
                $("#phone-error").text("");
            }
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

        // ... (existing code)
    });
</script>
</body><!-- This template was made by Colorlib (https://colorlib.com) -->
</html>

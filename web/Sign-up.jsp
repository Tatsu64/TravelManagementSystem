<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <%-- Hiển thị thông báo lỗi nếu có --%>
                    <p style="color: red" class="text-danger">${mess}</p>
                    <form action="SignUpServlet" method="post" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <%-- Giữ lại giá trị đã nhập --%>
                            <input type="text" name="name" id="name" placeholder="Your Name" value="${auth.name}"/>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label>
                            <%-- Giữ lại giá trị đã nhập --%>
                            <input type="email" name="email" id="email" placeholder="Your Email"/>
                            <span id="email-error" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="password" placeholder="Your Password" value="${auth.password}"/>
                        </div>
                        <div class="form-group">
                            <label for="phone"><i class="zmdi zmdi-phone"></i></label>
                            <%-- Giữ lại giá trị đã nhập --%>
                            <input type="text" name="phone" id="phone" placeholder="Your Phone" value="${auth.phone}"/>
                            <span id="phone-error" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <label for="address"><i class="zmdi zmdi-address"></i></label>
                            <%-- Giữ lại giá trị đã nhập --%>
                            <input type="text" name="address" id="address" placeholder="Your Address" value="${auth.address}"/>
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
                    <a href="login.jsp" class="signup-image-link" id="show-signin">I am already a member</a>
                    <br>
                    <a href="index.jsp" class="signup-image-link">Back to Home Page</a>
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
            $(".sign-in").hide();
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

    });
</script>
</body>
</html>

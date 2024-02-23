<%-- 
    Document   : profile
    Created on : Jan 25, 2024, 5:06:56 PM
    Author     : NPB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="css/style_profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
<%@include file="includes/header.jsp" %>
</head>

<body>
    <br>
    <br>
    <br>
    <br>
    <form action="UpdateProfileServlet" method="post" onsubmit="return validatePhoneNumber();"> 
        <input type="hidden" value="${auth.userId}" name="id">
        <input type="hidden" value="${auth.password}" name="password">
        <input type="hidden" value="${auth.role}" name="role">
        
        <c:if test="${not empty failedMsg}">
            <h5 class="text-center text-danger">${failedMsg}</h5> 
            <c:remove var="failedMsg" scope="session" />
        </c:if>

        <c:if test="${not empty succMsg}">
            <h5 class="text-center text-success">${succMsg}</h5>
            <c:remove var="succMsg" scope="session" />
        </c:if>

    
    <div class="container light-style flex-grow-1 container-p-y">
        <h4 class="font-weight-bold py-3 mb-4" style="font-style: italic; font-family: Serif">
        
        </h4>
        <div class="card overflow-hidden">
            <div class="row no-gutters row-bordered row-border-light">
                <div class="col-md-3 pt-0">
                    <div class="list-group list-group-flush account-settings-links">
                        <a class="list-group-item list-group-item-action active" data-toggle="list"
                            href="#account-general">User Profile</a>
                        <a class="list-group-item list-group-item-action" 
                            href="Change-Password.jsp">Change Password</a>
                        <a class="list-group-item list-group-item-action" data-toggle="list"
                            href="#account-info"></a>
                        <a class="list-group-item list-group-item-action" data-toggle="list"
                            href="#account-social-links"></a>
                        <a class="list-group-item list-group-item-action" data-toggle="list"
                            href="#account-connections"></a>
                        <a class="list-group-item list-group-item-action" data-toggle="list"
                            href="#account-notifications"></a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade active show" id="account-general">
                            <%--<div class="card-body media align-items-center">
                                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxmVimnkn_TB3C495ZfgZD8qV0qz5MX88fsqL2Ow6qN0K4QWajKQJIiqbBbbfQMyT9lSA&usqp=CAU" alt
                                    class="d-block ui-w-80">
                                <div class="media-body ml-4">
                                    <label class="btn btn-outline-primary">
                                        Upload new photo
                                        <input type="file" class="account-settings-fileinput">
                                    </label> &nbsp;
                                    <div class="text-light small mt-1">Allowed JPG, GIF or PNG. Max size of 800K</div>
                                </div>
                            </div>--%>
                            <hr class="border-light m-0">
                            <div class="card-body">
                                <div class="form-group">
                                    <label class="form-label">Username</label>
                                    <input type="text" class="form-control mb-1" name="name" value="${auth.name}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">E-mail</label>
                                    <input type="text" class="form-control mb-1" name="email" value="${auth.email}" readonly>
                                    
                                    <%--<div class="alert alert-warning mt-3">
                                        Your email is not confirmed. Please check your inbox.<br>
                                        <a href="javascript:void(0)">Resend confirmation</a>
                                  </div> --%>
                                    
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Phone</label>
                                    <input type="text" class="form-control" name="phone" value="${auth.phone}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Address</label>
                                    <input type="text" class="form-control" name="address" value="${auth.address}">
                                </div>
                            </div>
                        </div>
                  
              
                        
                    </div>
                </div>
            </div>
        </div>
                              
        <div class="text-right mt-3">
            <input type="submit" class="btn btn-primary px-4" value="Save Changes">&nbsp;
            <a type="submit" href="index.jsp" style="color: black;" class="btn btn-default" >Back to Home Page</a>
        </div>

    </div>
    <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
            function validatePhoneNumber() {
                    var phoneNumber = document.getElementsByName("phone")[0].value;
                    // Regular expression to validate a phone number (assuming a simple format)
                    var phoneRegex = /^\d{10}$/;
                    if (!phoneRegex.test(phoneNumber)) {
                        alert("Please enter a valid 10-digit phone number.");
                        return false;
                    }
                    return true;
                }     
    </script>
   </form> 
</body>
</html>

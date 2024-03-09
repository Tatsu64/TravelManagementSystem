<%-- 
    Document   : View-Review-History
    Created on : Feb 28, 2024, 3:50:25 PM
    Author     : NPB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Review History</title>
    <link rel="stylesheet" href="css/style_profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%@include file="includes/header.jsp" %>
</head>
<body>
     <div class="container light-style flex-grow-1 container-p-y">   
        <div class="col-md-9">
            <div class="tab-content">
                <div class="tab-pane fade active show" id="account-general">
                    <hr class="border-light m-0">
                    <div class="card-body">
                        <h2 class="form-title">Review History</h2>
                        <c:if test="${not empty succMsg}">
                            <div class="alert alert-success" role="alert">
                                ${succMsg}
                            </div>
                            <c:remove var="succMsg" scope="request" />
                        </c:if>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Tour Name</th>
                                    <th>Comment</th>
                                    <th>Rating</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%-- Loop through the list of reviews and display each review details --%>
                                <c:forEach var="review" items="${reviews}">
                                    <tr>
                                        <td>${review.booking.tour.tourName}</td>
                                        <td>${review.content}</td>
                                        <td>${review.rating}</td>
                                        <td>
                                            <!-- Update button -->
                                            <button onclick="location.href='UpdateReviewServlet?action=update&reviewId=${review.reviewId}&action=update'" class="btn btn-primary">Edit</button>
                                            <!-- Delete button -->
                                            <button onclick="if(confirm('Are you sure you want to delete this review?')) location.href='UpdateReviewServlet?action=delete&reviewId=${review.reviewId}&action=delete'" type="button" class="btn btn-danger">Delete</button>                        
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <%-- Handle case when there are no reviews --%>
                        <c:if test="${empty reviews}">
                            <div class="alert alert-warning" role="alert">
                                No reviews found.
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="text-left mt-3">
                <a href="profile.jsp" class="btn btn-primary px4">Back To Profile</a>
            </div>
        </div>

   
    
    <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">

    </script>
</body>
</html>

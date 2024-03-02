<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Reviews</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <div class="container">
        <h2>Manage Reviews</h2>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Review ID</th>
                    <th scope="col">Content</th>
                    <th scope="col">Rating</th>
                    <th scope="col">Booking ID</th>
                    <th scope="col">Tour ID</th>
                    <th scope="col">Tour Name</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="review" items="${reviewList}">
                    <tr>
                        <td>${review.reviewId}</td>
                        <td>${review.content}</td>
                        <td>${review.rating}</td>
                        <td>${review.booking.bookingId}</td>
                        <td>${review.booking.tour.tourId}</td>
                        <td>${review.booking.tour.tourName}</td>
                        <td>
                            <!-- Delete button -->
                            <button onclick="if(confirm('Are you sure you want to delete this review?')) location.href='ManageReviewServlet?reviewId=${review.reviewId}&action=delete'" type="button" class="btn btn-danger">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="Home" class="btn btn-primary">Back to Homepage</a>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>

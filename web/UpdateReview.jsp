<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style_profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%@include file="includes/header.jsp" %>
    <title>Update Review</title>
<style> 
form { width: 50%; margin: 0 auto; padding: 2rem; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
input[type="text"], input[type="number"] { width: 100%; padding: 0.5rem; margin-bottom: 1rem; border-radius: 5px; border: 1px solid #ccc; }
input[type="text"]:invalid { border-color: red; }
input[type="number"]:invalid { border-color: red; }
input[type="submit"] { width: 100%; padding: 0.5rem; background-color: #86B817; color: white; border: none; border-radius: 5px; cursor: pointer; }
input[type="submit"]:hover { background-color: #86B817; }
a { width: 100%; padding: 0.5rem; background-color: #f8f9fa; color: #007bff; border: 1px solid #ccc; border-radius: 5px; text-align: center; text-decoration: none; display: block; } 
</style>
</head>
<body> 
    <br> 
    <h1 class="text-center mb-4">Update Review</h1> 
    <form action="UpdateReviewServlet" method="post"> 
        <input type="hidden" name="reviewId" value="${review.reviewId}" /> 
        <input type="hidden" name="bookingId" value="${review.booking.bookingId}" /> 
        
        <div class="form-group"> 
            <label for="tourname">Tour Name</label> 
            <input type="text" id="tourname" name="tourname" value="${review.booking.tour.tourName}" readonly class="form-control" /> 
        </div> 
        <div class="form-group"> 
            <label for="content">Comment</label> 
            <textarea type="text" id="content" name="content" class="form-control">${review.content}</textarea>
        </div> 
        <div class="form-group"> 
            <label for="rating">Rating</label> 
            <input type="number" id="rating" name="rating" value="${review.rating}" min="0" max="5" class="form-control" /> 
        </div> 
       <div class="form-group">
            <input type="submit" class="btn btn-primary px-4" value="Update Review" /> &nbsp;
            <a type="submit" href="ViewReviewServlet?" class="btn btn-gray-dark">Back to Review History</a> 
       </div> 
    </form> 
</body> 
</html>
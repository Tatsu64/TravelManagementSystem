<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form action="CreateTransportationServlet" method="get">

        <div class="container">
            <h2>Create a New Transportation</h2>

            <div class="form-group">
                <label for="transportationName">Transportation Name:</label>
                <input type="text" id="transportationName" name="transportationName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="departureDate">Departure Date:</label>
                <input type="date" id="departureDate" name="departureDate" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="returnDate">Return Date:</label>
                <input type="date" id="returnDate" name="returnDate" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="imageUrl">Image URL:</label>
                <input type="text" id="imageUrl" name="imageUrl" class="form-control" required>
            </div>

            <!-- Back to CreateTour.jsp button -->
            <a href="EmployeeListServlet?" class="btn btn-secondary">Back to Create Tour</a>
            <br>
            <br>
            <button type="submit" class="btn btn-dark">Create Transportation</button>
        </div>
    </form>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>

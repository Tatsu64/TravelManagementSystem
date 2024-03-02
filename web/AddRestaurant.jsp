<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <title>Create Restaurant</title>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <form action="ManageRestaurantServlet" method="post" enctype="multipart/form-data">
        <div class="container">
            <h2>Create Restaurant</h2>
            <br>
            <div class="form-group">
                <label for="restaurantName">Restaurant Name:</label>
                <input type="text" id="restaurantName" name="restaurantName" class="form-control">
            </div>
            <br>
            <label for="location">Select a location:</label>
            <select id="location" name="locationId" required>
                <c:forEach var="location" items="${locations}">
                    <option value="${location.locationId}">${location.locationName}</option>
                </c:forEach>
            </select><br><br>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" class="form-control" rows="3"></textarea>
            </div>
            <br>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="file" name="image" id="image">
            </div>

            <br>
            <br>
            <!-- Add Button -->
            <button type="submit" class="btn btn-primary">Add</button>

            <!-- Back Button -->
            <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
        </div>
    </form>
    <footer>
        <%@include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

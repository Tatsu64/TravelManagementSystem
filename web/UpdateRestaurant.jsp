<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <title>Update Restaurant</title>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Update Restaurant</h2>
        <form action="EditDeleteRestaurantServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="restaurantId" value="${restaurant.restaurantId}">
            <div class="form-group">
                <label for="restaurantName">Restaurant Name:</label>
                <input type="text" id="restaurantName" name="restaurantName" class="form-control" value="${restaurant.restaurantName}">
            </div>
            <br>
            <label for="location">Select a location:</label>
            <select id="location" name="locationId" required>
                <c:forEach var="location" items="${locations}">
                    <option value="${location.locationId}" <c:if test="${location.locationId == restaurant.location.locationId}">selected</c:if>>${location.locationName}</option>
                </c:forEach>
            </select><br><br>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" class="form-control" rows="3">${restaurant.address}</textarea>
            </div>
            <br>
            <div class="form-group">
                <label for="image">Current Image:</label>
                <img src="images/${restaurant.imageUrl}" alt="Hotel Image" width="100">
            </div>
            <br>
            <div class="form-group">
                <label for="image">New Image:</label>
                <input type="file" name="image" id="image">
            </div>
            <br>
            <br>
            <!-- Update Button -->
            <button type="submit" class="btn btn-primary">Update</button>

            <!-- Back Button -->
            <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
        </form>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

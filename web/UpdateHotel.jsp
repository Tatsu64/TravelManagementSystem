<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <title>Update Hotel</title>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Update Hotel</h2>
        <br>
        <form action="EditDeleteHotelServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" id="hotelId" name="hotelId" value="${hotel.hotelId}">
            <div class="form-group">
                <label for="hotelName">Hotel Name:</label>
                <input type="text" id="hotelName" name="hotelName" class="form-control" value="${hotel.hotelName}">
            </div>
            <br>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" class="form-control" rows="3">${hotel.address}</textarea>
            </div>
            <br>
            <div class="form-group">
                <label for="location">Location:</label>
                <select id="location" name="locationId" class="form-control">
                    <c:forEach var="location" items="${locations}">
                        <option value="${location.locationId}" ${location.locationId == hotel.location.locationId ? 'selected' : ''}>${location.locationName}</option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <div class="form-group">
                <label for="image">Current Image:</label>
                <img src="images/${hotel.imageUrl}" alt="Hotel Image" width="100">
            </div>
            <br>
            <div class="form-group">
                <label for="newImage">New Image:</label>
                <input type="file" name="image" id="newImage">
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
        <%@include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

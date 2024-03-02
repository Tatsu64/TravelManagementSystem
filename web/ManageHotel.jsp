<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel List</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Hotel List</h2>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Hotel ID</th>
                    <th scope="col">Hotel Name</th>
                    <th scope="col">Location</th>
                    <th scope="col">Address</th>
                    <th scope="col">Image</th>
                    <th scope="col">Action</th> 
                </tr>
            </thead>
            <tbody>
                <c:forEach var="hotel" items="${hotelList}">
                    <tr>
                        <input type="hidden" id="locationId" name="locationId" value="${hotel.location.locationId}">
                        <td>${hotel.hotelId}</td>
                        <td>${hotel.hotelName}</td>
                        <td>${hotel.location.locationName}</td>
                        <td>${hotel.address}</td>
                        <td><img src="images/${hotel.imageUrl}" alt="Hotel Image" width="100"></td>
                        <td>
                            <!-- Update button -->
                            <button onclick="location.href='EditDeleteHotelServlet?hotelId=${hotel.hotelId}&action=update'" type="button" class="btn btn-warning">Update</button>
                            <!-- Delete button -->
                            <button onclick="if(confirm('Are you sure you want to delete this hotel?')) location.href='EditDeleteHotelServlet?hotelId=${hotel.hotelId}&action=delete'" type="button" class="btn btn-danger">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <br>
        <a href="FormCreateHotelServlet?" class="btn btn-primary">Add Hotel</a>
        <a href="Home" class="btn btn-primary">Back to Homepage</a>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

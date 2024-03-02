<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant List</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Restaurant List</h2>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Restaurant ID</th>
                    <th scope="col">Restaurant Name</th>
                    <th scope="col">Location</th>
                    <th scope="col">Address</th>
                    <th scope="col">Image</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="restaurant" items="${restaurantList}">
                    <tr>
                        <td>${restaurant.restaurantId}</td>
                        <td>${restaurant.restaurantName}</td>
                        <td>${restaurant.location.locationName}</td>
                        <td>${restaurant.address}</td>
                        <td><img src="images/${restaurant.imageUrl}" alt="Restaurant Image" width="100"></td>
                        <td>
                            <!-- Update button -->
                            <button onclick="location.href='EditDeleteRestaurantServlet?restaurantId=${restaurant.restaurantId}&action=update'" type="button" class="btn btn-warning">Update</button>
                            <!-- Delete button -->
                            <button onclick="if(confirm('Are you sure you want to delete this restaurant?')) location.href='EditDeleteRestaurantServlet?restaurantId=${restaurant.restaurantId}&action=delete'" type="button" class="btn btn-danger">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <br>
        <a href="FormCreateRestaurantServlet?" class="btn btn-primary">Add Restaurant</a>
        <a href="Home" class="btn btn-primary">Back to Homepage</a>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant List</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <h2>Restaurant List</h2>
    <br>
    <br>
    <div class="container">
        <input type="hidden" name="locationId" value="${locationId}">
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Restaurant Name</th>
                    <th scope="col">Reservation Date</th>
                    <th scope="col">Price</th>
                    <th scope="col">Address</th>
                    <th scope="col">Image</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${restaurantList}" var="restaurant">
                    <tr>
                        <td>${restaurant.restaurantName}</td>
                        <td>${restaurant.reservationDate}</td>
                        <td>${restaurant.price}</td>
                        <td>${restaurant.address}</td>
                        <td><img src="${restaurant.imageUrl}" alt="Restaurant Image" width="100"></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="AddRestaurant.jsp?locationId=${param.locationId}" class="btn btn-primary">Add Restaurant</a>
        <br>
        <br>
        <a href="index.jsp" class="btn btn-secondary">Finish</a>
    </div>
    <%@include file="includes/footer.jsp" %>
</body>
</html>

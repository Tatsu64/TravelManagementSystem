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
    
    <br>
    <br>
    <div class="container">
        <h2>Restaurant List</h2>
        <form action="RestaurantServlet" method="post"> <!-- AddRestaurantServlet is the servlet handling the form submission -->
            <input type="hidden" name="locationId" value="${locationId}">
            <input type="hidden" name="tourId" value="${tourId}">
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Select</th>
                        <th scope="col">Restaurant Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Image</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${restaurantList}" var="restaurant">
                        <tr>
                            <td><input type="checkbox" name="selectedRestaurants" value="${restaurant.restaurantId}"></td>
                            <td>${restaurant.restaurantName}</td>
                            <td>${restaurant.address}</td>
                            <td><img src="${restaurant.imageUrl}" alt="Restaurant Image" width="100"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Next</button>
        </form>
        <br>
        <br>
    </div>
    <footer>
        <%@include file="includes/footer.jsp" %>
    </footer>
</body>

</html>

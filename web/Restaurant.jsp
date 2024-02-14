<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant List</title>
    <%@include file="includes/header.jsp" %>
    <style>
        h2 {
            margin-left: 5%;
        }
        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #f8f9fa;
            padding: 20px 0;
            text-align: center;
        }
        .hotel-image {
            max-width: 100px; /* Độ rộng tối đa của hình ảnh */
            height: auto; /* Độ cao tự động tính dựa trên chiều rộng */
        }
    </style>
</head>
<body>
    <br>
    <br>
    <br>
    <h2>Restaurant List</h2>
    <br>
    <br>
    <div class="container">
        <form action="RestaurantServlet" method="post"> <!-- AddRestaurantServlet is the servlet handling the form submission -->
            <input type="hidden" name="locationId" value="${locationId}">
            <input type="hidden" name="tourId" value="${tourId}">
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Select</th>
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
                            <td><input type="checkbox" name="selectedRestaurants" value="${restaurant.restaurantId}"></td>
                            <td>${restaurant.restaurantName}</td>
                            <td>${restaurant.reservationDate}</td>
                            <td>${restaurant.price}</td>
                            <td>${restaurant.address}</td>
                            <td><img src="${restaurant.imageUrl}" alt="Restaurant Image" width="100"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Finish</button>
        </form>
        <br>
        <br>
    </div>
    <footer>
        <%@include file="includes/footer.jsp" %>
    </footer>
</body>

</html>

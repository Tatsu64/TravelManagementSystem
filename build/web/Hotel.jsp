<%-- 
    Document   : HotelServlet
    Created on : Feb 13, 2024, 11:30:53 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel List</title>
    <%@include file="includes/header.jsp" %>
    
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Hotel List</h2>
        <form action="HotelServlet" method="post"> <!-- Form để gửi dữ liệu khách sạn đã chọn -->
            <input type="hidden" name="locationId" value="${locationId}">
            <input type="hidden" name="tourId" value="${tourId}">
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Select</th> <!-- Cột chứa hộp kiểm -->
                        <th scope="col">Hotel Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Image</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${hotelList}" var="hotel">
                        <tr>
                            <td><input type="checkbox" name="selectedHotels" value="${hotel.hotelId}"></td> <!-- Hộp kiểm cho mỗi khách sạn -->
                            <td>${hotel.hotelName}</td>
                            <td>${hotel.address}</td>
                            <td><img src="${hotel.imageUrl}" alt="Hotel Image" class="hotel-image"></td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Next</button> <!-- Nút để gửi dữ liệu khi đã chọn -->
        </form>

        <br>
        <br>
    </div>
    <footer>
        <%@include file="includes/footer.jsp" %>
    </footer>
</body>

</html>

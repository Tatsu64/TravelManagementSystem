<%-- 
    Document   : AddHotel
    Created on : Feb 13, 2024, 10:34:24 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Hotel</title>
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
    <br>
    <br>
    <form action="HotelServlet" method="post">
        <div class="container">
            <input type="hidden" id="locationId" name="locationId" value="${param.locationId}">
            <div class="form-group">
                <label for="hotelName">Hotel Name:</label>
                <input type="text" id="hotelName" name="hotelName" class="form-control">
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" class="form-control">
            </div>
            <div class="form-group">
                <label for="imageUrl">Image URL:</label>
                <input type="text" id="imageUrl" name="imageUrl" class="form-control">
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" class="form-control" rows="3"></textarea>
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

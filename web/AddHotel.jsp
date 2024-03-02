<%-- 
    Document   : AddHotel
    Created on : Feb 13, 2024, 10:34:24 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes/header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Hotel</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <br>
        <form action="ManageHotelsServlet" method="post" enctype="multipart/form-data">
            <div class="container">
                <h2>Create Hotel</h2>
                <br>
                <div class="form-group">
                    <label for="hotelName">Hotel Name:</label>
                    <input type="text" id="hotelName" name="hotelName" class="form-control">
                </div>
                <br>
                <label for="locationId">Select a location:</label>
                <select id="locationId" name="locationId" required>
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

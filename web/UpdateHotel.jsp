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
        <form id="updateHotelForm" action="EditDeleteHotelServlet" method="post" enctype="multipart/form-data">
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
    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Success Modal -->
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Success!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Hotel details updated successfully.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        // Show success modal after form submission
        document.getElementById("updateHotelForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent default form submission
            $('#successModal').modal('show'); // Show success modal
            // Optional: Submit the form after a delay if needed
             setTimeout(function() {
                 document.getElementById("updateHotelForm").submit();
             }, 2000); // Submit after 2 seconds
        });
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <title>Update Restaurant</title>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Update Restaurant</h2>
        <form id="updateRestaurantForm" action="EditDeleteRestaurantServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="restaurantId" value="${restaurant.restaurantId}">
            <div class="form-group">
                <label for="restaurantName">Restaurant Name:</label>
                <input type="text" id="restaurantName" name="restaurantName" class="form-control" value="${restaurant.restaurantName}">
            </div>
            <br>
            <label for="location">Select a location:</label>
            <select id="location" name="locationId" class="form-control">
                <c:forEach var="location" items="${locations}">
                    <option value="${location.locationId}" ${location.locationId == restaurant.location.locationId ? 'selected' : ''}>${location.locationName}</option>
                </c:forEach>
            </select><br><br>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" class="form-control" rows="3">${restaurant.address}</textarea>
            </div>
            <br>
            <div class="form-group">
                <label for="image">Current Image:</label>
                <img src="images/${restaurant.imageUrl}" alt="Restaurant Image" width="100">
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

    <!-- Success Modal -->
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Update Successful</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Restaurant details have been successfully updated.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#updateRestaurantForm').submit(function(event) {
                event.preventDefault();
                $('#successModal').modal('show');
                 setTimeout(function() {
                 document.getElementById("updateRestaurantForm").submit();
             }, 2000); 
            });
        });
    </script>
</body>
</html>

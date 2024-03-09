<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Location</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Update Location</h2>
        <form id="updateLocationForm" action="EditDeleteLocationServlet" method="post">
            <input type="hidden" name="locationId" value="${location.locationId}">
            <div class="form-group">
                <label for="locationName">Location Name:</label>
                <input type="text" class="form-control" id="locationName" name="locationName" value="${location.locationName}" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Update</button>
            <button type="button" id="backButton" class="btn btn-secondary">Back</button>
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
                    Location details updated successfully.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        // Show success modal after form submission
        document.getElementById("updateLocationForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent default form submission
            $('#successModal').modal('show');
              setTimeout(function() {
                 document.getElementById("updateLocationForm").submit();
             }, 2000); 
        });

        // Back button functionality
        document.getElementById("backButton").addEventListener("click", function(event) {
            event.preventDefault(); // Prevent default behavior
            history.back(); // Go back to the previous page
        });
    </script>
</body>
</html>

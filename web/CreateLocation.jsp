<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Location</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Create New Location</h2>
        <form id="locationForm" action="LocationServlet" method="post">
            <div class="form-group">
                <label for="locationName">Location Name:</label>
                <input type="text" class="form-control" id="locationName" name="locationName" required>
            </div>
            <br> <br>
            <button type="submit" class="btn btn-primary">Create</button>
            <a href="LocationServlet?" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
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
                    Location created successfully.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Show success modal after form submission
        document.getElementById("locationForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent default form submission
            $('#successModal').modal('show'); // Show success modal
            // Optional: You can submit the form after a delay if needed
             setTimeout(function() {
                 document.getElementById("locationForm").submit();
             }, 2000); // Submit after 2 seconds
        });
    </script>
</body>
</html>

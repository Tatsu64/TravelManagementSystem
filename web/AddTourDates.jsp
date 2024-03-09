<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Tour Dates</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Create Tour Dates</h2>
        <form id="tourDatesForm" action="TourDatesServlet" method="post">
            <input type="hidden" name="update" value="${param.update}">
            <input type="hidden" name="tourId" value="${param.tourId}">
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required>
            </div>
            <div class="form-group">
                <label for="currentCapacity">Current Capacity:</label>
                <input type="number" class="form-control" id="currentCapacity" name="currentCapacity" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
        <br>
         <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
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
                    Tour dates created successfully.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</body>
<!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Show success modal after form submission
    document.getElementById("tourDatesForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission
        $('#successModal').modal('show'); // Show success modal
        // Optional: You can submit the form after a delay if needed
         setTimeout(function() {
             document.getElementById("tourDatesForm").submit();
         }, 2000); // Submit after 2 seconds
    });
</script>
</html>

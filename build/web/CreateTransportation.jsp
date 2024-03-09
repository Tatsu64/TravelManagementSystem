<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form id="transportationForm" action="CreateTransportationServlet" method="post" enctype="multipart/form-data">

        <div class="container">
            <h2>Create a New Transportation</h2>

            <div class="form-group">
                <label for="transportationName">Transportation Name:</label>
                <input type="text" id="transportationName" name="transportationName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="departureTime">Departure Time:</label>
                <input type="time" id="departureTime" name="departureTime" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="returnTime">Return Time:</label>
                <input type="time" id="returnTime" name="returnTime" class="form-control" required>
            </div>
            <br>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="file" name="image" id="image">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Create Transportation</button>
            <a href="TransportationServlet?" class="btn btn-secondary">Cancel</a>
        </div>
    </form>

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
                    Transportation created successfully.
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
        document.getElementById("transportationForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent default form submission
            $('#successModal').modal('show'); // Show success modal
            // Optional: You can submit the form after a delay if needed
             setTimeout(function() {
                 document.getElementById("transportationForm").submit();
             }, 2000); // Submit after 2 seconds
        });
    </script>
</body>
</html>

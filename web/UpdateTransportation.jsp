<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transportation Detail</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Transportation Detail</h2>
        <form id="updateTransportationForm" action="EditDeleteTransportationServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="transportationId" value="${transportation.transportationId}">
            <div class="form-group">
                <label for="transportationName">Transportation Name:</label>
                <input type="text" class="form-control" id="transportationName" name="transportationName" value="${transportation.transportationName}" required>
            </div>
            <div class="form-group">
                <label for="departureTime">Departure Time:</label>
                <input type="time" class="form-control" id="departureTime" name="departureTime" value="${transportation.departureTime}" required>
            </div>
            <div class="form-group">
                <label for="returnTime">Return Time:</label>
                <input type="time" class="form-control" id="returnTime" name="returnTime" value="${transportation.returnTime}" required>
            </div>
            <div class="form-group">
                <label for="image">Current Image:</label><br>
                <img src="images/${transportation.imageUrl}" alt="Transportation Image" width="100">
            </div>
            <br>
            <div class="form-group">
                <label for="image">New Image:</label>
                <input type="file" name="image" id="image">
            </div>
            <br>
            <br>
            <button type="submit" class="btn btn-primary">Update</button>
            <a href="TransportationServlet?" class="btn btn-secondary">Cancel</a>
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
                    Transportation details have been successfully updated.
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
            $('#updateTransportationForm').submit(function(event) {
                event.preventDefault();
                $('#successModal').modal('show');
                setTimeout(function() {
                    document.getElementById("updateTransportationForm").submit();
                }, 2000); 
            });
        });
    </script>
</body>
</html>

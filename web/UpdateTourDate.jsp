<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <title>Update Tour Date</title>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Update Tour Date</h2>
        <form id="updateTourDateForm" action="EditDeleteTourDatesServlet" method="post">
            <input type="hidden" name="tourId" value="${param.tourId}">
            <input type="hidden" name="tourDateId" value="${param.tourDateId}">
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" class="form-control" id="startDate" name="startDate" value="${tourDates.startDate}" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" class="form-control" id="endDate" name="endDate" value="${tourDates.endDate}" required>
            </div>
            <div class="form-group">
                <label for="currentCapacity">Current Capacity:</label>
                <input type="number" class="form-control" id="currentCapacity" name="currentCapacity" value="${tourDates.currentCapacity}" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Update</button>
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
                    Tour Date details have been successfully updated.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#updateTourDateForm').submit(function(event) {
                event.preventDefault();
                $('#successModal').modal('show');
                setTimeout(function() {
                    document.getElementById("updateTourDateForm").submit();
                }, 2000); 
            });
        });
    </script>
</body>
</html>

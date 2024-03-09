<%-- 
    Document   : UpdateActivitySchedule
    Created on : Feb 13, 2024, 10:34:24 PM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Activity Schedule</title>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<form id="updateActivityForm" action="EditDeleteActivityScheduleServlet" method="post" onsubmit="return validateForm()">
    <div class="container">
        <input type="hidden" name="scheduleId" value="${param.scheduleId}">
        <input type="hidden" id="tourId" name="tourId" value="${param.tourId}">

        <div class="form-group">
            <label for="dayNumber">Day Number:</label>
            <input type="number" id="dayNumber" name="dayNumber" class="form-control" value="${activitySchedule.dayNumber}" required>
        </div>
        <div class="form-group">
            <label for="activityName">Activity Name:</label>
            <input type="text" id="activityName" name="activityName" class="form-control" value="${activitySchedule.activityName}">
        </div>
        <div class="form-group">
            <label for="startTime">Start Time:</label>
            <input type="time" id="startTime" name="startTime" class="form-control" value="${activitySchedule.startTime}">
        </div>
        <div class="form-group">
            <label for="endTime">End Time:</label>
            <input type="time" id="endTime" name="endTime" class="form-control" value="${activitySchedule.endTime}">
        </div>
        <div class="form-group">
            <label for="location">Location:</label>
            <input type="text" id="location" name="location" class="form-control" value="${activitySchedule.location}">
        </div>
        <div class="form-group">
            <label for="activityDescription">Activity Description:</label>
            <textarea id="activityDescription" name="activityDescription" class="form-control" rows="3">${activitySchedule.description}</textarea>
        </div>
        <br>
        <br>
        <!-- Update Button -->
        <button type="submit" class="btn btn-primary">Update</button>

        <!-- Back Button -->
        <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
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
                Activity schedule updated successfully.
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
    function validateForm() {
        var startTime = document.getElementById("startTime").value;
        var endTime = document.getElementById("endTime").value;

        // Convert time to hours and minutes
        var startTimeParts = startTime.split(":");
        var endTimeParts = endTime.split(":");
        var startHour = parseInt(startTimeParts[0]);
        var startMinute = parseInt(startTimeParts[1]);
        var endHour = parseInt(endTimeParts[0]);
        var endMinute = parseInt(endTimeParts[1]);

        // Check if end time is after start time
        if (endHour < startHour || (endHour === startHour && endMinute <= startMinute)) {
            alert("End time must be after start time.");
            return false; // Prevent form submission
        }
        return true; // Allow form submission if time is valid
    }

    // Show success modal after form submission
    document.getElementById("updateActivityForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission
        if (validateForm()) {
            $('#successModal').modal('show'); // Show success modal
            // Optional: Submit the form after a delay if needed
             setTimeout(function() {
                 document.getElementById("updateActivityForm").submit();
             }, 2000); // Submit after 2 seconds
        }
    });
</script>
</body>
</html>

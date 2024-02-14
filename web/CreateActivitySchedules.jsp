<%-- 
    Document   : CreateActivitySchedules
    Created on : Feb 13, 2024, 10:34:24 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes/header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Activity Schedule</title>
    </head>
    <body>
         <%@ include file="includes/navbar.jsp" %>
        <br>
        <br>
        <br>
        <br>
        <br>
        <form action="ActivityScheduleServlet" method="post">
            <div class="container">
                <input type="hidden" name="locationId" value="${param.locationId}">
                <input type="hidden" id="tourId" name="tourId" value="${param.tourId}">

            <div class="form-group">
                <label for="dayNumber">Day Number:</label>
                <input type="number" id="dayNumber" name="dayNumber" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="activityName">Activity Name:</label>
                <input type="text" id="activityName" name="activityName" class="form-control">
            </div>
            <div class="form-group">
                <label for="activityDate">Activity Date:</label>
                <input type="date" id="activityDate" name="activityDate" class="form-control">
            </div>
            <div class="form-group">
                <label for="startTime">Start Time:</label>
                <input type="time" id="startTime" name="startTime" class="form-control">
            </div>
            <div class="form-group">
                <label for="endTime">End Time:</label>
                <input type="time" id="endTime" name="endTime" class="form-control">
            </div>
            <div class="form-group">
                <label for="location">Location:</label>
                <input type="text" id="location" name="location" class="form-control">
            </div>
            <div class="form-group">
                <label for="activityDescription">Activity Description:</label>
                <textarea id="activityDescription" name="activityDescription" class="form-control" rows="3"></textarea>
            </div>
            <br>
            <br>
            <!-- Add Button -->
            <button type="submit" class="btn btn-primary">Add</button>

            <!-- Back Button -->
            <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
            </div>
        </form>
            <%@ include file="includes/footer.jsp" %>
    </body>
</html>

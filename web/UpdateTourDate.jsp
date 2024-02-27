<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Tour Date</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Update Tour Date</h2>
        <form action="EditDeleteTourDatesServlet" method="post">
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
        </form>
        <br>
         <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

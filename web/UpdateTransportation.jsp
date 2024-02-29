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
        <form action="EditDeleteTransportationServlet" method="post" enctype="multipart/form-data">
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
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

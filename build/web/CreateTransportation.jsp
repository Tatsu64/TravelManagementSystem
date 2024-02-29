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
    <form action="CreateTransportationServlet" method="post" enctype="multipart/form-data" ">

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
    <%@ include file="includes/footer.jsp" %>
 

</body>
</html>

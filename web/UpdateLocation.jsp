<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Location</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Update Location</h2>
        <form action="EditDeleteLocationServlet" method="post">
            <input type="hidden" name="locationId" value="${location.locationId}">
            <div class="form-group">
                <label for="locationName">Location Name:</label>
                <input type="text" class="form-control" id="locationName" name="locationName" value="${location.locationName}" required>
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

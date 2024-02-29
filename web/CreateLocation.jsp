<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Location</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>Create New Location</h2>
        <form action="LocationServlet" method="post">
            <div class="form-group">
                <label for="locationName">Location Name:</label>
                <input type="text" class="form-control" id="locationName" name="locationName" required>
            </div>
            <br> <br>
            <button type="submit" class="btn btn-primary">Create</button>
            <a href="LocationServlet?" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <br>
    <br>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>

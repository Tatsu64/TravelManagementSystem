<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Locations</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>List of Locations</h2>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Location ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${locations}" var="location">
                    <tr>
                        <td>${location.locationId}</td>
                        <td>${location.locationName}</td>
                        <td>
                            <a href="EditDeleteLocationServlet?locationId=${location.locationId}&action=update" class="btn btn-warning">Update</a>
                            <a href="EditDeleteLocationServlet?locationId=${location.locationId}&action=delete" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this location?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
            <br>
            <a href="CreateLocation.jsp" class="btn btn-primary">Create Location</a>
            <a href="Home" class="btn btn-primary">Back to Homepage</a>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

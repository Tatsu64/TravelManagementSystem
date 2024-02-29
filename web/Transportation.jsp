<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transportation List</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <br>
        <br>
        <br>
        <div class="container">
            <h2>Transportation List</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Transportation ID</th>
                        <th scope="col">Transportation Name</th>
                        <th scope="col">Departure Time</th>
                        <th scope="col">Return Time</th>
                        <th scope="col">Image</th>
                        <th scope="col">Action</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="transportation" items="${transportationList}">
                        <tr>
                            <td>${transportation.transportationId}</td>
                            <td>${transportation.transportationName}</td>
                            <td>${transportation.departureTime}</td>
                            <td>${transportation.returnTime}</td>
                            <td><img src="images/${transportation.imageUrl}" alt="Transportation Image" width="100"></td>
                            <td>
                            <!-- Update button -->
                            <button onclick="location.href='EditDeleteTransportationServlet?transportationId=${transportation.transportationId}&action=update'" type="button" class="btn btn-warning">Update</button>
                            <!-- Delete button -->
                            <button onclick="if(confirm('Are you sure you want to delete this transportation?')) location.href='EditDeleteTransportationServlet?transportationId=${transportation.transportationId}&action=delete'" type="button" class="btn btn-danger">Delete</button>
                        </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            <a href="CreateTransportation.jsp" class="btn btn-primary">Add Transportation</a>
            <a href="Home" class="btn btn-primary">Back to Homepage</a>
        </div>
        <footer>
            <%@ include file="includes/footer.jsp" %>
        </footer>
    </body>
</html>

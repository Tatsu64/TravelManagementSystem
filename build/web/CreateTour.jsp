<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form action="CreateTourServlet" method="get">

        <div class="container">
            <h2>Create a New Tour</h2>
            
            <div class="form-group">
                <label for="locationName">Location Name:</label>
                <input type="text" id="locationName" name="locationName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="tourName">Tour Name:</label>
                <input type="text" id="tourName" name="tourName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control" rows="3" required></textarea>
            </div>

            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="tourPrice">Tour Price:</label>
                <input type="text" id="tourPrice" name="tourPrice" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="imageUrl">Image URL:</label>
                <input type="text" id="imageUrl" name="imageUrl" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="employeeId">Employee:</label>
                <select id="employeeId" name="employeeId" class="form-control" required>
                    <c:forEach items="${employeeList}" var="employee">
                        <option value="${employee.employeeId}">${employee.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="startLocation">Start Location:</label>
                <input type="text" id="startLocation" name="startLocation" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="maxCapacity">Max Capacity:</label>
                <input type="text" id="maxCapacity" name="maxCapacity" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="currentCapacity">Current Capacity:</label>
                <input type="text" id="currentCapacity" name="currentCapacity" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="selectedTransportations">Select Transportations:</label>
                <c:forEach items="${transportationList}" var="transportation">
                    <div class="form-check">
                        <input type="checkbox" id="transportation${transportation.transportationId}" name="selectedTransportations[]" value="${transportation.transportationId}" class="form-check-input">
                        <label for="transportation${transportation.transportationId}" class="form-check-label">${transportation.transportationName}</label>
                    </div>
                </c:forEach>
            </div>


            <br>
            <a href="CreateTransportation.jsp" class="btn btn-secondary">Add Transportation</a>
            <br>
            <br>
            <button type="submit" class="btn btn-dark">Create Tour</button>
            <a href="index.jsp" class="btn btn-secondary">Back to HomePage</a>
        </div>
    </form>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>

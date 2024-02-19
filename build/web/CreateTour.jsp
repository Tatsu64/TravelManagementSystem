<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes/header.jsp" %>
        <title>Create Tour</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <br>
        <br>
        <form action="CreateTourServlet" method="post" enctype="multipart/form-data">

            <div class="container">
                <h2>Create a New Tour</h2>
                <br>
                <br>
                <h2>Select Transportations:</h2>
                <div id="transportationContainer">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Transportation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${transportationList}" var="transportation" varStatus="loop">
                                <c:if test="${loop.index < 4}">
                                    <tr>
                                        <td>
                                            <div class="form-check">
                                                <input type="checkbox" id="transportation${transportation.transportationId}" name="selectedTransportations[]" value="${transportation.transportationId}" class="form-check-input">
                                                <label for="transportation${transportation.transportationId}" class="form-check-label">${transportation.transportationName}</label>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <c:if test="${transportationList.size() > 4}">
                    <button type="button" id="showMoreBtn" onclick="showMore()">Show More</button>
                    <button type="button" id="showLessBtn" onclick="showLess()" style="display: none;">Show Less</button>
                </c:if>
                <br>
                <br>
                <a href="CreateTransportation.jsp" class="btn btn-secondary">Add Transportation</a>
                <br>
                <br>

                <label for="location">Select a location:</label>
                <select id="location" name="locationId" required>
                    <c:forEach var="location" items="${locations}">
                        <option value="${location.locationId}">${location.locationName}</option>
                    </c:forEach>
                </select><br><br>

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
                <br>

                <div class="form-group">
                    <label for="image">Image:</label>
                    <input type="file" name="image" id="image">
                </div>
                <br>

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
                <br>
                <br>
                <button type="submit" class="btn btn-dark">Create Tour</button>
                <a href="index.jsp" class="btn btn-secondary">Back to HomePage</a>
            </div>
        </form>
        <%@ include file="includes/footer.jsp" %>
        <script>
            function showMore() {
                var container = document.getElementById('transportationContainer');
                var showMoreBtn = document.getElementById('showMoreBtn');
                var showLessBtn = document.getElementById('showLessBtn');
                var html = '';

            <c:forEach items="${transportationList}" var="transportation" begin="4" varStatus="loop">
                html += '<tr><td><div class="form-check">';
                html += '<input type="checkbox" id="transportation${transportation.transportationId}" name="selectedTransportations[]" value="${transportation.transportationId}" class="form-check-input">';
                html += '<label for="transportation${transportation.transportationId}" class="form-check-label">${transportation.transportationName}</label>';
                html += '</div></td></tr>';
            </c:forEach>

                container.querySelector('tbody').innerHTML += html;
                showMoreBtn.style.display = 'none';
                showLessBtn.style.display = 'inline';
            }

            function showLess() {
                var container = document.getElementById('transportationContainer');
                var showMoreBtn = document.getElementById('showMoreBtn');
                var showLessBtn = document.getElementById('showLessBtn');

                container.querySelector('tbody').innerHTML = '';

            <c:forEach items="${transportationList}" var="transportation" varStatus="loop">
                <c:if test="${loop.index < 4}">
                var html = '<tr><td><div class="form-check">';
                html += '<input type="checkbox" id="transportation${transportation.transportationId}" name="selectedTransportations[]" value="${transportation.transportationId}" class="form-check-input">';
                html += '<label for="transportation${transportation.transportationId}" class="form-check-label">${transportation.transportationName}</label>';
                html += '</div></td></tr>';
                container.querySelector('tbody').innerHTML += html;
                </c:if>
            </c:forEach>

                showMoreBtn.style.display = 'inline';
                showLessBtn.style.display = 'none';
            }
        </script>
    </body>
</html>

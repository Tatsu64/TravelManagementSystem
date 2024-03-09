<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes/header.jsp" %>
        <title>Update Tour</title>
    </head>
    <body>
        <br>
        <br>
         <form id="updateTourForm" action="EditDeleteTourServlet" method="post" enctype="multipart/form-data">
            <div class="container">
                <h2>Update Tour</h2>
                <br>
                <br>
                <input type="hidden" name="tourId" value="${tour.tourId}">
                <h3>Transportation</h3>
                <c:forEach items="${transportationList}" var="transportation">
                    <c:set var="isChecked" value="false" />
                    <c:forEach items="${selectedTransportations}" var="selectedTransportation">
                        <c:if test="${transportation.transportationId eq selectedTransportation.transportationId}">
                            <c:set var="isChecked" value="true" />
                        </c:if>
                    </c:forEach>
                    <div class="form-check">
                        <input type="checkbox" id="transportation${transportation.transportationId}" name="selectedTransportations[]" value="${transportation.transportationId}" class="form-check-input" ${isChecked ? 'checked' : ''}>
                        <label for="transportation${transportation.transportationId}" class="form-check-label">${transportation.transportationName}</label>
                    </div>
                </c:forEach>

                <div class="form-group">
                    <label for="tourName">Tour Name:</label>
                    <input type="text" id="tourName" name="tourName" class="form-control" value="${tour.tourName}" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" class="form-control" rows="3" required>${tour.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="tourPrice">Tour Price:</label>
                    <input type="text" id="tourPrice" name="tourPrice" class="form-control" value="${tour.tourPrice}" required>
                </div>
                <br>

                <div class="form-group">
                    <label for="image">Current Image:</label><br>
                    <img src="images/${tour.imageUrl}" alt="Tour Image" width="100">
                </div>
                <br>

                <div class="form-group">
                    <label for="image">New Image:</label>
                    <input type="file" name="image" id="image">
                </div>
                <br>

                <div class="form-group">
                    <label for="employeeId">Employee:</label>
                    <select id="employeeId" name="employeeId" class="form-control" required>
                        <c:forEach items="${employeeList}" var="employee">
                            <option value="${employee.employeeId}" ${employee.employeeId eq tour.employee.employeeId ? 'selected' : ''}>${employee.fullName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="startLocation">Start Location:</label>
                    <input type="text" id="startLocation" name="startLocation" class="form-control" value="${tour.startLocation}" required>
                </div>

                <div class="form-group">
                    <label for="maxCapacity">Max Capacity:</label>
                    <input type="text" id="maxCapacity" name="maxCapacity" class="form-control" value="${tour.maxCapacity}" required>
                </div>

                <h3>Activity Schedules</h3>
                <table class="table table-light">
                    <thead>
                        <tr>
                            <th scope="col">Day Number</th>
                            <th scope="col">Activity Name</th>
                            <th scope="col">Location</th>
                            <th scope="col">Action</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${activityScheduleList}" var="schedule">
                        <input type="hidden" name="scheduleId" value="${schedule.scheduleId}">
                        <tr>
                            <td>${schedule.dayNumber}</td>
                            <td>${schedule.activityName}</td>
                            <td>${schedule.location}</td>
                            <td>
                                <button onclick="location.href = 'EditDeleteActivityScheduleServlet?scheduleId=${schedule.scheduleId}&action=update&tourId=${tour.tourId}'" type="button" class="btn btn-warning">Update</button>
                                <button onclick="if (confirm('Are you sure you want to delete this activity?')) window.location.href = 'EditDeleteActivityScheduleServlet?scheduleId=${schedule.scheduleId}&action=delete&tourId=${tour.tourId}'" type="button" class="btn btn-danger">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button onclick="location.href = 'CreateActivitySchedules.jsp?tourId=${tour.tourId}&update=1';" type="button" class="btn btn-primary">Add Activity Schedule</button>
                <br>
                <br>

                <h3>Tour Dates</h3>
                <table class="table table-light">
                    <thead>
                        <tr>
                            <th scope="col">Start Date</th>
                            <th scope="col">End Date</th>
                            <th scope="col">Current Capacity</th>
                            <th scope="col">Action</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${tourDatesList}" var="tourDate">
                            <tr>
                                <td>${tourDate.startDate}</td>
                                <td>${tourDate.endDate}</td>
                                <td>${tourDate.currentCapacity}</td>
                                <td>
                                <button onclick="location.href = 'EditDeleteTourDatesServlet?tourDateId=${tourDate.tourDateId}&action=update&tourId=${tour.tourId}'" type="button" class="btn btn-warning">Update</button>
                                <button onclick="if (confirm('Are you sure you want to delete this tour date?')) window.location.href = 'EditDeleteTourDatesServlet?tourDateId=${tourDate.tourDateId}&action=delete&tourId=${tour.tourId}'" type="button" class="btn btn-danger">Delete</button>
                            </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button onclick="location.href = 'AddTourDates.jsp?tourId=${tour.tourId}&update=1';" type="button" class="btn btn-primary">Add Tour Date</button>
                <br>
                <br>

                <!-- For Hotels -->
                <h3>Hotel</h3>
                <c:forEach items="${hotelList}" var="hotel">
                    <c:set var="isChecked" value="false" />
                    <c:forEach items="${selectedHotels}" var="selectedHotel">
                        <c:if test="${hotel.hotelId eq selectedHotel.hotelId}">
                            <c:set var="isChecked" value="true" />
                        </c:if>
                    </c:forEach>
                    <div class="form-check">
                        <input type="checkbox" id="hotel${hotel.hotelId}" name="selectedHotels[]" value="${hotel.hotelId}" class="form-check-input" ${isChecked ? 'checked' : ''}>
                        <label for="hotel${hotel.hotelId}" class="form-check-label">${hotel.hotelName}</label>
                    </div>
                </c:forEach>

                <!-- For Restaurants -->
                <h3>Restaurant</h3>
                <c:forEach items="${restaurantList}" var="restaurant">
                    <c:set var="isChecked" value="false" />
                    <c:forEach items="${selectedRestaurants}" var="selectedRestaurant">
                        <c:if test="${restaurant.restaurantId eq selectedRestaurant.restaurantId}">
                            <c:set var="isChecked" value="true" />
                        </c:if>
                    </c:forEach>
                    <div class="form-check">
                        <input type="checkbox" id="restaurant${restaurant.restaurantId}" name="selectedRestaurants[]" value="${restaurant.restaurantId}" class="form-check-input" ${isChecked ? 'checked' : ''}>
                        <label for="restaurant${restaurant.restaurantId}" class="form-check-label">${restaurant.restaurantName}</label>
                    </div>
                </c:forEach>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">Update Tour</button>
                <a href="ApprovalTourServlet?" class="btn btn-secondary">Cancel</a>
                <br>
                <br>
                <br>
                <br>
            </div>
        </form>
         <!-- Success Modal -->
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Update Successful</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Tour details have been successfully updated.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#updateTourForm').submit(function(event) {
                event.preventDefault();
                $('#successModal').modal('show');
                setTimeout(function() {
                 document.getElementById("updateTourForm").submit();
             }, 2000); 
            });
        });
    </script>
    </body>
</html>

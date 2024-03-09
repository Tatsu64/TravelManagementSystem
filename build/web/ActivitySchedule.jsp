<%-- 
    Document   : ActivitySchedule
    Created on : Feb 13, 2024, 11:30:53 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Activity Schedules</title>
        <%@include file="includes/header.jsp" %>
        
    </head>
    <body>
        <div class="container">
            <br>
            <br>
            <h2>Activity Schedules</h2>
            <input type="hidden" name="locationId" value="${locationId}">
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Day Number</th>
                        <th scope="col">Activity Name</th>
                        <th scope="col">Location</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${activityScheduleList}" var="schedule">
                        <tr>
                            <td>${schedule.dayNumber}</td>
                            <td>${schedule.activityName}</td>
                            <td>${schedule.location}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button onclick="location.href='CreateActivitySchedules.jsp?tourId=${tourId}&update=0&locationId=${locationId}';" type="button" class="btn btn-primary">Add Activity Schedule</button>
            <br>
            <br>
           <a href="HotelServlet?tourId=${tourId}&locationId=${locationId}" class="btn btn-secondary">Next</a>
        </div>
        <footer>
            <%@include file="includes/footer.jsp" %>
        </footer>
    </body>
</html>

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <title>Manage Tours</title>

    </head>
    <body>
        <br>
        <br>
        <br>

        <br>
        <br>
        <div class="container">
            <h2>Manage Tours</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Tour ID</th>
                        <th scope="col">Tour Name</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tours}" var="tour">
                        <tr>
                            <td>${tour.tourId}</td>
                            <td>${tour.tourName}</td>
                            <td>
                                <input type="hidden" name="tourId" value="${tour.tourId}">
                                <form id="viewTourForm" action="ViewUpdateTourServlet" method="get">
                                    <input type="hidden" name="tourId" value="${tour.tourId}">
                                
                                <button type="submit" class="btn btn-warning">Update</button>
                                <button type="button" class="btn btn-danger" onclick="if (confirm('Are you sure you want to delete this tour?')) window.location.href = 'EditDeleteTourServlet?tourId=${tour.tourId}'">Delete</button>
                                <button type="button" onclick="window.location.href = 'ViewTourDetailServlet?tourId=${tour.tourId}'">View Detail</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            
            <a href="EmployeeListServlet?" class="btn btn-primary">Create Tour</a>
            <a href="Home" class="btn btn-primary">Back to Homepage</a>
        </div>
        <footer>
            <%@include file="includes/footer.jsp" %>
        </footer>
    </body>
</html>

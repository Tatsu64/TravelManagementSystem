<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <title>Tour Dates</title>
    </head>
    <body>
        <br>
        <br>
        <br>

        <br>
        <br>
        <div class="container">
            <h2>Tour Dates</h2>
            <input type="hidden" name="tourId" value="${tourId}">
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Start Date</th>
                        <th scope="col">End Date</th>
                        <th scope="col">Current Capacity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tourDatesList}" var="tourDate">
                        <tr>
                            <td>${tourDate.startDate}</td>
                            <td>${tourDate.endDate}</td>
                            <td>${tourDate.currentCapacity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button onclick="location.href = 'AddTourDates.jsp?tourId=${tourId}';" type="button" class="btn btn-primary">Add Tour Date</button>
            <br>
            <br>
            <a href="ApprovalTourServlet?" class="btn btn-secondary">Finish</a>
        </div>

        <footer>
            <%@include file="includes/footer.jsp" %>
        </footer>
    </body>
</html>

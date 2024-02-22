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
                            <form action="ApprovalTourServlet" method="post">
                                <input type="hidden" name="tourId" value="${tour.tourId}">
                                <input type="submit" name="action" value="Accept">
                                <input type="submit" name="action" value="Reject">
                                <button type="button" onclick="window.location.href='ViewTourDetailServlet?tourId=${tour.tourId}'">View Detail</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <br>
       
        <a href="index.jsp" class="btn btn-primary">Back to Homepage</a>
    </div>
    <footer>
    <%@include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

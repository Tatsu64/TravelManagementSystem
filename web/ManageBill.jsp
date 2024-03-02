<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <title>All Bills</title>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h2>All Bills</h2>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Bill ID</th>
                    <th scope="col">Booking ID</th>
                    <th scope="col">Payment Date</th>
                    <th scope="col">Payment Method</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bill" items="${bills}">
                    <tr>
                        <td>${bill.billId}</td>
                        <td>${bill.booking.bookingId}</td>
                        <td>${bill.paymentDate}</td>
                        <td>${bill.paymentMethod}</td>
                        <td><a href="ViewBillDetailServlet?billId=${bill.billId}">View Detail</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="Home" class="btn btn-primary">Back to Homepage</a>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

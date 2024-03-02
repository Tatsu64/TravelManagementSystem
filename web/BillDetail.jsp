<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bill Detail</title>
    <style>
        .info-container {
            margin-bottom: 20px;
        }
        table {
            margin-left: 3%;
            width: calc(100% - 5%);
            border-collapse: collapse;
        }
        h2, h3{
            margin-left: 3%;
        }
        a{
            margin-left: 3%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <br>
    <br>
    <br>
    <div class="container">
        <h3>Bill Detail</h3>
        <table>
            <tr>
                <th>Bill ID:</th>
                <td>${bill.billId}</td>
            </tr>
            <tr>
                <th>Payment Date:</th>
                <td>${bill.paymentDate}</td>
            </tr>
            <tr>
                <th>Payment Method:</th>
                <td>${bill.paymentMethod}</td>
            </tr>
            <tr>
                <th>Booking ID:</th>
                <td>${bill.booking.bookingId}</td>
            </tr>
            <tr>
                <th>Tour:</th>
                <td>${bill.booking.tour.tourName}</td>
            </tr>
            <tr>
                <th>User:</th>
                <td>${bill.booking.user.name}</td>
            </tr>
            <tr>
                <th>Booking Date:</th>
                <td>${bill.booking.bookingDate}</td>
            </tr>
            <tr>
                <th>Number of People:</th>
                <td>${bill.booking.numberOfPeople}</td>
            </tr>
            <tr>
                <th>Total Price:</th>
                <td>${bill.booking.totalPrice}</td>
            </tr>
        </table>
            <br>
            <br>
            <a href="BillServlet?" class="btn btn-primary">Back</a>
    </div>
    <footer>
        <%@ include file="includes/footer.jsp" %>
    </footer>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking History</title>
    <link rel="stylesheet" href="css/style_profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <div class="container light-style flex-grow-1 container-p-y">
        <div class="col-md-9">
            <div class="tab-content">
                <div class="tab-pane fade active show" id="account-general">
                    <hr class="border-light m-0">
                    <div class="card-body">
                        <h2 class="form-title">Booking History</h2>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Tour Name</th>
                                    <th>Booking Date</th>
                                    <th>Number of People</th>
                                    <th>Total Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="booking" items="${bookings}">
                                    <tr>
                                        <td>${booking.tour.tourName}</td>
                                        <td>${booking.bookingDate}</td>
                                        <td>${booking.numberOfPeople}</td>
                                        <td>${booking.totalPrice}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${empty bookings}">
                            <div class="alert alert-warning" role="alert">
                                No bookings found.
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="text-left mt-3">
                <a href="profile.jsp" class="btn btn-primary px4">Back To Profile</a>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Hotel List</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <br>
            <br>
            <h2>Hotel List</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Hotel ID</th>
                        <th scope="col">Hotel Name</th>
                        <th scope="col">Location</th>
                        <th scope="col">Address</th>
                        <th scope="col">Image</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hotel" items="${hotelList}">
                        <tr>
                    <input type="hidden" id="locationId" name="locationId" value="${hotel.location.locationId}">
                    <td>${hotel.hotelId}</td>
                    <td>${hotel.hotelName}</td>
                    <td>${hotel.location.locationName}</td>
                    <td>${hotel.address}</td>
                    <td><img src="images/${hotel.imageUrl}" alt="Hotel Image" width="100"></td>
                    <td>
                        <!-- Update button -->
                        <button onclick="location.href = 'EditDeleteHotelServlet?hotelId=${hotel.hotelId}&action=update'" type="button" class="btn btn-warning">Update</button>
                        <!-- Delete button -->
                        <button onclick="openConfirmDeleteModal(${hotel.hotelId})" type="button" class="btn btn-danger">Delete</button>

                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            <a href="FormCreateHotelServlet?" class="btn btn-primary">Add Hotel</a>
            <a href="Home" class="btn btn-primary">Back to Homepage</a>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete this hotel?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger" id="confirmDeleteButton">Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

        <script>
                                function openConfirmDeleteModal(hotelId) {
                                    $('#confirmDeleteModal').modal('show');
                                    // Lưu hotelId vào một input hidden trong modal để sử dụng khi xác nhận xóa
                                    $('#confirmDeleteModal').find('#confirmDeleteButton').attr('data-hotel-id', hotelId);
                                }

                                // Xác nhận xóa khi người dùng nhấn nút "Delete" trong modal
                                $(document).on('click', '#confirmDeleteButton', function () {
                                    var hotelId = $(this).attr('data-hotel-id');
                                    // Thực hiện chuyển hướng đến servlet để xóa khách sạn
                                    location.href = 'EditDeleteHotelServlet?hotelId=' + hotelId + '&action=delete';
                                });
        </script>
    </body>
</html>

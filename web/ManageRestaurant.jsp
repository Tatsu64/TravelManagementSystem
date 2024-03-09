<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Restaurant List</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <br>
            <br>
            <h2>Restaurant List</h2>

            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Restaurant ID</th>
                        <th scope="col">Restaurant Name</th>
                        <th scope="col">Location</th>
                        <th scope="col">Address</th>
                        <th scope="col">Image</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <tr>
                            <td>${restaurant.restaurantId}</td>
                            <td>${restaurant.restaurantName}</td>
                            <td>${restaurant.location.locationName}</td>
                            <td>${restaurant.address}</td>
                            <td><img src="images/${restaurant.imageUrl}" alt="Restaurant Image" width="100"></td>
                            <td>
                                <!-- Update button -->
                                <button onclick="location.href = 'EditDeleteRestaurantServlet?restaurantId=${restaurant.restaurantId}&action=update'" type="button" class="btn btn-warning">Update</button>
                                <!-- Delete button -->
                                <button onclick="openConfirmDeleteModal(${restaurant.restaurantId})" type="button" class="btn btn-danger">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            <a href="FormCreateRestaurantServlet?" class="btn btn-primary">Add Restaurant</a>
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
                        Are you sure you want to delete this restaurant?
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
                                function openConfirmDeleteModal(restaurantId) {
                                    $('#confirmDeleteModal').modal('show');
                                    // Lưu restaurantId vào một input hidden trong modal để sử dụng khi xác nhận xóa
                                    $('#confirmDeleteModal').find('#confirmDeleteButton').attr('data-restaurant-id', restaurantId);
                                }

                                // Xác nhận xóa khi người dùng nhấn nút "Delete" trong modal
                                $(document).on('click', '#confirmDeleteButton', function () {
                                    var restaurantId = $(this).attr('data-restaurant-id');
                                    // Thực hiện chuyển hướng đến servlet để xóa nhà hàng
                                    location.href = 'EditDeleteRestaurantServlet?restaurantId=' + restaurantId + '&action=delete';
                                });
        </script>
    </body>
</html>

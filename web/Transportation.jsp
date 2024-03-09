<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transportation List</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <br>
            <br>
            <h2>Transportation List</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Departure Time</th>
                        <th scope="col">Return Time</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${transportationList}" var="transportation">
                        <tr>
                            <td>${transportation.transportationId}</td>
                            <td>${transportation.transportationName}</td>
                            <td>${transportation.departureTime}</td>
                            <td>${transportation.returnTime}</td>
                            <td>
                                <!-- Update button -->
                                <button onclick="location.href = 'EditDeleteTransportationServlet?transportationId=${transportation.transportationId}&action=update'" type="button" class="btn btn-warning">Update</button>

                                <!-- Delete button -->
                                <button type="button" class="btn btn-danger" onclick="openConfirmDeleteModal(${transportation.transportationId})">Delete</button>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            <a href="CreateTransportation.jsp" class="btn btn-primary">Add Transportation</a>
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
                        Are you sure you want to delete this transportation?
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
                                function openConfirmDeleteModal(transportationId) {
                                    $('#confirmDeleteModal').modal('show');
                                    // Lưu transportationId vào một input hidden trong modal để sử dụng khi xác nhận xóa
                                    $('#confirmDeleteModal').find('#confirmDeleteButton').attr('data-transportation-id', transportationId);
                                }

                                // Xác nhận xóa khi người dùng nhấn nút "Delete" trong modal
                                $(document).on('click', '#confirmDeleteButton', function () {
                                    var transportationId = $(this).attr('data-transportation-id');
                                    // Thực hiện chuyển hướng đến servlet để xóa phương tiện vận chuyển
                                    window.location.href = 'EditDeleteTransportationServlet?transportationId=' + transportationId + '&action=delete';
                                });
        </script>
    </body>
</html>

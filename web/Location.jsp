<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Locations</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <br>
            <br>
            <h2>List of Locations</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Location ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${locations}" var="location">
                        <tr>
                            <td>${location.locationId}</td>
                            <td>${location.locationName}</td>
                            <td>
                                <a href="EditDeleteLocationServlet?locationId=${location.locationId}&action=update" class="btn btn-warning">Update</a>
                                <button type="button" class="btn btn-danger" onclick="openConfirmDeleteModal(${location.locationId})">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            <a href="CreateLocation.jsp" class="btn btn-primary">Create Location</a>
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
                        Are you sure you want to delete this location?
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
                                    function openConfirmDeleteModal(locationId) {
                                        $('#confirmDeleteModal').modal('show');
                                        // Lưu locationId vào một input hidden trong modal để sử dụng khi xác nhận xóa
                                        $('#confirmDeleteModal').find('#confirmDeleteButton').attr('data-location-id', locationId);
                                    }

                                    // Xác nhận xóa khi người dùng nhấn nút "Delete" trong modal
                                    $(document).on('click', '#confirmDeleteButton', function () {
                                        var locationId = $(this).attr('data-location-id');
                                        // Thực hiện chuyển hướng đến servlet để xóa địa điểm
                                        window.location.href = 'EditDeleteLocationServlet?locationId=' + locationId + '&action=delete';
                                    });
        </script>
    </body>
</html>

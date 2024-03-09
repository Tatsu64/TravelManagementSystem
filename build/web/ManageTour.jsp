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
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <br>
            <br>
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
                                </form>
                                <button type="button" class="btn btn-danger" onclick="openConfirmDeleteModal(${tour.tourId})">Delete</button>
                                <br>
                                <button type="button" onclick="window.location.href = 'ViewTourDetailServlet?tourId=${tour.tourId}'" >View Detail</button>
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
                        Are you sure you want to delete this tour?
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
                                    function openConfirmDeleteModal(tourId) {
                                        $('#confirmDeleteModal').modal('show');
                                        // Lưu tourId vào một input hidden trong modal để sử dụng khi xác nhận xóa
                                        $('#confirmDeleteModal').find('#confirmDeleteButton').attr('data-tour-id', tourId);
                                    }

                                    // Xác nhận xóa khi người dùng nhấn nút "Delete" trong modal
                                    $(document).on('click', '#confirmDeleteButton', function () {
                                        var tourId = $(this).attr('data-tour-id');
                                        // Thực hiện chuyển hướng đến servlet để xóa tour
                                        window.location.href = 'EditDeleteTourServlet?tourId=' + tourId;
                                    });
        </script>
    </body>
</html>

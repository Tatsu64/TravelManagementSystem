<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Reviews</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <br>
            <br>
            <h2>Manage Reviews</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Review ID</th>
                        <th scope="col">Content</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Booking ID</th>
                        <th scope="col">Tour ID</th>
                        <th scope="col">Tour Name</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="review" items="${reviewList}">
                        <tr>
                            <td>${review.reviewId}</td>
                            <td>${review.content}</td>
                            <td>${review.rating}</td>
                            <td>${review.booking.bookingId}</td>
                            <td>${review.booking.tour.tourId}</td>
                            <td>${review.booking.tour.tourName}</td>
                            <td>
                                <!-- Delete button -->
                                <button onclick="openConfirmDeleteModal(${review.reviewId})" type="button" class="btn btn-danger">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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
                        Are you sure you want to delete this review?
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
                                    function openConfirmDeleteModal(reviewId) {
                                        $('#confirmDeleteModal').modal('show');
                                        // Lưu reviewId vào một input hidden trong modal để sử dụng khi xác nhận xóa
                                        $('#confirmDeleteModal').find('#confirmDeleteButton').attr('data-review-id', reviewId);
                                    }

                                    // Xác nhận xóa khi người dùng nhấn nút "Delete" trong modal
                                    $(document).on('click', '#confirmDeleteButton', function () {
                                        var reviewId = $(this).attr('data-review-id');
                                        // Thực hiện chuyển hướng đến servlet để xóa đánh giá
                                        location.href = 'ManageReviewServlet?reviewId=' + reviewId + '&action=delete';
                                    });
        </script>
    </body>
</html>

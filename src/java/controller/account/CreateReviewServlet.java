package controller.account;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.BookingDAO;
import model.database.DatabaseConnector;
import model.entity.Booking;
import model.dao.ReviewDAO;

public class CreateReviewServlet extends HttpServlet {
    private ReviewDAO reviewDAO;
    private BookingDAO bookingDAO;

    public void init() throws ServletException {
        super.init();
        reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());
        bookingDAO = new BookingDAO(DatabaseConnector.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String content = request.getParameter("content");
        int rating = Integer.parseInt(request.getParameter("rating"));

        try {
            // Retrieve the Booking object using the bookingId
            Booking booking = bookingDAO.getBookingById(bookingId);
            if (booking != null) {
                // Get the tourId from the retrieved Booking object
                int tourId = booking.getTour().getTourId();

                // Thực hiện tạo review
                boolean success = reviewDAO.createReview(userId, bookingId, content, rating);

                if (success) {
                    // Chuyển hướng người dùng đến trang thành công hoặc trang chi tiết tour
                    response.sendRedirect("Detail?id=" + tourId);
                } else {
                    // Xử lý nếu có lỗi xảy ra
                    response.sendRedirect("404.jsp");
                }
            } else {
                // Handle the case where the bookingId is invalid
                response.sendRedirect("404.jsp");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

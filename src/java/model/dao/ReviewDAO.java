/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author ADMIN
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entity.Booking;
import model.entity.Review;
import model.entity.Tour;

public class ReviewDAO {

    private Connection connection;

    public ReviewDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Review> getAllReviews() throws SQLException {
        List<Review> reviewList = new ArrayList<>();
        String sql = "SELECT r.review_id, r.booking_id, r.content, r.rating, "
                + "b.booking_date, b.number_of_people, b.total_price, "
                + "b.tour_id, t.tour_name "
                + "FROM Reviews r "
                + "JOIN Bookings b ON r.booking_id = b.booking_id "
                + "JOIN Tours t ON b.tour_id = t.tour_id";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Review review = new Review();
                review.setReviewId(resultSet.getInt("review_id"));
                review.setContent(resultSet.getString("content"));
                review.setRating(resultSet.getInt("rating"));

                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                booking.setNumberOfPeople(resultSet.getInt("number_of_people"));
                booking.setTotalPrice(resultSet.getBigDecimal("total_price"));

                Tour tour = new Tour();
                tour.setTourId(resultSet.getInt("tour_id"));
                tour.setTourName(resultSet.getString("tour_name"));

                booking.setTour(tour);
                review.setBooking(booking);

                reviewList.add(review);
            }
        }
        return reviewList;
    }

    public void deleteReview(int reviewId) throws SQLException {
        String deleteQuery = "DELETE FROM Reviews WHERE review_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, reviewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
        }
    }

}

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
import model.entity.User;

public class BookingDAO {
    
    private Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public Booking getBookingById(int bookingId) throws SQLException {
        Booking booking = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Bookings WHERE booking_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, bookingId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                
                // Get and set tour information
                TourDAO tourDAO = new TourDAO(connection);
                Tour tour = tourDAO.getTourById(resultSet.getInt("tour_id"));
                booking.setTour(tour);
                
                // Get and set user information
                UserDAO userDAO = new UserDAO(connection);
                User user = userDAO.getUserById(resultSet.getInt("user_id"));
                booking.setUser(user);
                
                booking.setBookingDate(resultSet.getDate("booking_date"));
                booking.setNumberOfPeople(resultSet.getInt("number_of_people"));
                booking.setTotalPrice(resultSet.getBigDecimal("total_price"));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return booking;
    }
    
public List<Booking> getBookingsByUserId(int userId) throws SQLException {
    List<Booking> bookings = new ArrayList<>();
    String query = "SELECT b.booking_id, b.number_of_people, b.total_price, b.booking_date, " +
                   "t.tour_id, t.tour_name " +
                   "FROM reviews r " +
                   "JOIN bookings b ON r.booking_id = b.booking_id " +
                   "JOIN tours t ON b.tour_id = t.tour_id " +
                   "WHERE b.user_id = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setNumberOfPeople(resultSet.getInt("number_of_people"));
                booking.setTotalPrice(resultSet.getBigDecimal("total_price"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                
                // Retrieve tour details
                Tour tour = new Tour();
                tour.setTourId(resultSet.getInt("tour_id"));
                tour.setTourName(resultSet.getString("tour_name"));
                
                // Set the tour in the booking
                booking.setTour(tour);
                
                // Add the booking to the list
                bookings.add(booking);
            }
        }
    }
    return bookings;
}


}


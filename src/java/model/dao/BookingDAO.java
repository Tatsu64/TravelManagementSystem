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
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
        String query = "SELECT b.booking_id, b.number_of_people, b.total_price, b.booking_date, "
                + "t.tour_id, t.tour_name "
                + "FROM bookings b "
                + "JOIN tours t ON b.tour_id = t.tour_id "
                + "WHERE b.user_id = ?";

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

    public int createBookingAndGetId(Booking booking) throws SQLException {
        String sql = "INSERT INTO Bookings (tour_id, user_id, booking_date, number_of_people, total_price) VALUES (?, ?, ?, ?, ?)";
        int bookingId = -1;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, booking.getTour().getTourId());
            statement.setInt(2, booking.getUser().getUserId());
            statement.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
            statement.setInt(4, booking.getNumberOfPeople());
            statement.setBigDecimal(5, booking.getTotalPrice());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bookingId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
            }
        }
        return bookingId;
    }
    
public Booking getLatestBookingByUserId(int userId) throws SQLException {
    Booking latestBooking = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT TOP 1 * FROM Bookings WHERE user_id = ? ORDER BY booking_date DESC";
        statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            latestBooking = new Booking();
            latestBooking.setBookingId(resultSet.getInt("booking_id"));

            // Get and set tour information
            TourDAO tourDAO = new TourDAO(connection);
            Tour tour = tourDAO.getTourById(resultSet.getInt("tour_id"));
            latestBooking.setTour(tour);

            // Get and set user information
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.getUserById(resultSet.getInt("user_id"));
            latestBooking.setUser(user);

            latestBooking.setBookingDate(resultSet.getDate("booking_date"));
            latestBooking.setNumberOfPeople(resultSet.getInt("number_of_people"));
            latestBooking.setTotalPrice(resultSet.getBigDecimal("total_price"));
        }
    } finally {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    return latestBooking;
}




}

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
import model.entity.Booking;
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
}


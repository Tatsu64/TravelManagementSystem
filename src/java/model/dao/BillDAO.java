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
import model.database.DatabaseConnector;
import model.entity.Bill;
import model.entity.Booking;
import model.entity.Tour;
import model.entity.User;


public class BillDAO {
    
    private Connection connection;

    public BillDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Bill> getAllBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Bills";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getInt("bill_id"));
                bill.setPaymentDate(resultSet.getDate("payment_date"));
                bill.setPaymentMethod(resultSet.getString("payment_method"));

                // Get booking information
                int bookingId = resultSet.getInt("booking_id");
                BookingDAO bookingDAO = new BookingDAO(connection);
                Booking booking = bookingDAO.getBookingById(bookingId);
                bill.setBooking(booking);

                bills.add(bill);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return bills;
    }
    
    public Bill getBillById(int billId) throws SQLException {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Bill bill = null;

    try {
        connection = DatabaseConnector.getConnection();
       String query = "SELECT b.bill_id, b.payment_date, b.payment_method, "
             + "booking.booking_id, booking.tour_id, booking.user_id, booking.booking_date, booking.number_of_people, booking.total_price, "
             + "users.name as name, "
             + "tour.tour_name "
             + "FROM Bills b "
             + "JOIN Bookings booking ON b.booking_id = booking.booking_id "
             + "JOIN Users users ON booking.user_id = users.user_id "
             + "JOIN Tours tour ON booking.tour_id = tour.tour_id "
             + "WHERE b.bill_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, billId);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            bill = new Bill();
            bill.setBillId(resultSet.getInt("bill_id"));
            bill.setPaymentDate(resultSet.getDate("payment_date"));
            bill.setPaymentMethod(resultSet.getString("payment_method"));
            
            Booking booking = new Booking();
            booking.setBookingId(resultSet.getInt("booking_id"));
            
            // Get and set tour information
            TourDAO tourDAO = new TourDAO(connection);
            Tour tour = tourDAO.getTourById(resultSet.getInt("tour_id"));
            tour.setTourName(resultSet.getString("tour_name"));
            booking.setTour(tour);
            
            // Set user information
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("name"));
            booking.setUser(user);
            
            booking.setBookingDate(resultSet.getDate("booking_date"));
            booking.setNumberOfPeople(resultSet.getInt("number_of_people"));
            booking.setTotalPrice(resultSet.getBigDecimal("total_price"));
            
            bill.setBooking(booking);
        }
    } finally {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    return bill;
}

}


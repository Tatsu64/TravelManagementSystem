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
import model.entity.Tour;

public class TourDAO {
    private Connection connection;

    public TourDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    // Create
    public void addTour(Tour tour) {
        try {
            String query = "INSERT INTO Tours (tour_name, description, start_date, end_date, tour_price, image_url, employee_id, start_location, max_capacity, current_capacity) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tour.getTourName());
                statement.setString(2, tour.getDescription());
                statement.setDate(3, new java.sql.Date(tour.getStartDate().getTime()));
                statement.setDate(4, new java.sql.Date(tour.getEndDate().getTime()));
                statement.setBigDecimal(5, tour.getTourPrice());
                statement.setString(6, tour.getImageUrl());
                statement.setInt(7, tour.getEmployee().getEmployeeId());
                statement.setString(8, tour.getStartLocation());
                statement.setInt(9, tour.getMaxCapacity());
                statement.setInt(10, tour.getCurrentCapacity());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    // Read
    public Tour getTourById(int tourId) {
        try {
            String query = "SELECT * FROM Tours WHERE tour_id = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tourId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractTourFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return null;
    }

    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();

        try {
            String query = "SELECT * FROM Tours";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tour tour = extractTourFromResultSet(resultSet);
                    tours.add(tour);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return tours;
    }

    // Update
    public void updateTour(Tour tour) {
        try {
            String query = "UPDATE Tours SET tour_name = ?, description = ?, start_date = ?, end_date = ?, " +
                           "tour_price = ?, image_url = ?, employee_id = ?, start_location = ?, max_capacity = ?, current_capacity = ? " +
                           "WHERE tour_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tour.getTourName());
                statement.setString(2, tour.getDescription());
                statement.setDate(3, new java.sql.Date(tour.getStartDate().getTime()));
                statement.setDate(4, new java.sql.Date(tour.getEndDate().getTime()));
                statement.setBigDecimal(5, tour.getTourPrice());
                statement.setString(6, tour.getImageUrl());
                statement.setInt(7, tour.getEmployee().getEmployeeId());
                statement.setString(8, tour.getStartLocation());
                statement.setInt(9, tour.getMaxCapacity());
                statement.setInt(10, tour.getCurrentCapacity());
                statement.setInt(11, tour.getTourId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    // Delete
    public void deleteTour(int tourId) {
        try {
            String query = "DELETE FROM Tours WHERE tour_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tourId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    // Helper method to extract a Tour from a ResultSet
    private Tour extractTourFromResultSet(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setTourId(resultSet.getInt("tour_id"));
        tour.setTourName(resultSet.getString("tour_name"));
        tour.setDescription(resultSet.getString("description"));
        tour.setStartDate(resultSet.getDate("start_date"));
        tour.setEndDate(resultSet.getDate("end_date"));
        tour.setTourPrice(resultSet.getBigDecimal("tour_price"));
        tour.setImageUrl(resultSet.getString("image_url"));

        // You may need to fetch and set the Employee object here by querying the Employees table

        tour.setStartLocation(resultSet.getString("start_location"));
        tour.setMaxCapacity(resultSet.getInt("max_capacity"));
        tour.setCurrentCapacity(resultSet.getInt("current_capacity"));

        return tour;
    }
}


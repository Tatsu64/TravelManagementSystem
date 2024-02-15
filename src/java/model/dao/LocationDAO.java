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
import model.entity.Location;

public class LocationDAO {
    private Connection connection;

    // Constructor
    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    // Phương thức để tạo mới một địa điểm trong cơ sở dữ liệu
    public boolean createLocation(Location location) {
        String query = "INSERT INTO Locations (location_name, tour_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, location.getLocationName());
            preparedStatement.setInt(2, location.getTour().getTourId());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    location.setLocationId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating location failed, no ID obtained.");
                }
                System.out.println("A new location was inserted successfully!");
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    // Phương thức để tạo mới một địa điểm trong cơ sở dữ liệu và trả về location_id

    public int createLocationAndGetId(Location location) {
        String query = "INSERT INTO Locations (location_name, tour_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, location.getLocationName());
            preparedStatement.setInt(2, location.getTour().getTourId());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int locationId = generatedKeys.getInt(1);
                    System.out.println("A new location was inserted successfully with ID: " + locationId);
                    return locationId;
                } else {
                    throw new SQLException("Creating location failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // Trả về -1 nếu có lỗi xảy ra hoặc không thể lấy được location_id
    }

    // Phương thức để lấy thông tin của Location dựa trên tourId
    public Location getLocationByTourId(int tourId) throws SQLException {
        Location location = null; // Khởi tạo đối tượng Location

        String query = "SELECT * FROM Locations WHERE tour_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, tourId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Tạo đối tượng Location và đặt các giá trị từ kết quả truy vấn
                    location = new Location();
                    location.setLocationId(resultSet.getInt("location_id"));
                    location.setLocationName(resultSet.getString("location_name"));
                    location.setTourId(tourId); // Đặt tourId cho đối tượng Location
                }
            }
        }

        return location;
    }
}


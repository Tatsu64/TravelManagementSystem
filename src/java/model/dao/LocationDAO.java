/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import model.entity.MenuLocation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.database.DatabaseConnector.connection;
import model.entity.Employee;
import model.entity.Location;

/**
 *
 * @author toden
 */
public class LocationDAO {

    public LocationDAO(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int createLocationAndGetId(Location newLocation) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Phương thức để tạo mới một địa điểm trong cơ sở dữ liệu
    public boolean createLocation(Location location) {
        String query = "INSERT INTO Locations (location_name) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, location.getLocationName());


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
    // Phương thức để lấy danh sách các địa điểm
    public List<Location> getLocationList() throws SQLException {
        List<Location> locations = new ArrayList<>();

        // Chuỗi truy vấn SQL
        String query = "SELECT * FROM Locations";

        // Chuẩn bị câu lệnh truy vấn
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Thực hiện truy vấn và lấy kết quả
            try (ResultSet resultSet = statement.executeQuery()) {
                // Duyệt qua các hàng trong kết quả và tạo đối tượng Location cho mỗi hàng
                while (resultSet.next()) {
                    int locationId = resultSet.getInt("location_id");
                    String locationName = resultSet.getString("location_name");

                    // Tạo đối tượng Location từ dữ liệu trong hàng kết quả
                    Location location = new Location(locationId, locationName);

                    // Thêm đối tượng Location vào danh sách
                    locations.add(location);
                }
            }
        }

        return locations;
    }
    // Phương thức để lấy danh sách các địa điểm dựa trên tour_id
    public List<Location> getLocationByTourId(int tourId) {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.* FROM Locations l JOIN TourLocation tl ON l.location_id = tl.location_id WHERE tl.tour_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tourId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Location location = new Location();
                    location.setLocationId(rs.getInt("location_id"));
                    location.setLocationName(rs.getString("location_name"));
                    locations.add(location);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locations;
    }

    public static List<MenuLocation> getMenuLocation() throws SQLException {
        List<MenuLocation> locations = new ArrayList<>();
        try ( PreparedStatement statement = connection.prepareStatement("select * from Locations");  ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getNString(2);

                locations.add(new MenuLocation(name,"https://t4.ftcdn.net/jpg/02/80/82/81/360_F_280828158_ZZ2W8atYMHiSkLoDzxgDHNhdmXJ31jCR.jpg"));
            }
        }
        return locations;
    }

}

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
import model.database.DatabaseConnector;
import static model.database.DatabaseConnector.connection;
import model.entity.Location;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toden
 */
public class LocationDAO {

    public LocationDAO(Connection connection) {
      
    }

    public int createLocationAndGetId(Location newLocation) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Phương thức thêm mới một địa điểm vào cơ sở dữ liệu
    public void createLocation(Location location) throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            // Tạo câu lệnh SQL để thêm mới một địa điểm
            String insertQuery = "INSERT INTO Locations (location_name) VALUES (?)";
            pstmt = connection.prepareStatement(insertQuery);
            
            // Thiết lập các tham số cho câu lệnh SQL
            pstmt.setString(1, location.getLocationName());
            
            // Thực thi câu lệnh SQL để thêm mới địa điểm
            pstmt.executeUpdate();
        } finally {
            // Đóng PreparedStatement sau khi thêm mới địa điểm
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    public void deleteLocation(int locationId) throws SQLException {
    PreparedStatement pstmt = null;
    
    try {
        // Tạo câu lệnh SQL để xóa địa điểm
        String deleteQuery = "DELETE FROM Locations WHERE location_id = ?";
        pstmt = connection.prepareStatement(deleteQuery);
        
        // Thiết lập tham số cho câu lệnh SQL
        pstmt.setInt(1, locationId);
        
        // Thực thi câu lệnh SQL để xóa địa điểm
        pstmt.executeUpdate();
    } finally {
        // Đóng PreparedStatement sau khi xóa địa điểm
        if (pstmt != null) {
            pstmt.close();
        }
    }
}
    
    public void updateLocation(Location location) throws SQLException {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DatabaseConnector.getConnection();
        
        // Tạo câu lệnh SQL để cập nhật thông tin địa điểm
        String updateQuery = "UPDATE Locations SET location_name = ? WHERE location_id = ?";
        pstmt = conn.prepareStatement(updateQuery);
        
        // Thiết lập các tham số cho câu lệnh SQL
        pstmt.setString(1, location.getLocationName());
        pstmt.setInt(2, location.getLocationId());
        
        // Thực thi câu lệnh SQL để cập nhật thông tin địa điểm
        pstmt.executeUpdate();
    } finally {
        // Đóng các tài nguyên
        if (pstmt != null) {
            pstmt.close();
        }
        // Không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
    }
}

public Location getLocationById(int locationId) throws SQLException {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Location location = null;

    try {
        conn = DatabaseConnector.getConnection();
        
        // Tạo câu lệnh SQL để lấy thông tin của địa điểm dựa trên ID
        String query = "SELECT * FROM Locations WHERE location_id = ?";
        pstmt = conn.prepareStatement(query);
        
        // Thiết lập tham số cho câu lệnh SQL
        pstmt.setInt(1, locationId);
        
        // Thực thi câu lệnh SQL và lấy kết quả
        rs = pstmt.executeQuery();
        
        // Kiểm tra xem có kết quả trả về hay không
        if (rs.next()) {
            // Khởi tạo một đối tượng Location với thông tin từ kết quả truy vấn
            location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocationName(rs.getString("location_name"));
        }
    } finally {
        // Đóng các tài nguyên
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        // Không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
    }

    return location;
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


    public static List<MenuLocation> getMenuLocation() {
        List<MenuLocation> locations = new ArrayList<>();
        try ( PreparedStatement statement = connection.prepareStatement("select * from Locations");  ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getNString(2);

                locations.add(new MenuLocation(name,"https://t4.ftcdn.net/jpg/02/80/82/81/360_F_280828158_ZZ2W8atYMHiSkLoDzxgDHNhdmXJ31jCR.jpg"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locations;
    }
}

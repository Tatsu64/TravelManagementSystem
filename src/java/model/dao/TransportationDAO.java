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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.database.DatabaseConnector;
import model.entity.Transportation;

public class TransportationDAO {

    private Connection connection;

    public TransportationDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    public List<Transportation> getTransportationList() throws SQLException {
        List<Transportation> transportationList = new ArrayList<>();

        // Use a try-with-resources statement to automatically close resources (PreparedStatement and ResultSet)
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transportations"); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Create Transportation objects and add them to the list
                Transportation transportation = new Transportation();
                transportation.setTransportationId(resultSet.getInt("transportation_id"));
                transportation.setTransportationName(resultSet.getString("transportation_name"));
                transportation.setImageUrl(resultSet.getString("image_url"));
                transportation.setDepartureTime(resultSet.getTime("departure_time"));
                transportation.setReturnTime(resultSet.getTime("return_time"));
                // Set other properties as needed

                transportationList.add(transportation);
            }
        }

        return transportationList;
    }

    // Method to create a new transportation record
    public void createTransportation(Transportation transportation) throws SQLException {
        String query = "INSERT INTO Transportations (transportation_name, image_url, departure_time, return_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transportation.getTransportationName());
            statement.setString(2, transportation.getImageUrl());
            statement.setTime(3, transportation.getDepartureTime());
            statement.setTime(4, transportation.getReturnTime());
            // Execute the insert statement
            statement.executeUpdate();
        }
    }

    public void deleteTransportation(int transportationId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();

            // Xóa các tham chiếu từ bảng trung gian TourTransportation
            String deleteTourTransportationQuery = "DELETE FROM TourTransportation WHERE transportation_id = ?";
            pstmt = conn.prepareStatement(deleteTourTransportationQuery);
            pstmt.setInt(1, transportationId);
            pstmt.executeUpdate();

            // Xóa thông tin vận chuyển từ bảng Transportation
            String deleteTransportationQuery = "DELETE FROM Transportations WHERE transportation_id = ?";
            pstmt = conn.prepareStatement(deleteTransportationQuery);
            pstmt.setInt(1, transportationId);
            pstmt.executeUpdate();
        } finally {
            // Đóng tài nguyên
            if (pstmt != null) {
                pstmt.close();
            }
            // Không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
        }
    }

    public Transportation getTransportationById(int transportationId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transportation transportation = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM Transportations WHERE transportation_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, transportationId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Transportation từ dữ liệu trong ResultSet
                transportation = new Transportation();
                transportation.setTransportationId(rs.getInt("transportation_id"));
                transportation.setTransportationName(rs.getString("transportation_name"));
                transportation.setDepartureTime(rs.getTime("departure_time"));
                transportation.setReturnTime(rs.getTime("return_time"));
                transportation.setImageUrl(rs.getString("image_url"));
            }
        } finally {
            // Đóng tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            // Không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
        }

        return transportation;
    }

    public void updateTransportation(Transportation transportation) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();

            // Tạo câu lệnh SQL để cập nhật thông tin vận chuyển
            String updateQuery = "UPDATE Transportations SET transportation_name = ?, departure_time = ?, return_time = ?, image_url = ? WHERE transportation_id = ?";
            pstmt = conn.prepareStatement(updateQuery);

            // Thiết lập các tham số cho câu lệnh SQL
            pstmt.setString(1, transportation.getTransportationName());
            pstmt.setTime(2, transportation.getDepartureTime());
            pstmt.setTime(3, transportation.getReturnTime());
            pstmt.setString(4, transportation.getImageUrl());
            pstmt.setInt(5, transportation.getTransportationId());

            // Thực thi câu lệnh SQL để cập nhật thông tin vận chuyển
            pstmt.executeUpdate();
        } finally {
            // Đóng tài nguyên
            if (pstmt != null) {
                pstmt.close();
            }
            // Không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
        }
    }

    public static List<Transportation> getTransportationByTourId(int tourid) {
        List<Transportation> transportationList = new ArrayList<>();

        // Use a try-with-resources statement to automatically close resources (PreparedStatement and ResultSet)
        try (PreparedStatement statement = DatabaseConnector.connection.prepareStatement("SELECT * FROM Transportations t join TourTransportation tt on t.transportation_id = tt.transportation_id\n"
                + "where tt.tour_id = ?")) {
            statement.setInt(1, tourid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Create Transportation objects and add them to the list
                Transportation transportation = new Transportation();
                transportation.setTransportationId(resultSet.getInt("transportation_id"));
                transportation.setTransportationName(resultSet.getString("transportation_name"));
                transportation.setImageUrl(resultSet.getString("image_url"));
                transportation.setDepartureTime(resultSet.getTime("departure_time"));
                transportation.setReturnTime(resultSet.getTime("return_time"));
                // Set other properties as needed

                transportationList.add(transportation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransportationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return transportationList;
    }
}

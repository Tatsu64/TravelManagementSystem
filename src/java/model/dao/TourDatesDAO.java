/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.entity.TourDates;
import java.sql.*;
import model.database.DatabaseConnector;

/**
 *
 * @author ADMIN
 */
public class TourDatesDAO {

    private Connection connection;

    // Constructor
    public TourDatesDAO(Connection connection) {
        this.connection = connection;
    }

    // Setter for connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<TourDates> getTourDatesList(int tourId) throws SQLException {
        List<TourDates> tourDatesList = new ArrayList<>();
        String query = "SELECT * FROM TourDates WHERE tour_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TourDates tourDates = new TourDates();
                tourDates.setTourDateId(resultSet.getInt("tour_date_id"));
                tourDates.setStartDate(resultSet.getDate("start_date"));
                tourDates.setEndDate(resultSet.getDate("end_date"));
                tourDates.setCurrentCapacity(resultSet.getInt("current_capacity"));

                tourDatesList.add(tourDates);
            }
        }

        return tourDatesList;
    }

    public void createTourDates(TourDates tourDates) throws SQLException {
        String query = "INSERT INTO TourDates (tour_id, start_date, end_date, current_capacity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, tourDates.getTourId());
            preparedStatement.setDate(2, new java.sql.Date(tourDates.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(tourDates.getEndDate().getTime()));
            preparedStatement.setInt(4, tourDates.getCurrentCapacity());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteTourDate(int tourDateId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM TourDates WHERE tour_date_id = ?");
            pstmt.setInt(1, tourDateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc ghi log lỗi tại đây
        } finally {
            // Đóng PreparedStatement
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public TourDates getTourDatesById(int tourDateId) throws SQLException {
        TourDates tourDates = null;
        String query = "SELECT * FROM TourDates WHERE tour_date_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, tourDateId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tourDates = new TourDates();
                tourDates.setTourDateId(resultSet.getInt("tour_date_id"));
                tourDates.setTourId(resultSet.getInt("tour_id"));
                tourDates.setStartDate(resultSet.getDate("start_date"));
                tourDates.setEndDate(resultSet.getDate("end_date"));
                tourDates.setCurrentCapacity(resultSet.getInt("current_capacity"));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            // Không đóng kết nối ở đây để sử dụng lại kết nối cho các tác vụ khác
        }

        return tourDates;
    }

    public void updateTourDates(TourDates tourDates) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();

            // Tạo câu lệnh SQL để cập nhật thông tin Tour Date
            String updateQuery = "UPDATE TourDates SET start_date = ?, end_date = ?, current_capacity = ? WHERE tour_date_id = ?";
            pstmt = conn.prepareStatement(updateQuery);

            // Thiết lập các tham số cho câu lệnh SQL
            pstmt.setDate(1, new java.sql.Date(tourDates.getStartDate().getTime()));
            pstmt.setDate(2, new java.sql.Date(tourDates.getEndDate().getTime()));
            pstmt.setInt(3, tourDates.getCurrentCapacity());
            pstmt.setInt(4, tourDates.getTourDateId());

            // Thực thi câu lệnh SQL để cập nhật thông tin Tour Date
            pstmt.executeUpdate();
        } finally {
            // Đóng PreparedStatement, nhưng không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void updateCurrentCapacityByTourId(int tourId, Date startDate, int newCapacity) throws SQLException {
        String sql = "UPDATE TourDates SET current_capacity = ? WHERE tour_id = ? AND start_date = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newCapacity);
            statement.setInt(2, tourId);
            statement.setDate(3, new java.sql.Date(startDate.getTime())); // Chuyển đổi java.util.Date thành java.sql.Date
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
        }
    }

}

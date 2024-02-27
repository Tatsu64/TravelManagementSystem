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
import model.entity.ActivitySchedule;

public class ActivityScheduleDAO {

    private Connection connection;

    // Constructor
    public ActivityScheduleDAO(Connection connection) {
        this.connection = connection;
    }

    // Setter for connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<ActivitySchedule> getActivityScheduleList(int tourId) throws SQLException {
        List<ActivitySchedule> activitySchedules = new ArrayList<>();
        String query = "SELECT * FROM ActivitySchedules WHERE tour_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, tourId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ActivitySchedule activitySchedule = new ActivitySchedule();
                activitySchedule.setScheduleId(resultSet.getInt("schedule_id"));
                activitySchedule.setDayNumber(resultSet.getInt("day_number"));
                activitySchedule.setActivityName(resultSet.getString("activity_name"));
                activitySchedule.setStartTime(resultSet.getTime("start_time"));
                activitySchedule.setEndTime(resultSet.getTime("end_time"));
                activitySchedule.setLocation(resultSet.getString("location"));
                activitySchedule.setDescription(resultSet.getString("description"));
                activitySchedule.setImageUrl(resultSet.getString("image_url"));

                activitySchedules.add(activitySchedule);
            }
        } finally {
            // Close ResultSet and PreparedStatement
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return activitySchedules;
    }

    public void createActivitySchedule(ActivitySchedule activitySchedule) throws SQLException {
        String query = "INSERT INTO ActivitySchedules (tour_id, day_number, activity_name, start_time, end_time, location, description, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, activitySchedule.getTour().getTourId());
            preparedStatement.setInt(2, activitySchedule.getDayNumber());
            preparedStatement.setString(3, activitySchedule.getActivityName());
            preparedStatement.setTime(4, activitySchedule.getStartTime());
            preparedStatement.setTime(5, activitySchedule.getEndTime());
            preparedStatement.setString(6, activitySchedule.getLocation());
            preparedStatement.setString(7, activitySchedule.getDescription());
            preparedStatement.setString(8, activitySchedule.getImageUrl());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteActivitySchedule(int scheduleId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String deleteQuery = "DELETE FROM ActivitySchedules WHERE schedule_id = ?";
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, scheduleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng statement ở đây sẽ không đóng kết nối
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ActivitySchedule getActivityScheduleById(int scheduleId) {
        ActivitySchedule activitySchedule = null;
        String query = "SELECT * FROM ActivitySchedules WHERE schedule_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnector.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, scheduleId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                activitySchedule = new ActivitySchedule();
                activitySchedule.setScheduleId(resultSet.getInt("schedule_id"));
                activitySchedule.setDayNumber(resultSet.getInt("day_number"));
                activitySchedule.setActivityName(resultSet.getString("activity_name"));
                activitySchedule.setStartTime(resultSet.getTime("start_time"));
                activitySchedule.setEndTime(resultSet.getTime("end_time"));
                activitySchedule.setLocation(resultSet.getString("location"));
                activitySchedule.setDescription(resultSet.getString("description"));
                activitySchedule.setImageUrl(resultSet.getString("image_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log lỗi
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                // Không đóng kết nối ở đây
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return activitySchedule;
    }

    public void updateActivitySchedule(ActivitySchedule activitySchedule) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            pstmt = conn.prepareStatement("UPDATE ActivitySchedules SET day_number=?, activity_name=?, start_time=?, end_time=?, location=?, description=? WHERE schedule_id=?");
            pstmt.setInt(1, activitySchedule.getDayNumber());
            pstmt.setString(2, activitySchedule.getActivityName());
            pstmt.setTime(3, activitySchedule.getStartTime());
            pstmt.setTime(4, activitySchedule.getEndTime());
            pstmt.setString(5, activitySchedule.getLocation());
            pstmt.setString(6, activitySchedule.getDescription());
            pstmt.setInt(7, activitySchedule.getScheduleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } 
        }
    }

}

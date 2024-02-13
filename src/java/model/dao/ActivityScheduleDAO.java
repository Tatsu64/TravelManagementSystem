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

    // Method to get activity schedule list by tourId
    public List<ActivitySchedule> getActivityScheduleList(int tourId) throws SQLException {
        List<ActivitySchedule> activitySchedules = new ArrayList<>();
        String query = "SELECT * FROM ActivitySchedules WHERE tour_id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ActivitySchedule activitySchedule = new ActivitySchedule();
                activitySchedule.setScheduleId(resultSet.getInt("schedule_id"));
                activitySchedule.setDayNumber(resultSet.getInt("day_number"));
                activitySchedule.setActivityName(resultSet.getString("activity_name"));
                activitySchedule.setActivityDate(resultSet.getDate("activity_date"));
                activitySchedule.setStartTime(resultSet.getTime("start_time"));
                activitySchedule.setEndTime(resultSet.getTime("end_time"));
                activitySchedule.setLocation(resultSet.getString("location"));
                activitySchedule.setDescription(resultSet.getString("description"));
                activitySchedule.setImageUrl(resultSet.getString("image_url"));
                
                activitySchedules.add(activitySchedule);
            }
        }
        
        return activitySchedules;
    }
     public void createActivitySchedule(ActivitySchedule activitySchedule) throws SQLException {
        String query = "INSERT INTO ActivitySchedules (tour_id, day_number, activity_name, activity_date, start_time, end_time, location, description, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, activitySchedule.getTour().getTourId());
            preparedStatement.setInt(2, activitySchedule.getDayNumber());
            preparedStatement.setString(3, activitySchedule.getActivityName());
            preparedStatement.setDate(4, new java.sql.Date(activitySchedule.getActivityDate().getTime()));          
            preparedStatement.setTime(5, activitySchedule.getStartTime());
            preparedStatement.setTime(6, activitySchedule.getEndTime());
            preparedStatement.setString(7, activitySchedule.getLocation());
            preparedStatement.setString(8, activitySchedule.getDescription());
            preparedStatement.setString(9, activitySchedule.getImageUrl());

            preparedStatement.executeUpdate();
        }
    }
}



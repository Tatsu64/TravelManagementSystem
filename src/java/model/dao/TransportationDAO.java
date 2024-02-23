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
    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transportations ORDER BY transportation_id DESC");
         ResultSet resultSet = statement.executeQuery()) {

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
}


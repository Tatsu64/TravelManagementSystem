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

    // Replace this method with your actual implementation to fetch the transportation list from the database
   public List<Transportation> getTransportationList() throws SQLException {
    List<Transportation> transportationList = new ArrayList<>();

    // Use a try-with-resources statement to automatically close resources (PreparedStatement and ResultSet)
    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transportations");
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            // Create Transportation objects and add them to the list
            Transportation transportation = new Transportation();
            transportation.setTransportationId(resultSet.getInt("transportation_id"));
            transportation.setTransportationName(resultSet.getString("transportation_name"));
            transportation.setDepartureDate(resultSet.getDate("departure_date"));
            transportation.setReturnDate(resultSet.getDate("return_date"));
            transportation.setPrice(resultSet.getBigDecimal("price"));
            transportation.setImageUrl(resultSet.getString("image_url"));
            // Set other properties as needed

            transportationList.add(transportation);
        }
    }

    return transportationList;
}

     // Method to create a new transportation record
    public void createTransportation(Transportation transportation) throws SQLException {
        String query = "INSERT INTO Transportations (transportation_name, departure_date, return_date, price, image_url) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transportation.getTransportationName());
            statement.setDate(2, new java.sql.Date(transportation.getDepartureDate().getTime()));
            statement.setDate(3, new java.sql.Date(transportation.getReturnDate().getTime()));
            statement.setBigDecimal(4, transportation.getPrice());
            statement.setString(5, transportation.getImageUrl());

            // Execute the insert statement
            statement.executeUpdate();
        }
    }
}


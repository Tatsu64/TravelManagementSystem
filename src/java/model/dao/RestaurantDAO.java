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
import model.entity.Restaurant;

public class RestaurantDAO {
    private Connection connection;

    // Constructor
    public RestaurantDAO(Connection connection) {
        this.connection = connection;
    }

    // Phương thức lấy danh sách nhà hàng từ cơ sở dữ liệu dựa trên locationId
    public List<Restaurant> getRestaurantList(int locationId) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();
        String query = "SELECT * FROM Restaurants WHERE location_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, locationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
                    restaurant.setRestaurantName(resultSet.getString("restaurant_name"));

                    // Tạo đối tượng Location
                    Location location = new Location();
                    location.setLocationId(resultSet.getInt("location_id"));

                    // Đặt đối tượng Location cho nhà hàng
                    restaurant.setLocation(location);

                    restaurant.setImageUrl(resultSet.getString("image_url"));
                    restaurant.setAddress(resultSet.getString("address"));
                    restaurantList.add(restaurant);
                }
            }
        }

        return restaurantList;
    }
    
    public List<Restaurant> getRestaurantByTourId(int tourId) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();

        String query = "SELECT * FROM Restaurants R "
                + "JOIN RestaurantTour RT ON R.restaurant_id = RT.restaurant_id "
                + "WHERE RT.tour_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
                    restaurant.setRestaurantName(resultSet.getString("restaurant_name"));
                    restaurant.setAddress(resultSet.getString("address"));
                    restaurant.setImageUrl(resultSet.getString("image_url"));
                    // Add restaurant to the list
                    restaurantList.add(restaurant);
                }
            }
        }

        return restaurantList;
    }
    
    public boolean createRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO Restaurants (restaurant_name, location_id, image_url, address) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, restaurant.getRestaurantName());
            preparedStatement.setInt(2, restaurant.getLocation().getLocationId());
            preparedStatement.setString(3, restaurant.getImageUrl());
            preparedStatement.setString(4, restaurant.getAddress());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}


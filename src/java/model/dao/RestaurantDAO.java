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

                    restaurant.setReservationDate(resultSet.getDate("reservation_date"));
                    restaurant.setPrice(resultSet.getBigDecimal("price"));
                    restaurant.setImageUrl(resultSet.getString("image_url"));
                    restaurant.setAddress(resultSet.getString("address"));
                    restaurantList.add(restaurant);
                }
            }
        }

        return restaurantList;
    }
    
    public boolean createRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO Restaurants (restaurant_name, location_id, reservation_date, price, image_url, address) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, restaurant.getRestaurantName());
            preparedStatement.setInt(2, restaurant.getLocation().getLocationId());
            preparedStatement.setDate(3, new java.sql.Date(restaurant.getReservationDate().getTime()));
            preparedStatement.setBigDecimal(4, restaurant.getPrice());
            preparedStatement.setString(5, restaurant.getImageUrl());
            preparedStatement.setString(6, restaurant.getAddress());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}


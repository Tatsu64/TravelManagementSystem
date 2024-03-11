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

    public List<Restaurant> getRestaurantByTourDateId(int tourDateId) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();

//        String query = "SELECT * FROM Restaurants R "
//                + "JOIN RestaurantTour RT ON R.restaurant_id = RT.restaurant_id "
//                + "WHERE RT.tour_id = ?";

        String query = "SELECT * FROM Restaurants R \n"
                + "JOIN RestaurantTour RT ON R.restaurant_id = RT.restaurant_id \n"
                + "JOIN TourDates TD ON TD.tour_id = RT.tour_id AND TD.tour_date_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourDateId);
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

    public List<Restaurant> getAllRestaurants() throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Chuẩn bị câu truy vấn
            String query = "SELECT r.restaurant_id, r.restaurant_name, r.image_url, r.address, l.location_id, l.location_name "
                    + "FROM Restaurants r "
                    + "INNER JOIN Locations l ON r.location_id = l.location_id";
            statement = connection.prepareStatement(query);

            // Thực thi câu truy vấn
            resultSet = statement.executeQuery();

            // Duyệt qua kết quả và tạo danh sách nhà hàng
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
                restaurant.setRestaurantName(resultSet.getString("restaurant_name"));

                // Tạo đối tượng Location và đặt giá trị locationId
                Location location = new Location();
                location.setLocationId(resultSet.getInt("location_id"));
                location.setLocationName(resultSet.getString("location_name"));
                restaurant.setLocation(location);

                restaurant.setImageUrl(resultSet.getString("image_url"));
                restaurant.setAddress(resultSet.getString("address"));

                // Thêm nhà hàng vào danh sách
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        } finally {
            // Đóng kết nối, PreparedStatement và ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return restaurants;
    }

    public void createRestaurant(Restaurant restaurant) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Chuẩn bị câu truy vấn
            String query = "INSERT INTO Restaurants (restaurant_name, location_id, address, image_url) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            // Thiết lập tham số cho câu truy vấn
            statement.setString(1, restaurant.getRestaurantName());
            statement.setInt(2, restaurant.getLocation().getLocationId());
            statement.setString(3, restaurant.getAddress());
            statement.setString(4, restaurant.getImageUrl());

            // Thực thi câu truy vấn
            statement.executeUpdate();
        } finally {
            // Đóng kết nối và PreparedStatement
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteRestaurant(int restaurantId) throws SQLException {
        Connection connection = null;
        PreparedStatement deleteRestaurantStatement = null;
        PreparedStatement deleteRestaurantTourStatement = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();
            connection.setAutoCommit(false); // Bật chế độ tự commit để có thể rollback khi cần thiết

            // Xoá các bản ghi liên quan trong bảng RestaurantTour
            String deleteRestaurantTourQuery = "DELETE FROM RestaurantTour WHERE restaurant_id = ?";
            deleteRestaurantTourStatement = connection.prepareStatement(deleteRestaurantTourQuery);
            deleteRestaurantTourStatement.setInt(1, restaurantId);
            deleteRestaurantTourStatement.executeUpdate();

            // Xoá nhà hàng từ bảng Restaurants
            String deleteRestaurantQuery = "DELETE FROM Restaurants WHERE restaurant_id = ?";
            deleteRestaurantStatement = connection.prepareStatement(deleteRestaurantQuery);
            deleteRestaurantStatement.setInt(1, restaurantId);
            deleteRestaurantStatement.executeUpdate();

            // Commit các thay đổi vào cơ sở dữ liệu
            connection.commit();
        } catch (SQLException e) {
            // Rollback nếu có lỗi xảy ra
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Đóng kết nối và PreparedStatement
            try {
                if (deleteRestaurantStatement != null) {
                    deleteRestaurantStatement.close();
                }
                if (deleteRestaurantTourStatement != null) {
                    deleteRestaurantTourStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Restaurant getRestaurantById(int restaurantId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Restaurant restaurant = null;

        try {
            connection = DatabaseConnector.getConnection();
            String query = "SELECT * FROM Restaurants WHERE restaurant_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, restaurantId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                restaurant = new Restaurant();
                restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
                restaurant.setRestaurantName(resultSet.getString("restaurant_name"));
                // Lấy thông tin location_id từ cơ sở dữ liệu và tạo đối tượng Location
                Location location = new Location();
                int locationId = resultSet.getInt("location_id");
                location.setLocationId(locationId);
                // Gán location cho nhà hàng
                restaurant.setLocation(location);
                restaurant.setImageUrl(resultSet.getString("image_url"));
                restaurant.setAddress(resultSet.getString("address"));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return restaurant;
    }

    public void updateRestaurant(Restaurant restaurant) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Chuẩn bị câu truy vấn
            String query = "UPDATE Restaurants SET restaurant_name = ?, location_id = ?, address = ?, image_url = ? WHERE restaurant_id = ?";
            statement = connection.prepareStatement(query);

            // Thiết lập tham số cho câu truy vấn
            statement.setString(1, restaurant.getRestaurantName());
            statement.setInt(2, restaurant.getLocation().getLocationId());
            statement.setString(3, restaurant.getAddress());
            statement.setString(4, restaurant.getImageUrl());
            statement.setInt(5, restaurant.getRestaurantId());

            // Thực thi câu truy vấn
            statement.executeUpdate();
        } finally {
            // Đóng kết nối và PreparedStatement
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

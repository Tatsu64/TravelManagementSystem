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
import model.entity.Hotel;
import model.entity.Location;

public class HotelDAO {
    private Connection connection;

    public HotelDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Hotel> getHotelList(int locationId) throws SQLException {
        List<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM Hotels WHERE location_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setHotelId(rs.getInt("hotel_id"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    
                    // Lấy thông tin địa điểm từ cơ sở dữ liệu và đặt cho đối tượng Location
                    Location location = new Location();
                    location.setLocationId(rs.getInt("location_id"));
                    // Đặt location cho hotel
                    hotel.setLocation(location);
                    
                    hotel.setImageUrl(rs.getString("image_url"));
                    hotel.setAddress(rs.getString("address"));
                    
                    // Thêm hotel vào danh sách
                    hotelList.add(hotel);
                }
            }
        }
        return hotelList;
    }
    public List<Hotel> getHotelByTourId(int tourId) throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotels INNER JOIN HotelTour ON Hotels.hotel_id = HotelTour.hotel_id WHERE HotelTour.tour_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setHotelId(resultSet.getInt("hotel_id"));
                    hotel.setHotelName(resultSet.getString("hotel_name"));
                    hotel.setImageUrl(resultSet.getString("image_url"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotels.add(hotel);
                }
            }
        }

        return hotels;
    }
    public boolean createHotel(Hotel hotel) throws SQLException {
        String query = "INSERT INTO Hotels (hotel_name, location_id, image_url, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotel.getHotelName());
            preparedStatement.setInt(2, hotel.getLocation().getLocationId());
            preparedStatement.setString(3, hotel.getImageUrl());
            preparedStatement.setString(4, hotel.getAddress());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        }
    }
}



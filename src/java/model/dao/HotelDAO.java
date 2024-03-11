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

    public List<Hotel> getHotelByTourDateId(int tourDateId) throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotels \n"
                + "JOIN HotelTour ON Hotels.hotel_id = HotelTour.hotel_id \n"
                + "JOIN TourDates TD ON TD.tour_id = HotelTour.tour_id AND TD.tour_date_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourDateId);
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

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Chuẩn bị câu truy vấn
            String query = "SELECT h.hotel_id, h.hotel_name, h.image_url, h.address, l.location_id, l.location_name "
                    + "FROM Hotels h "
                    + "INNER JOIN Locations l ON h.location_id = l.location_id";
            statement = connection.prepareStatement(query);

            // Thực thi câu truy vấn
            resultSet = statement.executeQuery();

            // Duyệt qua kết quả và tạo danh sách khách sạn
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("hotel_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));

                // Lấy thông tin về địa điểm từ cơ sở dữ liệu và tạo đối tượng Location
                Location location = new Location();
                location.setLocationId(resultSet.getInt("location_id"));
                location.setLocationName(resultSet.getString("location_name"));
                hotel.setLocation(location);

                hotel.setImageUrl(resultSet.getString("image_url"));
                hotel.setAddress(resultSet.getString("address"));

                // Thêm khách sạn vào danh sách
                hotels.add(hotel);
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

        return hotels;
    }

    public void addHotelToDatabase(Hotel hotel) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Hotels (hotel_name, location_id, address, image_url) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, hotel.getHotelName());
            stmt.setInt(2, hotel.getLocation().getLocationId());
            stmt.setString(3, hotel.getAddress());
            stmt.setString(4, hotel.getImageUrl());

            stmt.executeUpdate();
        } finally {
            // Đóng kết nối và statement
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteHotel(int hotelId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Tắt auto-commit để thực hiện giao dịch
            connection.setAutoCommit(false);

            // Chuẩn bị câu truy vấn để xóa các tham chiếu trong bảng trung gian HotelTour
            String deleteHotelTourQuery = "DELETE FROM HotelTour WHERE hotel_id = ?";
            statement = connection.prepareStatement(deleteHotelTourQuery);
            statement.setInt(1, hotelId);
            statement.executeUpdate();
            statement.close();

            // Tiếp tục chuẩn bị câu truy vấn để xóa khách sạn từ bảng Hotels
            String deleteHotelQuery = "DELETE FROM Hotels WHERE hotel_id = ?";
            statement = connection.prepareStatement(deleteHotelQuery);
            statement.setInt(1, hotelId);
            statement.executeUpdate();

            // Commit giao dịch
            connection.commit();
        } catch (SQLException e) {
            // Nếu có lỗi, rollback giao dịch
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            // Mở lại auto-commit và đóng kết nối và PreparedStatement
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void updateHotel(Hotel hotel) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Chuẩn bị câu truy vấn để cập nhật thông tin khách sạn trong bảng Hotels
            String query = "UPDATE Hotels SET hotel_name = ?, location_id = ?, image_url = ?, address = ? WHERE hotel_id = ?";
            statement = connection.prepareStatement(query);

            // Thiết lập các tham số cho câu truy vấn
            statement.setString(1, hotel.getHotelName());
            statement.setInt(2, hotel.getLocation().getLocationId());
            statement.setString(3, hotel.getImageUrl());
            statement.setString(4, hotel.getAddress());
            statement.setInt(5, hotel.getHotelId());

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

    public Hotel getHotelById(int hotelId) throws SQLException {
        Hotel hotel = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Lấy kết nối từ DatabaseConnector
            connection = DatabaseConnector.getConnection();

            // Chuẩn bị câu truy vấn
            String query = "SELECT * FROM Hotels WHERE hotel_id = ?";
            statement = connection.prepareStatement(query);

            // Thiết lập tham số cho câu truy vấn
            statement.setInt(1, hotelId);

            // Thực thi câu truy vấn
            resultSet = statement.executeQuery();

            // Kiểm tra nếu có kết quả trả về
            if (resultSet.next()) {
                // Tạo đối tượng Hotel từ kết quả trả về
                hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("hotel_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));

                // Lấy thông tin về địa điểm từ cơ sở dữ liệu và tạo đối tượng Location
                Location location = new Location();
                location.setLocationId(resultSet.getInt("location_id"));
                hotel.setLocation(location);

                hotel.setImageUrl(resultSet.getString("image_url"));
                hotel.setAddress(resultSet.getString("address"));
            }
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

        return hotel;
    }

}

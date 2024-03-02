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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.database.DatabaseConnector;
import model.entity.User;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    public User checkLogIn(String email, String password) {
        try {
            Connection con = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Users WHERE email = ? AND CONVERT(VARCHAR(MAX), password) = ?";

            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);

                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        User user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("address"), rs.getString("phone"), rs.getString("role"));
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return null;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setRole(resultSet.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setRole(resultSet.getString("role"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return userList;
    }

    public boolean updateUserProfile(User user) {
        boolean success = false;
        String sql = "UPDATE Users SET name = ?, password = ?, email = ?, address = ?, phone = ?, role = ? WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getRole());
            statement.setInt(7, user.getUserId());

            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return success;
    }

    /*
    public boolean checkPassword(int userID, String password) {
        boolean success = false;
        String sql = "SELECT * FROM Users WHERE user_id = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            success = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
     */
    public void addUser(User user) {
        String sql = "INSERT INTO Users (name, password, email, address, phone, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getRole());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void deleteUser(int userId) throws SQLException {
    Connection connection = null;
    PreparedStatement deleteReviewStatement = null;
    PreparedStatement deleteBillStatement = null;
    PreparedStatement deleteBookingStatement = null;
    PreparedStatement deleteUserStatement = null;

    try {
        connection = DatabaseConnector.getConnection();
        connection.setAutoCommit(false); // Bắt đầu một transaction

        // Xoá tất cả các hóa đơn của người dùng từ bảng Bills
        String deleteBillQuery = "DELETE FROM Bills WHERE booking_id IN (SELECT booking_id FROM Bookings WHERE user_id = ?)";
        deleteBillStatement = connection.prepareStatement(deleteBillQuery);
        deleteBillStatement.setInt(1, userId);
        deleteBillStatement.executeUpdate();

        // Xoá tất cả các đánh giá của người dùng từ bảng Reviews
        String deleteReviewQuery = "DELETE FROM Reviews WHERE booking_id IN (SELECT booking_id FROM Bookings WHERE user_id = ?)";
        deleteReviewStatement = connection.prepareStatement(deleteReviewQuery);
        deleteReviewStatement.setInt(1, userId);
        deleteReviewStatement.executeUpdate();

        // Xoá tất cả các đặt phòng của người dùng từ bảng Bookings
        String deleteBookingQuery = "DELETE FROM Bookings WHERE user_id = ?";
        deleteBookingStatement = connection.prepareStatement(deleteBookingQuery);
        deleteBookingStatement.setInt(1, userId);
        deleteBookingStatement.executeUpdate();

        // Xoá người dùng từ bảng Users
        String deleteUserQuery = "DELETE FROM Users WHERE user_id = ?";
        deleteUserStatement = connection.prepareStatement(deleteUserQuery);
        deleteUserStatement.setInt(1, userId);
        deleteUserStatement.executeUpdate();

        connection.commit(); // Commit transaction nếu không có lỗi xảy ra
    } catch (SQLException e) {
        if (connection != null) {
            connection.rollback(); // Rollback transaction nếu có lỗi
        }
        e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
    } finally {
        // Đóng các resource
        if (deleteBillStatement != null) {
            deleteBillStatement.close();
        }
        if (deleteReviewStatement != null) {
            deleteReviewStatement.close();
        }
        if (deleteBookingStatement != null) {
            deleteBookingStatement.close();
        }
        if (deleteUserStatement != null) {
            deleteUserStatement.close();
        }
    }
}


    public boolean checkUserExist(String email, String name) {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ? OR name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Returns true if either email or name exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exception appropriately
        }
        return false; // Returns false if there is an SQL error or neither email nor name is found
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM Users WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return null;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("role")
        );
    }

    public boolean newUser(String name, String phone, String email, String password, String address, String role) throws Exception {
        String sql = "INSERT INTO Users (name, phone, email, password, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, address);
            statement.setString(6, role);

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void updatePassword(String newpassword, int id) {
        String query = "UPDATE Users SET password = ? WHERE user_id = ?";
        Connection conn;
        PreparedStatement statement;
        try {
            Connection connection = DatabaseConnector.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, newpassword);
            statement.setInt(2, id);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean changePassword(String email, String newPassword) {
        try {
            Connection con = DatabaseConnector.getConnection();
            String sql = "UPDATE Users SET password = ? WHERE email = ?";

            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setString(1, newPassword);
                statement.setString(2, email);
                int rowsUpdated = statement.executeUpdate();

                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
            return false;
        }
    }
}

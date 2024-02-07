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
import model.entity.Employee;
import model.entity.Tour;
import model.entity.TourTransportation;

public class TourDAO {
    private Connection connection;

    public TourDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    // Create
    public void addTour(Tour tour) {
        try {
            String query = "INSERT INTO Tours (tour_name, description, start_date, end_date, tour_price, image_url, employee_id, start_location, max_capacity, current_capacity, approval_status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tour.getTourName());
                statement.setString(2, tour.getDescription());
                statement.setDate(3, new java.sql.Date(tour.getStartDate().getTime()));
                statement.setDate(4, new java.sql.Date(tour.getEndDate().getTime()));
                statement.setBigDecimal(5, tour.getTourPrice());
                statement.setString(6, tour.getImageUrl());
                statement.setInt(7, tour.getEmployee().getEmployeeId());
                statement.setString(8, tour.getStartLocation());
                statement.setInt(9, tour.getMaxCapacity());
                statement.setInt(10, tour.getCurrentCapacity());
                statement.setInt(11, tour.getApprovalStatus()); // Set the approval status

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    public static int insertTourAndGetId(Connection connection, Tour tour) {
        int generatedTourId = -1;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Tours (tour_name, description, start_date, end_date, tour_price, image_url, employee_id, start_location, max_capacity, current_capacity, approval_status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, tour.getTourName());
            preparedStatement.setString(2, tour.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(tour.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(tour.getEndDate().getTime()));
            preparedStatement.setBigDecimal(5, tour.getTourPrice());
            preparedStatement.setString(6, tour.getImageUrl());
            preparedStatement.setInt(7, tour.getEmployee().getEmployeeId());
            preparedStatement.setString(8, tour.getStartLocation());
            preparedStatement.setInt(9, tour.getMaxCapacity());
            preparedStatement.setInt(10, tour.getCurrentCapacity());
            preparedStatement.setInt(11, tour.getApprovalStatus()); // Set the approval status

            // Execute the insertion
            int affectedRows = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedTourId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating tour failed, no ID obtained.");
                }
                generatedKeys.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or handle it appropriately
        } finally {
            // Close the PreparedStatement in the finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return generatedTourId;
    }



    // Phương thức để thêm tour_id và transportation_id vào bảng TourTransportations
    public boolean addTourTransportation(TourTransportation tourTransportation) {
        // Câu lệnh SQL để chèn dữ liệu vào bảng TourTransportations
        String sql = "INSERT INTO TourTransportations (tour_id, transportation_id) VALUES (?, ?)";

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement statement = connection.prepareStatement(sql);

            // Thiết lập các tham số từ đối tượng TourTransportation
            statement.setInt(1, tourTransportation.getTourId());
            statement.setInt(2, tourTransportation.getTransportationId());

            // Thực thi câu lệnh SQL
            int rowsInserted = statement.executeUpdate();

            // Kiểm tra xem có hàng nào được chèn không
            if (rowsInserted > 0) {
                System.out.println("TourTransportation đã được thêm vào bảng thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm TourTransportation vào bảng: " + e.getMessage());
        }

        return false;
    }


    // Read
    public Tour getTourById(int tourId) {
        try {
            String query = "SELECT * FROM Tours WHERE tour_id = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tourId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractTourFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return null;
    }

    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();

        try {
            String query = "SELECT * FROM Tours";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tour tour = extractTourFromResultSet(resultSet);
                    tours.add(tour);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return tours;
    }

    // Update
    public void updateTour(Tour tour) {
        try {
            String query = "UPDATE Tours SET tour_name = ?, description = ?, start_date = ?, end_date = ?, "
                    + "tour_price = ?, image_url = ?, employee_id = ?, start_location = ?, max_capacity = ?, current_capacity = ?, "
                    + "approval_status = ? "
                    + // Include approval_status in the update query
                    "WHERE tour_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tour.getTourName());
                statement.setString(2, tour.getDescription());
                statement.setDate(3, new java.sql.Date(tour.getStartDate().getTime()));
                statement.setDate(4, new java.sql.Date(tour.getEndDate().getTime()));
                statement.setBigDecimal(5, tour.getTourPrice());
                statement.setString(6, tour.getImageUrl());
                statement.setInt(7, tour.getEmployee().getEmployeeId());
                statement.setString(8, tour.getStartLocation());
                statement.setInt(9, tour.getMaxCapacity());
                statement.setInt(10, tour.getCurrentCapacity());
                statement.setInt(11, tour.getApprovalStatus()); // Set the approval status
                statement.setInt(12, tour.getTourId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }


    // Delete
    public void deleteTour(int tourId) {
        try {
            String query = "DELETE FROM Tours WHERE tour_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tourId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    // Helper method to extract a Tour from a ResultSet
  private Tour extractTourFromResultSet(ResultSet resultSet) throws SQLException {
    Tour tour = new Tour();
    tour.setTourId(resultSet.getInt("tour_id"));
    tour.setTourName(resultSet.getString("tour_name"));
    tour.setDescription(resultSet.getString("description"));
    tour.setStartDate(resultSet.getDate("start_date"));
    tour.setEndDate(resultSet.getDate("end_date"));
    tour.setTourPrice(resultSet.getBigDecimal("tour_price"));
    tour.setImageUrl(resultSet.getString("image_url"));

    // Fetch and set the Employee object
    int employeeId = resultSet.getInt("employee_id");
    EmployeeDAO employeeDAO = new EmployeeDAO(connection); // Create an instance of EmployeeDAO
    Employee employee = employeeDAO.getEmployeeById(employeeId); // Fetch the employee from the database
    tour.setEmployee(employee); // Set the employee in the tour object

    tour.setStartLocation(resultSet.getString("start_location"));
    tour.setMaxCapacity(resultSet.getInt("max_capacity"));
    tour.setCurrentCapacity(resultSet.getInt("current_capacity"));
    
    tour.setApprovalStatus(resultSet.getInt("approval_status")); // Set the approval status

    return tour;
}

}


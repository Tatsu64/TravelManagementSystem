/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author ADMIN
 */
import model.entity.HomeTour;
import static helper.Helper.convertToLocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.database.DatabaseConnector;
import model.entity.Employee;
import model.entity.HotelTour;
import model.entity.RestaurantTour;
import model.entity.Tour;
import model.entity.TourTransportation;
import model.entity.Transportation;

public class TourDAO {

    private Connection connection;

    public TourDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    // Create
    public void addTour(Tour tour) {
        try {
            String query = "INSERT INTO Tours (tour_name, description, tour_price, image_url, employee_id, start_location, max_capacity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tour.getTourName());
                statement.setString(2, tour.getDescription());
                statement.setBigDecimal(3, tour.getTourPrice());
                statement.setString(4, tour.getImageUrl());
                statement.setInt(5, tour.getEmployee().getEmployeeId());
                statement.setString(6, tour.getStartLocation());
                statement.setInt(7, tour.getMaxCapacity());

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
                    "INSERT INTO Tours (tour_name, description, tour_price, image_url, employee_id, start_location, max_capacity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, tour.getTourName());
            preparedStatement.setString(2, tour.getDescription());
            preparedStatement.setBigDecimal(3, tour.getTourPrice());
            preparedStatement.setString(4, tour.getImageUrl());
            preparedStatement.setInt(5, tour.getEmployee().getEmployeeId());
            preparedStatement.setString(6, tour.getStartLocation());
            preparedStatement.setInt(7, tour.getMaxCapacity());

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
        String sql = "INSERT INTO TourTransportation (tour_id, transportation_id) VALUES (?, ?)";

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

    public void updateTourTransportation(int tourId, List<Integer> selectedTransportations) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            // Xóa tất cả các bản ghi về vận chuyển của tour trong bảng trung gian trước khi thêm lại thông tin vận chuyển mới
            String deleteQuery = "DELETE FROM TourTransportation WHERE tour_id = ?";
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, tourId);
            stmt.executeUpdate();

            // Thêm thông tin vận chuyển mới vào bảng trung gian
            String insertQuery = "INSERT INTO TourTransportation (tour_id, transportation_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            for (int transportationId : selectedTransportations) {
                stmt.setInt(1, tourId);
                stmt.setInt(2, transportationId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức lấy danh sách các IDs của transportation liên kết với một tour cụ thể
    public List<Integer> getTourTransportationIds(int tourId) throws SQLException {
        List<Integer> transportationIds = new ArrayList<>();

        String query = "SELECT transportation_id FROM tour_transportation WHERE tour_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int transportationId = resultSet.getInt("transportation_id");
                    transportationIds.add(transportationId);
                }
            }
        }

        return transportationIds;
    }

    public void addTourLocation(int tourId, int locationId) throws SQLException {
        String sql = "INSERT INTO TourLocation (tour_id, location_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tourId);
            statement.setInt(2, locationId);
            statement.executeUpdate();
        }
    }

    // Phương thức để thêm hotel_id và tour_id vào bảng HotelTour
    public boolean addHotelTour(HotelTour hotelTour) {
        // Câu lệnh SQL để chèn dữ liệu vào bảng HotelTour
        String sql = "INSERT INTO HotelTour (hotel_id, tour_id) VALUES (?, ?)";

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement statement = connection.prepareStatement(sql);

            // Thiết lập các tham số từ đối tượng HotelTour
            statement.setInt(1, hotelTour.getHotelId());
            statement.setInt(2, hotelTour.getTourId());

            // Thực thi câu lệnh SQL
            int rowsInserted = statement.executeUpdate();

            // Kiểm tra xem có hàng nào được chèn không
            if (rowsInserted > 0) {
                System.out.println("HotelTour đã được thêm vào bảng thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm HotelTour vào bảng: " + e.getMessage());
        }

        return false;
    }

    // Phương thức để thêm restaurant_id và tour_id vào bảng RestaurantTour
    public boolean addRestaurantTour(RestaurantTour restaurantTour) {
        // Câu lệnh SQL để chèn dữ liệu vào bảng RestaurantTour
        String sql = "INSERT INTO RestaurantTour (restaurant_id, tour_id) VALUES (?, ?)";

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement statement = connection.prepareStatement(sql);

            // Thiết lập các tham số từ đối tượng RestaurantTour
            statement.setInt(1, restaurantTour.getRestaurantId());
            statement.setInt(2, restaurantTour.getTourId());

            // Thực thi câu lệnh SQL
            int rowsInserted = statement.executeUpdate();

            // Kiểm tra xem có hàng nào được chèn không
            if (rowsInserted > 0) {
                System.out.println("RestaurantTour đã được thêm vào bảng thành công!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm RestaurantTour vào bảng: " + e.getMessage());
        }

        return false;
    }

    public void updateHotelTour(int tourId, List<Integer> hotelIds) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            // Xóa tất cả các bản ghi về khách sạn của tour trong bảng trung gian trước khi thêm lại thông tin khách sạn mới
            String deleteQuery = "DELETE FROM HotelTour WHERE tour_id = ?";
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, tourId);
            stmt.executeUpdate();

            // Thêm thông tin khách sạn mới vào bảng trung gian
            String insertQuery = "INSERT INTO HotelTour (tour_id, hotel_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            for (int hotelId : hotelIds) {
                stmt.setInt(1, tourId);
                stmt.setInt(2, hotelId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRestaurantTour(int tourId, List<Integer> restaurantIds) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            // Xóa tất cả các bản ghi về nhà hàng của tour trong bảng trung gian trước khi thêm lại thông tin nhà hàng mới
            String deleteQuery = "DELETE FROM RestaurantTour WHERE tour_id = ?";
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, tourId);
            stmt.executeUpdate();

            // Thêm thông tin nhà hàng mới vào bảng trung gian
            String insertQuery = "INSERT INTO RestaurantTour (tour_id, restaurant_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            for (int restaurantId : restaurantIds) {
                stmt.setInt(1, tourId);
                stmt.setInt(2, restaurantId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tour> getTours() {
        List<Tour> tours = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Tạo câu lệnh SQL
            String sql = "SELECT t.*, e.* FROM Tours t JOIN Employees e ON t.employee_id = e.employee_id ";
            // Chuẩn bị statement
            stmt = connection.prepareStatement(sql);
            // Thực thi truy vấn
            rs = stmt.executeQuery();
            // Xử lý kết quả
            while (rs.next()) {
                Tour tour = new Tour();
                Employee employee = new Employee(); // Tạo một đối tượng Employee cho mỗi tour
                tour.setTourId(rs.getInt("tour_id"));
                tour.setTourName(rs.getString("tour_name"));
                tour.setDescription(rs.getString("description"));
                tour.setTourPrice(rs.getBigDecimal("tour_price"));
                tour.setImageUrl(rs.getString("image_url"));
                // Đặt các trường Employee từ ResultSet
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPosition(rs.getString("position"));
                // Đặt đối tượng Employee cho tour
                tour.setEmployee(employee);
                tour.setStartLocation(rs.getString("start_location"));
                tour.setMaxCapacity(rs.getInt("max_capacity"));
                tours.add(tour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối, statement và result set
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tours;
    }

    public List<Transportation> getTransportationsForTour(int tourId) {
        List<Transportation> transportations = new ArrayList<>();
        String query = "SELECT * FROM Transportations "
                + "INNER JOIN TourTransportation ON Transportations.transportation_id = TourTransportation.transportation_id "
                + "WHERE TourTransportation.tour_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tourId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Transportation transportation = new Transportation();
                    transportation.setTransportationId(resultSet.getInt("transportation_id"));
                    transportation.setTransportationName(resultSet.getString("transportation_name"));
                    transportation.setImageUrl(resultSet.getString("image_url"));
                    transportation.setDepartureTime(resultSet.getTime("departure_time"));
                    transportation.setReturnTime(resultSet.getTime("return_time"));
                    // Các thông tin khác của Transportation có sẵn trong cơ sở dữ liệu

                    transportations.add(transportation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
        }
        return transportations;
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

    public static HomeTour getHomeTourById(int tourId) {
        try {
            String query = "select * from Tours t join TourDates td on t.tour_id = td.tour_id join TourLocation tl on tl.tour_id = t.tour_id join Locations l on l.location_id = tl.location_id where t.tour_id = ?";

            try (PreparedStatement statement = DatabaseConnector.connection.prepareStatement(query)) {
                statement.setInt(1, tourId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    HomeTour t = extractHomeTourFromResultSet(resultSet);
                    String sl = resultSet.getNString("location_name");
                    if (sl != null) {
                        t.setSecondlocation(sl);
                    }
                    return t;
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
            String query = "select * from Tours t join TourDates td on t.tour_id = td.tour_id";

            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
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

    public static List<HomeTour> getAllHomeTours() {
        List<HomeTour> tours = new ArrayList<>();

        try {
            String query = "select * from Tours t join TourDates td on t.tour_id = td.tour_id where td.start_date > GETDATE() and td.end_date < DATEADD(month, 1, GETDATE())";

            try (PreparedStatement statement = DatabaseConnector.connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    HomeTour tour = extractHomeTourFromResultSet(resultSet);
                    tours.add(tour);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return tours;
    }

    public void deleteTour(int tourId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnector.getConnection();

            // Xóa các bản ghi từ bảng Reviews có bookingId tương ứng với tourId
            String deleteReviewsQuery = "DELETE FROM Reviews WHERE booking_id IN (SELECT booking_id FROM Bookings WHERE tour_id = ?)";
            statement = connection.prepareStatement(deleteReviewsQuery);
            statement.setInt(1, tourId);
            statement.executeUpdate();
            statement.close();

            // Xóa các bản ghi từ bảng Bills có bookingId tương ứng với tourId
            String deleteBillsQuery = "DELETE FROM Bills WHERE booking_id IN (SELECT booking_id FROM Bookings WHERE tour_id = ?)";
            statement = connection.prepareStatement(deleteBillsQuery);
            statement.setInt(1, tourId);
            statement.executeUpdate();
            statement.close();

            // Xóa các bản ghi từ bảng Bookings có tourId tương ứng
            String deleteBookingsQuery = "DELETE FROM Bookings WHERE tour_id = ?";
            statement = connection.prepareStatement(deleteBookingsQuery);
            statement.setInt(1, tourId);
            statement.executeUpdate();
            statement.close();

            // Xóa các bản ghi từ các bảng trung gian và TourDates
            String[] deleteQueries = {
                "DELETE FROM ActivitySchedules WHERE tour_id = ?",
                "DELETE FROM TourDates WHERE tour_id = ?",
                "DELETE FROM HotelTour WHERE tour_id = ?",
                "DELETE FROM RestaurantTour WHERE tour_id = ?",
                "DELETE FROM TourLocation WHERE tour_id = ?",
                "DELETE FROM TourTransportation WHERE tour_id = ?"
            };

            for (String query : deleteQueries) {
                statement = connection.prepareStatement(query);
                statement.setInt(1, tourId);
                statement.executeUpdate();
                statement.close();
            }

            // Xóa tour từ bảng Tours
            String deleteTourQuery = "DELETE FROM Tours WHERE tour_id = ?";
            statement = connection.prepareStatement(deleteTourQuery);
            statement.setInt(1, tourId);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log lỗi
        }
    }

    public void updateTour(Tour tour) {
        String query = "UPDATE Tours SET tour_name=?, description=?, tour_price=?, image_url=?, employee_id=?, start_location=?, max_capacity=? WHERE tour_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tour.getTourName());
            preparedStatement.setString(2, tour.getDescription());
            preparedStatement.setBigDecimal(3, tour.getTourPrice());
            preparedStatement.setString(4, tour.getImageUrl());
            preparedStatement.setInt(5, tour.getEmployee().getEmployeeId());
            preparedStatement.setString(6, tour.getStartLocation());
            preparedStatement.setInt(7, tour.getMaxCapacity());
            preparedStatement.setInt(8, tour.getTourId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract a Tour from a ResultSet
    private Tour extractTourFromResultSet(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setTourId(resultSet.getInt("tour_id"));
        tour.setTourName(resultSet.getString("tour_name"));
        tour.setDescription(resultSet.getString("description"));
        tour.setTourPrice(resultSet.getBigDecimal("tour_price"));
        tour.setImageUrl(resultSet.getString("image_url"));

        // Fetch and set the Employee object
        int employeeId = resultSet.getInt("employee_id");
        EmployeeDAO employeeDAO = new EmployeeDAO(connection); // Create an instance of EmployeeDAO
        Employee employee = employeeDAO.getEmployeeById(employeeId); // Fetch the employee from the database
        tour.setEmployee(employee); // Set the employee in the tour object

        tour.setStartLocation(resultSet.getString("start_location"));
        tour.setMaxCapacity(resultSet.getInt("max_capacity"));
        tour.setStartDate(resultSet.getDate("start_date"));
        tour.setEndDate(resultSet.getDate("end_date"));
        return tour;
    }

    private static HomeTour extractHomeTourFromResultSet(ResultSet resultSet) throws SQLException {
        HomeTour tour = new HomeTour();
        tour.setTourId(resultSet.getInt("tour_id"));
        tour.setDescription(resultSet.getString("description"));
        Date startdate = resultSet.getDate("start_date");
        Date endate = resultSet.getDate("end_date");
        tour.setDateStart(startdate.toString());
        tour.setDateEnd(endate.toString());
        int days = (int) ChronoUnit.DAYS.between(convertToLocalDate(startdate), convertToLocalDate(endate));
        tour.setDay(days);
        tour.setPrice(resultSet.getDouble("tour_price"));
        tour.setImage(resultSet.getString("image_url"));

        tour.setLocation(resultSet.getString("start_location"));

        tour.setPerson(resultSet.getInt("max_capacity"));

        return tour;
    }

    public static List<HomeTour> getHomeToursByStartDateAndLocation(String location, String startdate) {
        List<HomeTour> tours = new ArrayList<>();

        try {
            String query = "select * from Tours t join TourDates td on t.tour_id = td.tour_id join TourLocation tl on t.tour_id = tl.tour_id\n"
                    + "join Locations l on l.location_id = tl.location_id\n"
                    + "where td.start_date = ? and t.start_location = ?";

            try (PreparedStatement statement = DatabaseConnector.connection.prepareStatement(query)) {
                statement.setString(1, startdate);
                statement.setString(2, location);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    HomeTour tour = extractHomeTourFromResultSet(resultSet);
                    tour.setSecondlocation(resultSet.getNString("location_name"));
                    tours.add(tour);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return tours;
    }

}

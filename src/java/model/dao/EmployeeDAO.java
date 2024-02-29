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
import model.entity.Employee;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    // Replace this method with your actual implementation to fetch the employee list from the database
    public List<Employee> getEmployeeList() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();

        // Use a try-with-resources statement to automatically close resources (PreparedStatement and ResultSet)
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employees"); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Create Employee objects and add them to the list
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFullName(resultSet.getString("full_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPosition(resultSet.getString("position"));

                employeeList.add(employee);
            }
        }

        return employeeList;
    }
    // Phương thức để lấy thông tin nhân viên bằng employee_id

     public Employee getEmployeeById(int employeeId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM Employees WHERE employee_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Tạo một đối tượng Employee từ dữ liệu trong ResultSet
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPosition(rs.getString("position"));
                // Các trường thông tin khác cũng có thể được đặt ở đây tùy thuộc vào cấu trúc của bảng Employees
            }
        } finally {
            // Đóng các tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            // Không đóng kết nối ở đây để tiếp tục sử dụng kết nối cho các công việc khác
        }

        return employee;
    }

    public void createEmployee(Employee employee) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Tạo câu lệnh SQL để chèn một nhân viên mới vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO Employees (full_name, email, position) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(insertQuery);

            // Thiết lập các tham số cho câu lệnh SQL
            pstmt.setString(1, employee.getFullName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPosition());

            // Thực thi câu lệnh SQL để thêm nhân viên mới
            pstmt.executeUpdate();
        } finally {
            // Đóng các tài nguyên
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    public void deleteEmployee(int employeeId) throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            String deleteQuery = "DELETE FROM Employees WHERE employee_id = ?";
            pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
     public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            // Tạo câu lệnh SQL để cập nhật thông tin nhân viên
            String updateQuery = "UPDATE Employees SET full_name = ?, email = ?, position = ? WHERE employee_id = ?";
            pstmt = connection.prepareStatement(updateQuery);
            
            // Thiết lập các tham số cho câu lệnh SQL
            pstmt.setString(1, employee.getFullName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPosition());
            pstmt.setInt(4, employee.getEmployeeId());
            
            // Thực thi câu lệnh SQL để cập nhật thông tin nhân viên
            pstmt.executeUpdate();
        } finally {
            // Đóng PreparedStatement
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}

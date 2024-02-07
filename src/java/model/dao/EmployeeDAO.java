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
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employees");
             ResultSet resultSet = statement.executeQuery()) {
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

    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM Employees WHERE employee_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, employeeId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        employee = new Employee();
                        employee.setEmployeeId(resultSet.getInt("employee_id"));
                        employee.setFullName(resultSet.getString("full_name"));
                        employee.setEmail(resultSet.getString("email"));
                        employee.setPosition(resultSet.getString("position"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log cho ngoại lệ phù hợp
        }
        return employee;
    }
}


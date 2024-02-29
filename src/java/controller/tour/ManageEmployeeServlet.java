/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmployeeDAO;
import model.database.DatabaseConnector;
import model.entity.Employee;

/**
 *
 * @author ADMIN
 */
public class ManageEmployeeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageEmployeeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageEmployeeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = DatabaseConnector.getConnection();

            // Create an instance of EmployeeDAO
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);

            // Fetch the list of employees
            List<Employee> employeeList = employeeDAO.getEmployeeList();

            request.setAttribute("employeeList", employeeList);
            request.getRequestDispatcher("/Employee.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManageEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin của nhân viên từ request
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String position = request.getParameter("position");

        // Tạo đối tượng Employee từ thông tin đã lấy
        Employee newEmployee = new Employee(fullName, email, position);

        // Thêm nhân viên vào cơ sở dữ liệu bằng cách gọi phương thức createEmployee trong EmployeeDAO
        try {
            Connection connection = DatabaseConnector.getConnection();
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            employeeDAO.createEmployee(newEmployee);
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log lỗi
        }

        // Sau khi thêm nhân viên, chuyển hướng người dùng đến trang danh sách nhân viên
        response.sendRedirect("ManageEmployeeServlet?");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

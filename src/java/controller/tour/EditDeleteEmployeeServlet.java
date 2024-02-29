/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class EditDeleteEmployeeServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteEmployeeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteEmployeeServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "update":
                    // Xử lý cập nhật nhân viên
                    updateEmployee(request, response);
                    break;
                case "delete":
                    // Xử lý xóa nhân viên
                    deleteEmployee(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp"); // Hoặc xử lý lỗi khác
                    break;
            }
        } else {
            response.sendRedirect("error.jsp"); // Hoặc xử lý lỗi khác
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeIdStr = request.getParameter("employeeId");

        // Kiểm tra xem employeeId có tồn tại không
        if (employeeIdStr != null && !employeeIdStr.isEmpty()) {
            try {
                // Chuyển đổi employeeId từ String sang int
                int employeeId = Integer.parseInt(employeeIdStr);

                // Tạo một đối tượng EmployeeDAO để truy vấn cơ sở dữ liệu
                EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnector.getConnection());

                // Gọi phương thức getEmployeeById để lấy thông tin của nhân viên
                Employee employee = employeeDAO.getEmployeeById(employeeId);

                // Kiểm tra xem employee có tồn tại không
                if (employee != null) {
                    // Đặt thuộc tính employee vào request để chuyển tiếp đến jsp
                    request.setAttribute("employee", employee);

                    // Chuyển tiếp đến trang jsp để hiển thị thông tin của nhân viên
                    request.getRequestDispatcher("/UpdateEmployee.jsp").forward(request, response);
                } else {
                    response.sendRedirect("404.jsp");
                }
            } catch (NumberFormatException | SQLException e) {
                response.sendRedirect("404.jsp");
            }
        } else {
            // Nếu không có employeeId được cung cấp, chuyển hướng đến trang lỗi hoặc thông báo lỗi
            response.sendRedirect("404.jsp");
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeIdStr = request.getParameter("employeeId");
        if (employeeIdStr != null && !employeeIdStr.isEmpty()) {
            try {
                int employeeId = Integer.parseInt(employeeIdStr);
                EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnector.getConnection());
                try {
                    employeeDAO.deleteEmployee(employeeId);
                } catch (SQLException ex) {
                    Logger.getLogger(EditDeleteEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Chuyển hướng hoặc gửi thông báo thành công
                response.sendRedirect("ManageEmployeeServlet?");
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ khi không thể chuyển đổi employeeId thành số nguyên
                response.sendRedirect("error.jsp");
            }
        } else {
            // Xử lý khi employeeId không tồn tại trong request
            response.sendRedirect("error.jsp");
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
        // Lấy thông tin về nhân viên từ request
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String position = request.getParameter("position");
        
        // Tạo đối tượng Employee từ thông tin đã lấy
        Employee employee = new Employee(employeeId, fullName, email, position);
        
        // Gọi phương thức updateEmployee trong EmployeeDAO để cập nhật thông tin
        EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnector.getConnection());
        try {
            employeeDAO.updateEmployee(employee);
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log lỗi
        }
        
        // Sau khi cập nhật, chuyển hướng về trang danh sách nhân viên
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

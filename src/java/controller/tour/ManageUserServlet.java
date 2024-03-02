/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.database.DatabaseConnector;
import model.entity.User;

/**
 *
 * @author ADMIN
 */
public class ManageUserServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageUserServlet at " + request.getContextPath() + "</h1>");
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
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Kiểm tra nếu yêu cầu là để xóa người dùng
    if ("delete".equals(request.getParameter("action"))) {
        deleteUser(request, response);
    } else { // Nếu không, tải danh sách người dùng
        loadUsers(request, response);
    }
}
   private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy userId từ request
    int userId = Integer.parseInt(request.getParameter("userId"));
    
    // Tạo đối tượng UserDAO
    UserDAO userDAO = new UserDAO(DatabaseConnector.getConnection());
    
    try {
        // Gọi phương thức deleteUser từ UserDAO để xóa người dùng
        userDAO.deleteUser(userId);
        
        // Chuyển hướng người dùng đến trang quản lý người dùng sau khi xóa thành công
        response.sendRedirect("ManageUserServlet?");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    private void loadUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     // Tạo một đối tượng UserDAO
        UserDAO userDAO = new UserDAO(DatabaseConnector.getConnection());

        // Gọi phương thức getAllUsers() để lấy danh sách người dùng
        List<User> userList = userDAO.getAllUsers();

        // Đặt danh sách người dùng vào thuộc tính của request
        request.setAttribute("userList", userList);

        // Chuyển hướng sang trang JSP để hiển thị danh sách người dùng
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

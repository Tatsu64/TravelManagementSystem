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
import model.dao.LocationDAO;
import model.database.DatabaseConnector;
import model.entity.Location;

/**
 *
 * @author ADMIN
 */
public class EditDeleteLocationServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteLocationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteLocationServlet at " + request.getContextPath() + "</h1>");
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
        // Lấy tham số action từ request để xác định yêu cầu của người dùng là delete hay update
        String action = request.getParameter("action");

        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "delete":
                    handleDeleteRequest(request, response);
                    break;
                case "update":
                    handleUpdateRequest(request, response);
                    break;
                default:
                    // Xử lý trường hợp không hợp lệ
                    response.sendRedirect("404.jsp");
            }
        } else {
            // Xử lý trường hợp action không được cung cấp
            response.sendRedirect("404.jsp");
        }
    }

    private void handleDeleteRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu xóa ở đây
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
        try {
            locationDAO.deleteLocation(locationId);
            response.sendRedirect("LocationServlet?");
        } catch (SQLException ex) {
            Logger.getLogger(LocationServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("404.jsp");
        }
    }

    private void handleUpdateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
        // Lấy locationId từ request
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        
        // Tạo đối tượng LocationDAO
        LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
        
        // Gọi phương thức getLocationById để lấy thông tin của địa điểm
        Location location = locationDAO.getLocationById(locationId);
        
        // Đặt thông tin địa điểm vào attribute của request để chuyển tiếp đến jsp
        request.setAttribute("location", location);
        
        // Chuyển tiếp request và response đến trang jsp để hiển thị thông tin của địa điểm
        request.getRequestDispatcher("UpdateLocation.jsp").forward(request, response);
    } catch (SQLException | NumberFormatException e) {
        // Xử lý ngoại lệ
        e.printStackTrace();
        // Hoặc chuyển hướng đến trang lỗi
        response.sendRedirect("404.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Xử lý yêu cầu cập nhật
    int locationId = Integer.parseInt(request.getParameter("locationId"));
    String locationName = request.getParameter("locationName");

    // Tạo một đối tượng Location mới với thông tin cập nhật
    Location updatedLocation = new Location();
    updatedLocation.setLocationId(locationId);
    updatedLocation.setLocationName(locationName);

    // Gọi phương thức updateLocation trong LocationDAO để cập nhật thông tin địa điểm
    LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
    try {
        locationDAO.updateLocation(updatedLocation);
        // Chuyển hướng người dùng đến trang hiển thị danh sách địa điểm sau khi cập nhật thành công
        response.sendRedirect("LocationServlet");
    } catch (SQLException ex) {
        Logger.getLogger(LocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("404.jsp");
    }
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

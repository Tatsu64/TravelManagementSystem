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
public class LocationServlet extends HttpServlet {

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
            out.println("<title>Servlet LocationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LocationServlet at " + request.getContextPath() + "</h1>");
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
            LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
            List<Location> locations = locationDAO.getLocationList();
            // Truyền danh sách này đến CreateTour.jsp để hiển thị
            request.setAttribute("locations", locations);
             request.getRequestDispatcher("/Location.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LocationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        // Lấy thông tin về địa điểm từ request
        String locationName = request.getParameter("locationName");

        // Tạo một đối tượng Location từ thông tin đã lấy được
        Location newLocation = new Location();
        newLocation.setLocationName(locationName);

        // Tạo một đối tượng LocationDAO để thực hiện thêm mới địa điểm vào cơ sở dữ liệu
        LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());

        try {
            // Thực hiện thêm mới địa điểm
            locationDAO.createLocation(newLocation);
            
            // Chuyển hướng về trang hoặc hiển thị thông báo thành công
            response.sendRedirect("LocationServlet?");
        } catch (SQLException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
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

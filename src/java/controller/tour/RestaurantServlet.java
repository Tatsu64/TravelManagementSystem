/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.RestaurantDAO;
import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.Location;
import model.entity.Restaurant;
import model.entity.RestaurantTour;

/**
 *
 * @author ADMIN
 */
public class RestaurantServlet extends HttpServlet {

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
            out.println("<title>Servlet RestaurantServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RestaurantServlet at " + request.getContextPath() + "</h1>");
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
            String tourIdStr = request.getParameter("tourId");
            int tourId;
            if (tourIdStr != null && !tourIdStr.isEmpty()) {
                tourId = Integer.parseInt(tourIdStr);
            } else {
                // Xử lý khi tourId không tồn tại hoặc là chuỗi rỗng
                // Ví dụ: Hiển thị trang lỗi hoặc chuyển hướng sang trang khác
                response.sendRedirect("error.jsp");
                return;
            }
            // Lấy locationId từ request parameter
            String locationIdStr = request.getParameter("locationId");
            int locationId;
            if (locationIdStr != null && !locationIdStr.isEmpty()) {
                locationId = Integer.parseInt(locationIdStr);
            } else {
                // Xử lý khi locationId không tồn tại hoặc là chuỗi rỗng
                // Ví dụ: Hiển thị trang lỗi hoặc chuyển hướng sang trang khác
                response.sendRedirect("error.jsp");
                return;
            }

            // Gọi phương thức getRestaurantList của RestaurantDAO để lấy danh sách nhà hàng
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());
            List<Restaurant> restaurantList = restaurantDAO.getRestaurantList(locationId);

            // Đặt danh sách nhà hàng làm thuộc tính yêu cầu
            request.setAttribute("tourId", tourId);
            request.setAttribute("restaurantList", restaurantList);
            request.setAttribute("locationId", locationId);

            // Chuyển hướng đến trang JSP
            request.getRequestDispatcher("/Restaurant.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Xử lý ngoại lệ SQLException
            ex.printStackTrace();
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
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        // Nhận danh sách khách sạn đã chọn từ request
        String[] selectedRestaurants = request.getParameterValues("selectedRestaurants");

        // Tạo một đối tượng DAO để thao tác với cơ sở dữ liệu
        TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());

        // Insert into TourTransportations
        for (String restaurantId : selectedRestaurants) {
            RestaurantTour restaurantTour = new RestaurantTour(Integer.parseInt(restaurantId), tourId);
            tourDAO.addRestaurantTour(restaurantTour);
        }

        // Redirect hoặc forward tới trang tiếp theo sau khi xử lý
        response.sendRedirect("index.jsp");
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

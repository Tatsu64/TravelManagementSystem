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
import model.database.DatabaseConnector;
import model.entity.Location;
import model.entity.Restaurant;

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
        try {
            // Lấy thông tin từ request
            String locationIdStr = request.getParameter("locationId");
            int locationId = Integer.parseInt(locationIdStr);

            String restaurantName = request.getParameter("restaurantName");
            String reservationDateStr = request.getParameter("reservationDate"); // Chuyển đổi từ String thành Date
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String imageUrl = request.getParameter("imageUrl");
            String address = request.getParameter("address");

            Date reservationDate = parseDate(reservationDateStr);

            // Tạo đối tượng Restaurant
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(restaurantName);

            // Tạo đối tượng Location chỉ với locationId để đặt vào Restaurant
            Location location = new Location();
            location.setLocationId(locationId);
            restaurant.setLocation(location);

            restaurant.setReservationDate(reservationDate);
            restaurant.setPrice(price);
            restaurant.setImageUrl(imageUrl);
            restaurant.setAddress(address);

            // Gọi phương thức createRestaurant của RestaurantDAO để thêm nhà hàng vào cơ sở dữ liệu
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());
            boolean isSuccess = restaurantDAO.createRestaurant(restaurant);

            if (isSuccess) {
                // Nếu thêm thành công, chuyển hướng đến trang hiển thị danh sách nhà hàng
                response.sendRedirect("RestaurantServlet?locationId=" + locationId);
            } else {
                // Nếu không thành công, có thể xử lý hiển thị thông báo lỗi hoặc chuyển hướng đến trang khác
                response.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException ex) {
            // Xử lý ngoại lệ
            ex.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(RestaurantServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Helper method to parse a string into a Date
    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disallow lenient parsing
        return sdf.parse(dateStr);
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

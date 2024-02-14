/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.HotelDAO;
import model.database.DatabaseConnector;
import model.entity.Hotel;
import model.entity.Location;

/**
 *
 * @author ADMIN
 */
public class HotelServlet extends HttpServlet {

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
            out.println("<title>Servlet HotelServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelServlet at " + request.getContextPath() + "</h1>");
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
        // Lấy location_id từ request
        String locationIdStr = request.getParameter("locationId");
        int locationId;
        try {
            locationId = Integer.parseInt(locationIdStr);
        } catch (NumberFormatException e) {
            // Xử lý khi locationId không hợp lệ
            response.sendRedirect("error.jsp");
            return;
        }

        // Khởi tạo HotelDAO và gọi phương thức getHotelList
        HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
        try {
            List<Hotel> hotelList = hotelDAO.getHotelList(locationId);
            // Chuyển danh sách khách sạn sang JSP để hiển thị
            request.setAttribute("locationId", locationId);
            request.setAttribute("hotelList", hotelList);
            request.getRequestDispatcher("Hotel.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Xử lý khi có lỗi SQL
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
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        // Trích xuất thông tin từ yêu cầu HTTP
        String hotelName = request.getParameter("hotelName");
        String address = request.getParameter("address");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        int locationId = Integer.parseInt(request.getParameter("locationId"));

        // Tạo đối tượng Hotel
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelName);
        hotel.setAddress(address);
        hotel.setPrice(price);
        hotel.setImageUrl(imageUrl);

        // Tạo đối tượng Location và đặt locationId
        Location location = new Location();
        location.setLocationId(locationId);
        hotel.setLocation(location);

        // Thực hiện thêm khách sạn vào cơ sở dữ liệu
        HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
        boolean success = hotelDAO.createHotel(hotel);
        
        if (success) {
            // Thêm thành công, chuyển hướng đến trang thành công
            request.setAttribute("locationId", locationId);
            response.sendRedirect("HotelServlet?locationId=" + locationId);
        } else {
            // Thêm không thành công, chuyển hướng đến trang lỗi
            response.sendRedirect("error.jsp");
        }
    } catch (SQLException ex) {
        // Xử lý ngoại lệ SQLException
        ex.printStackTrace();
        response.sendRedirect("error.jsp");
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

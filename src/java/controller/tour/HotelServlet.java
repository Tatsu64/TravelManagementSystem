/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.HotelDAO;
import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.Hotel;
import model.entity.HotelTour;


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
        String tourIdStr = request.getParameter("tourId");
         int tourId;
            if (tourIdStr != null && !tourIdStr.isEmpty()) {
                tourId = Integer.parseInt(tourIdStr);
            } else {
                // Xử lý khi tourId không tồn tại hoặc là chuỗi rỗng
                // Ví dụ: Hiển thị trang lỗi hoặc chuyển hướng sang trang khác
                response.sendRedirect("404.jsp");
                return;
            }
        // Lấy location_id từ request
        String locationIdStr = request.getParameter("locationId");
        int locationId;
        try {
            locationId = Integer.parseInt(locationIdStr);
        } catch (NumberFormatException e) {
            // Xử lý khi locationId không hợp lệ
            response.sendRedirect("404.jsp");
            return;
        }

        // Khởi tạo HotelDAO và gọi phương thức getHotelList
        HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
        try {
            List<Hotel> hotelList = hotelDAO.getHotelList(locationId);
            // Chuyển danh sách khách sạn sang JSP để hiển thị
            request.setAttribute("tourId", tourId);
            request.setAttribute("locationId", locationId);
            request.setAttribute("hotelList", hotelList);
            request.getRequestDispatcher("Hotel.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Xử lý khi có lỗi SQL
            ex.printStackTrace();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Nhận locationId từ request
    int locationId = Integer.parseInt(request.getParameter("locationId"));
    int tourId = Integer.parseInt(request.getParameter("tourId"));
    // Nhận danh sách khách sạn đã chọn từ request
    String[] selectedHotels = request.getParameterValues("selectedHotels");

    // Tạo một đối tượng DAO để thao tác với cơ sở dữ liệu
    TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());

    // Kiểm tra nếu danh sách khách sạn đã chọn rỗng
    if (selectedHotels == null || selectedHotels.length == 0) {
        // Redirect trực tiếp tới trang RestaurantServlet
        response.sendRedirect("RestaurantServlet?tourId=" + tourId + "&locationId=" + locationId);
        return; // Kết thúc phương thức doPost
    }

    // Insert into TourTransportations
    for (String hotelId : selectedHotels) {
        HotelTour hotelTour = new HotelTour(Integer.parseInt(hotelId), tourId);
        tourDAO.addHotelTour(hotelTour);
    }

    // Redirect hoặc forward tới trang tiếp theo sau khi xử lý
    response.sendRedirect("RestaurantServlet?tourId=" + tourId + "&locationId=" + locationId);
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

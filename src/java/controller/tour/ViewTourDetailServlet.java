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
import model.dao.ActivityScheduleDAO;
import model.dao.EmployeeDAO;
import model.dao.HotelDAO;
import model.dao.LocationDAO;
import model.dao.RestaurantDAO;
import model.dao.TourDAO;
import model.dao.TourDatesDAO;
import model.dao.TransportationDAO;
import model.database.DatabaseConnector;
import model.entity.ActivitySchedule;
import model.entity.Employee;
import model.entity.Hotel;
import model.entity.Location;
import model.entity.Restaurant;
import model.entity.Tour;
import model.entity.TourDates;
import model.entity.Transportation;

/**
 *
 * @author ADMIN
 */
public class ViewTourDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewTourDetailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewTourDetailServlet at " + request.getContextPath() + "</h1>");
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
        try {
            int tourId = Integer.parseInt(request.getParameter("tourId"));
            TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
            Tour tour = tourDAO.getTourById(tourId); // Lấy thông tin chi tiết của tour từ cơ sở dữ liệu
            List<Transportation> transportations = tourDAO.getTransportationsForTour(tourId);
            ActivityScheduleDAO activityScheduleDAO = new ActivityScheduleDAO(DatabaseConnector.getConnection());
            List<ActivitySchedule> activityScheduleList = activityScheduleDAO.getActivityScheduleList(tourId);
            TourDatesDAO tourDatesDAO = new TourDatesDAO(DatabaseConnector.getConnection());
            List<TourDates> tourDatesList = tourDatesDAO.getTourDatesList(tourId);

        
            LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
            
            // Lấy danh sách địa điểm dựa trên tourId
            List<Location> locations = locationDAO.getLocationByTourId(tourId);

            HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
             // Lấy danh sách khách sạn theo tourId
            List<Hotel> hotelList = hotelDAO.getHotelByTourId(tourId);
            
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());
            // Lấy danh sách nhà hàng dựa trên tourId
            List<Restaurant> restaurantList = restaurantDAO.getRestaurantByTourId(tourId);

            // Kiểm tra xem tour có tồn tại hay không
            if (tour != null) {
                // Truyền thông tin tour và danh sách vận chuyển tới trang JSP để hiển thị
                // Set the activityScheduleList as a request attribute
                // Chuyển danh sách khách sạn sang JSP để hiển thị
                request.setAttribute("tourDatesList", tourDatesList);
                request.setAttribute("restaurantList", restaurantList);
                request.setAttribute("hotelList", hotelList);
                request.setAttribute("locations", locations);
                request.setAttribute("activityScheduleList", activityScheduleList);
                request.setAttribute("tour", tour);
                request.setAttribute("transportations", transportations);
                request.getRequestDispatcher("/ViewTourDetail.jsp").forward(request, response);
            } else {
                // Nếu tour không tồn tại, có thể chuyển hướng người dùng đến một trang thông báo lỗi
                response.sendRedirect(request.getContextPath() + "/404.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewTourDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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

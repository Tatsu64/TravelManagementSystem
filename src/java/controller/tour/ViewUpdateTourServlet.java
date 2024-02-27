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
public class ViewUpdateTourServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewUpdateTourServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewUpdateTourServlet at " + request.getContextPath() + "</h1>");
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
            int tourId = Integer.parseInt(request.getParameter("tourId"));
            TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
            Tour tour = tourDAO.getTourById(tourId);
            
            EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnector.getConnection());
            List<Employee> employeeList = employeeDAO.getEmployeeList();
            
            TransportationDAO transportationDAO = new TransportationDAO(DatabaseConnector.getConnection());
            List<Transportation> transportationList = transportationDAO.getTransportationList();
            List<Transportation> selectedTransportations = tourDAO.getTransportationsForTour(tourId);
            
            ActivityScheduleDAO activityScheduleDAO = new ActivityScheduleDAO(DatabaseConnector.getConnection());
            List<ActivitySchedule> activityScheduleList = activityScheduleDAO.getActivityScheduleList(tourId);
            
            TourDatesDAO tourDatesDAO = new TourDatesDAO(DatabaseConnector.getConnection());
            List<TourDates> tourDatesList = tourDatesDAO.getTourDatesList(tourId);
            
            LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
            List<Location> locations = locationDAO.getLocationByTourId(tourId);

            int locationId = -1; // Initialize with a default value
            if (!locations.isEmpty()) {
                Location location = locations.get(0); // Get the first (and presumably only) location
                locationId = location.getLocationId();
            }
            HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
            List<Hotel> hotelList = hotelDAO.getHotelList(locationId);
            List<Hotel> selectedHotels = hotelDAO.getHotelByTourId(tourId);
            
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());
            List<Restaurant> restaurantList = restaurantDAO.getRestaurantList(locationId);
            List<Restaurant> selectedRestaurants = restaurantDAO.getRestaurantByTourId(tourId);
            
            request.setAttribute("restaurantList", restaurantList);
            request.setAttribute("selectedRestaurants", selectedRestaurants);
            request.setAttribute("hotelList", hotelList);
            request.setAttribute("selectedHotels", selectedHotels);
            request.setAttribute("activityScheduleList", activityScheduleList);
            request.setAttribute("tourDatesList", tourDatesList);
            request.setAttribute("selectedTransportations", selectedTransportations);
            request.setAttribute("transportationList", transportationList);
            request.setAttribute("employeeList", employeeList);
            request.setAttribute("tour", tour);
            request.getRequestDispatcher("/UpdateTour.jsp").forward(request, response);
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

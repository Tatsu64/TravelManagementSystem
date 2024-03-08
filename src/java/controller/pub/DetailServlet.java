/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.pub;

import static helper.Helper.convertToLocalDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ActivityScheduleDAO;
import model.dao.BookingDAO;
import model.dao.HotelDAO;
import model.dao.RestaurantDAO;
import model.dao.ReviewDAO;
import model.dao.TourDAO;
import model.dao.TransportationDAO;
import model.database.DatabaseConnector;
import model.entity.Booking;
import model.entity.Review;
import model.entity.Tour;

/**
 *
 * @author TATSU
 */
public class DetailServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath() + "</h1>");
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
            //processRequest(request, response);
            Integer userIdObj = (Integer) request.getSession().getAttribute("userId");
            int userId = userIdObj != null ? userIdObj.intValue() : 0; 
            int tourid = Integer.parseInt(request.getParameter("id"));
            TourDAO tdao = new TourDAO(DatabaseConnector.getConnection());
            RestaurantDAO rdao = new RestaurantDAO(DatabaseConnector.getConnection());
            ActivityScheduleDAO ad = new ActivityScheduleDAO(DatabaseConnector.getConnection());
            HotelDAO hd = new HotelDAO(DatabaseConnector.getConnection());
            ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());
            BookingDAO bookingDAO = new BookingDAO(DatabaseConnector.getConnection()); 
            
            Tour tour = tdao.getTourById(tourid);
            if (tour != null) {
            int days = (int) ChronoUnit.DAYS.between(convertToLocalDate(tour.getStartDate()), convertToLocalDate(tour.getEndDate()));
            request.setAttribute("tour", tour);
            request.setAttribute("days", days);
            request.setAttribute("restaurants", rdao.getRestaurantByTourId(tourid));
            request.setAttribute("transports", TransportationDAO.getTransportationByTourId(tourid));
            request.setAttribute("hotels", hd.getHotelByTourId(tourid));
            request.setAttribute("activities", ad.getActivityScheduleList(tourid));
            
            
            // Retrieve reviews based on the tour ID
            List<Review> reviews = reviewDAO.getAllReviewsByTourId(tourid);

            // Store user names, review content, and ratings as attributes in the request
            request.setAttribute("reviews", reviews);
            
            Booking latestBooking = bookingDAO.getLatestBookingByTourId(userId, tourid);
                if (latestBooking != null) {
                    List<Integer> bookingIds = new ArrayList<>();
                    bookingIds.add(latestBooking.getBookingId());
                    request.setAttribute("bookingIds", bookingIds);
                }

                request.getRequestDispatcher("tourDetail.jsp").forward(request, response);
            } else { 
                response.sendRedirect("404.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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

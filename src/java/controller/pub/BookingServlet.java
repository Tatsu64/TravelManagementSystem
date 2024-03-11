/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.pub;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.BillDAO;
import model.dao.BookingDAO;
import model.dao.TourDAO;
import model.dao.TourDatesDAO;
import model.database.DatabaseConnector;
import model.entity.Bill;
import model.entity.Booking;
import model.entity.HomeTour;
import model.entity.Tour;
import model.entity.User;

/**
 *
 * @author PC
 */
public class BookingServlet extends HttpServlet {

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
            out.println("<title>Servlet BookingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            int tourDateId = Integer.parseInt(request.getParameter("id"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int people = Integer.parseInt(request.getParameter("people"));
            int current = Integer.parseInt(request.getParameter("current"));
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String startDateStr = request.getParameter("dateStart");

            Date bookingDate = new Date();
            int newCapacity = current + people;
            java.sql.Date startDate = parseDate(startDateStr);
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(people));

            HomeTour homeTour = TourDAO.getHomeTourByTourDateId(tourDateId);
            int tourId = homeTour.getTourId();
            
            Tour tour = new Tour();
            tour.setTourId(tourId);

            User user = new User();
            user.setUserId(userId);
            user.setName(name);
            user.setEmail(email);

            Booking booking = new Booking();
            booking.setTour(tour);
            booking.setUser(user);
            booking.setNumberOfPeople(people);
            booking.setTotalPrice(totalPrice);
            booking.setBookingDate(bookingDate);

            try {
                // Khởi tạo BookingDAO
                BookingDAO bookingDAO = new BookingDAO(DatabaseConnector.getConnection());
                TourDatesDAO tourDateDAO = new TourDatesDAO(DatabaseConnector.getConnection());
                tourDateDAO.updateCurrentCapacityByTourId(tourId, startDate, newCapacity);

                // Tạo đơn đặt tour và lấy ID của nó
                int bookingId = bookingDAO.createBookingAndGetId(booking);
                Booking bk = new Booking();
                bk.setBookingId(bookingId);

                Bill bill = new Bill();
                bill.setBooking(bk);
                bill.setPaymentDate(bookingDate);
                bill.setPaymentMethod("Card");
                BillDAO billDAO = new BillDAO(DatabaseConnector.getConnection());

                // Tạo hóa đơn trong cơ sở dữ liệu
                billDAO.createBill(bill);
                
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                request.setAttribute("totalPrice", totalPrice);
                // Chuyển hướng người dùng đến trang thanh toán
                request.getRequestDispatcher("pay.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Helper method to parse a string into a Date
    private java.sql.Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disallow lenient parsing
        Date parsedDate = sdf.parse(dateStr);
        return new java.sql.Date(parsedDate.getTime());
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

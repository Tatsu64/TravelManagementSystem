/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.Employee;
import model.entity.Tour;

/**
 *
 * @author ADMIN
 */
public class CreateTourServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateTourServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateTourServlet at " + request.getContextPath() + "</h1>");
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
          // Retrieve data from the form
        String tourName = request.getParameter("tourName");
        String description = request.getParameter("description");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String tourPriceStr = request.getParameter("tourPrice");
        String imageUrl = request.getParameter("imageUrl");
        String employeeIdStr = request.getParameter("employeeId");
        String startLocation = request.getParameter("startLocation");
        String maxCapacityStr = request.getParameter("maxCapacity");
        String currentCapacityStr = request.getParameter("currentCapacity");

        // Convert string values to appropriate types
        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);
        BigDecimal tourPrice = new BigDecimal(tourPriceStr);
        int employeeId = Integer.parseInt(employeeIdStr);
        int maxCapacity = Integer.parseInt(maxCapacityStr);
        int currentCapacity = Integer.parseInt(currentCapacityStr);

        // Create a Tour object
        Tour newTour = new Tour();
        newTour.setTourName(tourName);
        newTour.setDescription(description);
        newTour.setStartDate(startDate);
        newTour.setEndDate(endDate);
        newTour.setTourPrice(tourPrice);
        newTour.setImageUrl(imageUrl);

        // In a real application, you would retrieve the employee from the database based on employeeId
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        newTour.setEmployee(employee);

        newTour.setStartLocation(startLocation);
        newTour.setMaxCapacity(maxCapacity);
        newTour.setCurrentCapacity(currentCapacity);

        // Save the Tour to the database
        saveTourToDatabase(newTour);

        // Redirect to a success page or display a success message
        response.sendRedirect("index.jsp");
    }
    // Helper method to parse a string into a Date
    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return null;
        }
    }

    // Helper method to save the Tour to the database
    private void saveTourToDatabase(Tour tour) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            TourDAO tourDAO = new TourDAO(connection);
            tourDAO.addTour(tour);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
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

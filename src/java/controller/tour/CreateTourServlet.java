/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Tour;
import java.io.*;
import javax.servlet.http.*;
import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.TourTransportation;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.*;
import model.entity.Employee;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve other form data
            String tourName = request.getParameter("tourName");
            String description = request.getParameter("description");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String tourPriceStr = request.getParameter("tourPrice");
            String employeeIdStr = request.getParameter("employeeId");
            String startLocation = request.getParameter("startLocation");
            String maxCapacityStr = request.getParameter("maxCapacity");
            String currentCapacityStr = request.getParameter("currentCapacity");
            String[] selectedTransportations = request.getParameterValues("selectedTransportations[]");
            String locationIdStr = request.getParameter("locationId");

            Date startDate = parseDate(startDateStr);
            Date endDate = parseDate(endDateStr);
            BigDecimal tourPrice = new BigDecimal(tourPriceStr);

            int employeeId;
            int maxCapacity;
            int currentCapacity;
            int locationId;

            try {
                locationId = Integer.parseInt(locationIdStr);
                employeeId = Integer.parseInt(employeeIdStr);
                maxCapacity = Integer.parseInt(maxCapacityStr);
                currentCapacity = Integer.parseInt(currentCapacityStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
                return;
            }

            // Handling image upload
            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);
            String uploadDirectory = "/images";
            String filePath = getServletContext().getRealPath(uploadDirectory + File.separator + fileName);

            // Save the file to the server
            try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            // Create a Tour object
            Tour newTour = new Tour();
            newTour.setTourName(tourName);
            newTour.setDescription(description);
            newTour.setStartDate(startDate);
            newTour.setEndDate(endDate);
            newTour.setTourPrice(tourPrice);
            newTour.setImageUrl(fileName); // Save the file path to database instead of image URL

            // In a real application, you would retrieve the employee from the database based on employeeId
            Employee employee = new Employee();
            employee.setEmployeeId(employeeId);
            newTour.setEmployee(employee);

            newTour.setStartLocation(startLocation);
            newTour.setMaxCapacity(maxCapacity);
            newTour.setCurrentCapacity(currentCapacity);

            // Assuming you have the tourId available from the form submission
            int generatedTourId = TourDAO.insertTourAndGetId(DatabaseConnector.getConnection(), newTour);
            TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
            tourDAO.addTourLocation(generatedTourId, locationId);

            // Insert into TourTransportations
            for (String transportationId : selectedTransportations) {
                TourTransportation tourTransportation = new TourTransportation(generatedTourId, Integer.parseInt(transportationId));
                tourDAO.addTourTransportation(tourTransportation);
            }

            // Redirect to a success page or display a success message
            response.sendRedirect("ActivityScheduleServlet?tourId=" + generatedTourId + "&locationId=" + locationId);

        } catch (ParseException ex) {
            ex.printStackTrace(); // Handle or log the exception appropriately
            response.sendRedirect("error.jsp"); // Redirect to an error page
        } catch (SQLException ex) {
            Logger.getLogger(CreateTourServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Helper method to parse a string into a Date
    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disallow lenient parsing
        return sdf.parse(dateStr);
    }

    // Helper method to get the file name from the Part
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
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

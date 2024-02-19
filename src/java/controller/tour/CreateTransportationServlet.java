/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.TransportationDAO;
import model.database.DatabaseConnector;
import model.entity.Transportation;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class CreateTransportationServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateTransportationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateTransportationServlet at " + request.getContextPath() + "</h1>");
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
            // Retrieve data from the form
            String transportationName = request.getParameter("transportationName");
            String departureDateString = request.getParameter("departureDate");
            String returnDateString = request.getParameter("returnDate");
            String priceString = request.getParameter("price");
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

            // Convert date strings to Date objects
            Date departureDate = parseDate(departureDateString);
            Date returnDate = parseDate(returnDateString);
            // Convert price string to BigDecimal
            BigDecimal price = new BigDecimal(priceString);

            // Create a new Transportation object
            Transportation newTransportation = new Transportation();
            newTransportation.setTransportationName(transportationName);
            newTransportation.setDepartureDate(departureDate);
            newTransportation.setReturnDate(returnDate);
            newTransportation.setPrice(price);
            newTransportation.setImageUrl(fileName);

            // Establish a database connection
            Connection connection = DatabaseConnector.getConnection();

            // Create a TransportationDAO instance with the connection
            TransportationDAO transportationDAO = new TransportationDAO(connection);

            // Save the new transportation entity to the database
            transportationDAO.createTransportation(newTransportation);

            // TODO: Redirect to a confirmation page or another destination
            // For example, redirect to the transportation list page
            response.sendRedirect("EmployeeListServlet");
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    
      private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return null;
        }
    }
    // Helper method to get the file name from the Part

    private String getFileName(Part part) {
        if (part != null) {
            String contentDisp = part.getHeader("content-disposition");
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
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

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.Employee;
import model.entity.Tour;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class EditDeleteTourServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteTourServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteTourServlet at " + request.getContextPath() + "</h1>");
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
        // Lấy tourId từ request parameter
        int tourId = Integer.parseInt(request.getParameter("tourId"));

        // Tạo một instance của TourDAO
        TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());

        // Gọi phương thức deleteTour để xóa tour từ cơ sở dữ liệu
        tourDAO.deleteTour(tourId);

        // Redirect hoặc forward đến một trang khác sau khi xóa tour thành công
        response.sendRedirect("ApprovalTourServlet?");
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
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        String tourName = request.getParameter("tourName");
        String description = request.getParameter("description");
        String tourPriceStr = request.getParameter("tourPrice");
        String employeeIdStr = request.getParameter("employeeId");
        String startLocation = request.getParameter("startLocation");
        String maxCapacityStr = request.getParameter("maxCapacity");
        String[] selectedTransportations = request.getParameterValues("selectedTransportations[]");
        String[] selectedHotels = request.getParameterValues("selectedHotels[]");
        String[] selectedRestaurants = request.getParameterValues("selectedRestaurants[]");

        BigDecimal tourPrice = new BigDecimal(tourPriceStr);
        int employeeId;
        int maxCapacity;
        try {
            employeeId = Integer.parseInt(employeeIdStr);
            maxCapacity = Integer.parseInt(maxCapacityStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("404.jsp");
            return;
        }

        // Get the file information from the request
        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);

        // Retrieve the existing tour from the database
        TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
        Tour tourToUpdate = tourDAO.getTourById(tourId);

        // Set the updated information for the tour
        tourToUpdate.setTourName(tourName);
        tourToUpdate.setDescription(description);
        tourToUpdate.setTourPrice(tourPrice);

        // Retrieve the employee from the database based on the employeeId
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        tourToUpdate.setEmployee(employee);

        tourToUpdate.setStartLocation(startLocation);
        tourToUpdate.setMaxCapacity(maxCapacity);

        // Check if a new file is selected
        if (!fileName.isEmpty()) {
            String uploadDirectory = "/images";
            String filePath = getServletContext().getRealPath(uploadDirectory + File.separator + fileName);

            // Save the new file to the server
            try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            // Update the image URL in the tour object
            tourToUpdate.setImageUrl(fileName);
        }

        // Convert the selected transportation IDs from String[] to List<Integer>
        List<Integer> transportationIds = new ArrayList<>();
        if (selectedTransportations != null) {
            for (String transportationIdStr : selectedTransportations) {
                transportationIds.add(Integer.valueOf(transportationIdStr));
            }
        }
        // Convert String[] to List<Integer>
        List<Integer> hotelIds = new ArrayList<>();
        if (selectedHotels != null) {
            for (String hotelId : selectedHotels) {
                hotelIds.add(Integer.valueOf(hotelId));
            }
        }

        List<Integer> restaurantIds = new ArrayList<>();
        if (selectedRestaurants != null) {
            for (String restaurantId : selectedRestaurants) {
                restaurantIds.add(Integer.valueOf(restaurantId));
            }
        }

        // Update the tour and its transportation associations in the database
        tourDAO.updateRestaurantTour(tourId, restaurantIds);
        tourDAO.updateTour(tourToUpdate);
        tourDAO.updateTourTransportation(tourId, transportationIds);
        tourDAO.updateHotelTour(tourId, hotelIds);

        // Redirect the user to the page showing the updated tour details
        response.sendRedirect("ViewTourDetailServlet?tourId=" + tourId);
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

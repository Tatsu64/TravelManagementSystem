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
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.HotelDAO;
import model.database.DatabaseConnector;
import model.entity.Hotel;
import model.entity.Location;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class ManageHotelsServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageHotelsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageHotelsServlet at " + request.getContextPath() + "</h1>");
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
        // Khởi tạo HotelDAO và gọi phương thức getHotelList
        HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
        List<Hotel> hotelList = hotelDAO.getAllHotels();
        // Chuyển danh sách khách sạn sang JSP để hiển thị
        request.setAttribute("hotelList", hotelList);
        request.getRequestDispatcher("ManageHotel.jsp").forward(request, response);
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
            // Lấy thông tin từ form
            String hotelName = request.getParameter("hotelName");
            String locationIdStr = request.getParameter("locationId");
            String address = request.getParameter("address");
            
             int locationId;

            try {
                locationId = Integer.parseInt(locationIdStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("404.jsp");
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
            
            Location location = new Location();
            location.setLocationId(locationId);
            // Tạo đối tượng Hotel
            Hotel hotel = new Hotel();
            hotel.setHotelName(hotelName);
            hotel.setLocation(location);
            hotel.setAddress(address);
            hotel.setImageUrl(fileName);
            
            // Thêm vào cơ sở dữ liệu
             HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
            hotelDAO.addHotelToDatabase(hotel);
            
            // Chuyển hướng đến trang danh sách khách sạn sau khi thêm thành công
            response.sendRedirect("ManageHotelsServlet?");
        } catch (IOException | NumberFormatException | SQLException | ServletException ex) {
            // Xử lý ngoại lệ
            ex.printStackTrace();
            response.sendRedirect("404.jsp");
        }
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

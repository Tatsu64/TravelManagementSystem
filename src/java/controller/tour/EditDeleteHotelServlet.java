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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.HotelDAO;
import model.dao.LocationDAO;
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
public class EditDeleteHotelServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteHotelServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteHotelServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "update":
                    updateHotel(request, response);
                    break;
                case "delete":
                    deleteHotel(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void updateHotel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));
            
            // Tạo đối tượng HotelDAO
            HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
            
            // Gọi phương thức getHotelById từ HotelDAO để lấy thông tin của khách sạn
            Hotel hotel = hotelDAO.getHotelById(hotelId);
            
            // Kiểm tra nếu khách sạn không tồn tại, chuyển hướng đến trang 404
            if (hotel == null) {
                response.sendRedirect("404.jsp");
                return;
            }
            LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
            List<Location> locations = locationDAO.getLocationList();
            request.setAttribute("locations", locations);
            
            // Lưu thông tin của khách sạn vào request attribute để hiển thị trên trang jsp
            request.setAttribute("hotel", hotel);
            
            // Chuyển hướng đến trang cập nhật thông tin khách sạn, nơi có thể hiển thị thông tin và cho phép người dùng cập nhật
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateHotel.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteHotel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy hotelId từ request parameter
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));

            // Tạo một đối tượng HotelDAO để thực hiện thao tác xóa
            HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());

            // Gọi phương thức xóa khách sạn từ HotelDAO
            hotelDAO.deleteHotel(hotelId);

            // Chuyển hướng đến trang danh sách khách sạn sau khi xóa thành công
            response.sendRedirect("ManageHotelsServlet");
        } catch (NumberFormatException | SQLException ex) {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy thông tin từ form
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));
            String hotelName = request.getParameter("hotelName");
            int locationId = Integer.parseInt(request.getParameter("locationId"));
            String address = request.getParameter("address");
            // Get the file information from the request
            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);

            HotelDAO hotelDAO = new HotelDAO(DatabaseConnector.getConnection());
            Hotel hotel = hotelDAO.getHotelById(hotelId);
            // Tạo đối tượng Hotel
            hotel.setHotelId(hotelId);
            hotel.setHotelName(hotelName);
            Location location = new Location();
            location.setLocationId(locationId);
            hotel.setLocation(location);
            hotel.setAddress(address);
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
                hotel.setImageUrl(fileName);
            }

            // Thực hiện cập nhật khách sạn trong cơ sở dữ liệu
            hotelDAO.updateHotel(hotel);

            // Chuyển hướng đến trang danh sách khách sạn sau khi cập nhật thành công
            response.sendRedirect("ManageHotelsServlet");
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            response.sendRedirect("404.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
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

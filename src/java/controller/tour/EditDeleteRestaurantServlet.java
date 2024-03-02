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
import model.dao.LocationDAO;
import model.dao.RestaurantDAO;
import model.database.DatabaseConnector;
import model.entity.Location;
import model.entity.Restaurant;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class EditDeleteRestaurantServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteRestaurantServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteRestaurantServlet at " + request.getContextPath() + "</h1>");
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
                case "delete":
                    deleteRestaurant(request, response);
                    break;
                case "update":
                    updateRestaurant(request, response);
                    break;
                default:
                    response.sendRedirect("404.jsp"); // Trường hợp không xác định được hành động
                    break;
            }
        } else {
            response.sendRedirect("404.jsp"); // Trường hợp không có hành động được chỉ định
        }
    }

    private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());
            restaurantDAO.deleteRestaurant(restaurantId);
            response.sendRedirect("ManageRestaurantServlet");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("404.jsp");
        }
    }

    private void updateRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy restaurantId từ request parameter
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

            // Tạo đối tượng RestaurantDAO
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());

            // Gọi phương thức getRestaurantById từ RestaurantDAO để lấy thông tin của nhà hàng
            Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);

            // Kiểm tra nếu nhà hàng không tồn tại, chuyển hướng đến trang 404
            if (restaurant == null) {
                response.sendRedirect("404.jsp");
                return;
            }

            // Lấy danh sách location từ cơ sở dữ liệu
            LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
            List<Location> locations = locationDAO.getLocationList();

            // Lưu danh sách location vào request attribute để hiển thị trên trang jsp
            request.setAttribute("locations", locations);

            // Lưu thông tin của nhà hàng vào request attribute để hiển thị trên trang jsp
            request.setAttribute("restaurant", restaurant);

            // Chuyển hướng đến trang cập nhật thông tin nhà hàng, nơi có thể hiển thị thông tin và cho phép người dùng cập nhật
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateRestaurant.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteRestaurantServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            // Lấy thông tin từ biểu mẫu
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            String restaurantName = request.getParameter("restaurantName");
            int locationId = Integer.parseInt(request.getParameter("locationId"));
            String address = request.getParameter("address");
            // Get the file information from the request
            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);

            // Tạo đối tượng Restaurant từ thông tin đã nhận được
            RestaurantDAO restaurantDAO = new RestaurantDAO(DatabaseConnector.getConnection());
            // Gọi phương thức getRestaurantById từ RestaurantDAO để lấy thông tin của nhà hàng
            Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);
            restaurant.setRestaurantId(restaurantId);
            restaurant.setRestaurantName(restaurantName);
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
                restaurant.setImageUrl(fileName);
            }

            // Lấy thông tin địa điểm từ ID và tạo đối tượng Location
            LocationDAO locationDAO = new LocationDAO(DatabaseConnector.getConnection());
            Location location = locationDAO.getLocationById(locationId);
            restaurant.setLocation(location);

            restaurant.setAddress(address);
            // Gọi phương thức updateRestaurant từ RestaurantDAO để cập nhật thông tin
            restaurantDAO.updateRestaurant(restaurant);

            // Chuyển hướng đến trang danh sách nhà hàng sau khi cập nhật thành công
            response.sendRedirect("ManageRestaurantServlet");
        } catch (IOException | NumberFormatException | SQLException | ServletException ex) {
            // Xử lý ngoại lệ
            ex.printStackTrace();
            response.sendRedirect("404.jsp");
        }
    }

// Phương thức helper để lấy tên file từ Part
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

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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.TourDAO;
import model.dao.TransportationDAO;
import model.database.DatabaseConnector;
import model.entity.Employee;
import model.entity.Tour;
import model.entity.Transportation;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class EditDeleteTransportationServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteTransportationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteTransportationServlet at " + request.getContextPath() + "</h1>");
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

        if (action != null && !action.isEmpty()) {
            if (action.equals("update")) {
                // Xử lý update
                updateTransportation(request, response);
            } else if (action.equals("delete")) {
                // Xử lý delete
                deleteTransportation(request, response);
            } else {
                // Xử lý action không hợp lệ
                response.sendRedirect("404.jsp");
            }
        } else {
            // Action không được cung cấp
            response.sendRedirect("404.jsp");
        }
    }

    private void updateTransportation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy transportationId từ request parameter
            int transportationId = Integer.parseInt(request.getParameter("transportationId"));

            // Tạo đối tượng TransportationDAO và gọi phương thức getTransportationById
            TransportationDAO transportationDAO = new TransportationDAO(DatabaseConnector.getConnection());
            Transportation transportation = transportationDAO.getTransportationById(transportationId);

            // Đặt thông tin vận chuyển vào request attribute
            request.setAttribute("transportation", transportation);

            // Chuyển hướng request và response đến trang jsp để hiển thị thông tin vận chuyển
            request.getRequestDispatcher("/UpdateTransportation.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException ex) {
            // Xử lý ngoại lệ SQL
            ex.printStackTrace(); // hoặc xử lý theo ý của bạn
            response.sendRedirect("404.jsp"); // Chuyển hướng đến trang lỗi
        }

    }

    private void deleteTransportation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy transportationId từ request
        String transportationIdStr = request.getParameter("transportationId");

        // Kiểm tra xem transportationId có tồn tại không
        if (transportationIdStr != null && !transportationIdStr.isEmpty()) {
            try {
                int transportationId = Integer.parseInt(transportationIdStr);

                // Gọi phương thức xóa vận chuyển từ DAO
                TransportationDAO transportationDAO = new TransportationDAO(DatabaseConnector.getConnection());
                transportationDAO.deleteTransportation(transportationId);

                // Chuyển hướng lại đến trang hiển thị danh sách vận chuyển
                response.sendRedirect("TransportationServlet");
            } catch (NumberFormatException | SQLException e) {
                // Xử lý nếu có lỗi xảy ra hoặc không thể chuyển đổi transportationId thành số nguyên
                e.printStackTrace();
                // Chuyển hướng đến trang lỗi hoặc trang chính tùy thuộc vào yêu cầu
                response.sendRedirect("404.jsp");
            }
        } else {
            // Xử lý khi transportationId không tồn tại trong request
            // Thông báo lỗi hoặc chuyển hướng đến trang khác tùy thuộc vào yêu cầu
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
            int transportationId = Integer.parseInt(request.getParameter("transportationId"));
            String transportationName = request.getParameter("transportationName");
            String departureTimeString = request.getParameter("departureTime");
            String returnTimeString = request.getParameter("returnTime");
            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);
            // Convert date strings to Date objects
            Time departureTime = parseTime(departureTimeString);
            Time returnTime = parseTime(returnTimeString);
            
            TransportationDAO transportationDAO = new TransportationDAO(DatabaseConnector.getConnection());
            Transportation newTransportation = transportationDAO.getTransportationById(transportationId);
            
            newTransportation.setTransportationId(transportationId);
            newTransportation.setTransportationName(transportationName);
            newTransportation.setDepartureTime(departureTime);
            newTransportation.setReturnTime(returnTime);
            
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
                newTransportation.setImageUrl(fileName);
            }
            transportationDAO.updateTransportation(newTransportation);
            response.sendRedirect("TransportationServlet");
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTransportationServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    // Helper method to parse a string into a Time with the same date as activityDate
    private Time parseTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null; // Trả về null nếu chuỗi thời gian là null hoặc rỗng
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            java.util.Date date = sdf.parse(timeStr);
            return new Time(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra trong quá trình chuyển đổi
        }
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

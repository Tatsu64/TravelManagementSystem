/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.dao.TourDatesDAO;
import model.database.DatabaseConnector;
import model.entity.TourDates;

/**
 *
 * @author ADMIN
 */
public class EditDeleteTourDatesServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteTourDatesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteTourDatesServlet at " + request.getContextPath() + "</h1>");
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
        // Lấy các tham số từ request
        String action = request.getParameter("action");
        String tourIdStr = request.getParameter("tourId");
        String tourDateIdStr = request.getParameter("tourDateId");

        // Kiểm tra xem action, tourId và scheduleId có tồn tại không
        if (action != null && !action.isEmpty() && tourIdStr != null && !tourIdStr.isEmpty() && tourDateIdStr != null && !tourDateIdStr.isEmpty()) {
            try {
                int tourId = Integer.parseInt(tourIdStr);
                int tourDateId = Integer.parseInt(tourDateIdStr);

                // Tùy thuộc vào hành động, gọi phương thức tương ứng từ DAO
                TourDatesDAO tourDatesDAO = new TourDatesDAO(DatabaseConnector.getConnection());
                if ("update".equals(action)) {
                    // Thực hiện update
                    TourDates tourDates = tourDatesDAO.getTourDatesById(tourDateId);
                    // Chuyển hướng đến trang cập nhật với thông tin cụ thể
                    request.setAttribute("tourDates", tourDates);
                    request.setAttribute("tourId", tourId);
                    request.getRequestDispatcher("UpdateTourDate.jsp").forward(request, response);
                } else if ("delete".equals(action)) {
                    // Thực hiện delete
                    tourDatesDAO.deleteTourDate(tourDateId);
                    // Chuyển hướng lại đến trang hiển thị tour detail hoặc trang chính
                    response.sendRedirect("ViewUpdateTourServlet?tourId=" + tourId);
                } else {
                    // Xử lý trường hợp không xác định hành động
                    response.sendRedirect("404.jsp");
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không thể chuyển đổi tourId hoặc scheduleId thành số nguyên
                e.printStackTrace();
                // Chuyển hướng đến trang lỗi nếu cần
                response.sendRedirect("404.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(EditDeleteTourDatesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Xử lý khi action, tourId hoặc scheduleId không tồn tại
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Lấy thông tin từ request
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        int tourDateId = Integer.parseInt(request.getParameter("tourDateId"));
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        int currentCapacity = Integer.parseInt(request.getParameter("currentCapacity"));
        
        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);
        
        // Tạo đối tượng TourDates từ thông tin nhận được
        TourDates tourDates = new TourDates();
        tourDates.setTourDateId(tourDateId);
        tourDates.setStartDate(startDate);
        tourDates.setEndDate(endDate);
        tourDates.setCurrentCapacity(currentCapacity);
        // Gọi phương thức updateTourDates trong DAO để cập nhật thông tin Tour Date
        TourDatesDAO tourDatesDAO = new TourDatesDAO(DatabaseConnector.getConnection());
        try {
            tourDatesDAO.updateTourDates(tourDates);
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc ghi log lỗi
            // Forward hoặc redirect đến trang lỗi tùy theo yêu cầu
            response.sendRedirect("404.jsp");
            return;
        }
        
        // Nếu cập nhật thành công, chuyển hướng đến trang hiển thị chi tiết Tour Date hoặc trang khác tùy theo yêu cầu
        response.sendRedirect("ViewUpdateTourServlet?tourId=" + tourId);
    } catch (ParseException ex) {
            Logger.getLogger(EditDeleteTourDatesServlet.class.getName()).log(Level.SEVERE, null, ex); 
    }
}
     // Helper method to parse a string into a Date
    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disallow lenient parsing
        return sdf.parse(dateStr);
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

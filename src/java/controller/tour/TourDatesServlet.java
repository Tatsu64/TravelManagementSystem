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
import java.util.List;
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
public class TourDatesServlet extends HttpServlet {

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
            out.println("<title>Servlet TourDatesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TourDatesServlet at " + request.getContextPath() + "</h1>");
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
        try {
            String tourIdStr = request.getParameter("tourId");
            int tourId;
            if (tourIdStr != null && !tourIdStr.isEmpty()) {
                tourId = Integer.parseInt(tourIdStr);
            } else {
                response.sendRedirect("error.jsp");
                return;
            }
            TourDatesDAO tourDatesDAO = new TourDatesDAO(DatabaseConnector.getConnection());
            List<TourDates> tourDatesList = tourDatesDAO.getTourDatesList(tourId);
            
            request.setAttribute("tourDatesList", tourDatesList);
            request.setAttribute("tourId", tourId);
            request.getRequestDispatcher("/TourDates.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TourDatesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        // Lấy các thông tin từ request để tạo mới một TourDates
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String currentCapacityStr = request.getParameter("currentCapacity");
        String tourIdStr = request.getParameter("tourId");
        
        // Kiểm tra xem các tham số có null hoặc rỗng không
        if (startDateStr == null || endDateStr == null || 
            currentCapacityStr == null || tourIdStr == null ||
            startDateStr.isEmpty() || endDateStr.isEmpty() ||
            currentCapacityStr.isEmpty() || tourIdStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        int tourId = Integer.parseInt(tourIdStr);
        
        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);
        
        int currentCapacity = Integer.parseInt(currentCapacityStr);
        
        // Tạo một đối tượng TourDates từ thông tin nhận được
        TourDates tourDates = new TourDates(tourId ,startDate, endDate, currentCapacity);
        
        // Thực hiện thêm mới TourDates vào cơ sở dữ liệu thông qua TourDatesDAO
        TourDatesDAO tourDatesDAO = new TourDatesDAO(DatabaseConnector.getConnection());
        tourDatesDAO .createTourDates(tourDates);
         int update = Integer.parseInt(request.getParameter("update"));
            // Nếu thêm mới thành công, chuyển hướng về trang danh sách TourDates
            if(update!=1){
            response.sendRedirect("TourDatesServlet?tourId=" + tourId);
             } else {
                response.sendRedirect("ViewUpdateTourServlet?tourId=" + tourId);
            }

    } catch (ParseException | NumberFormatException ex) {
        Logger.getLogger(TourDatesServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("error.jsp");
    }   catch (SQLException ex) {
            Logger.getLogger(TourDatesServlet.class.getName()).log(Level.SEVERE, null, ex);
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

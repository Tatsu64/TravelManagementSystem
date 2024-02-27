/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ActivityScheduleDAO;
import model.database.DatabaseConnector;
import model.entity.ActivitySchedule;
import model.entity.Tour;

/**
 *
 * @author ADMIN
 */
public class EditDeleteActivityScheduleServlet extends HttpServlet {

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
            out.println("<title>Servlet EditDeleteActivityScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDeleteActivityScheduleServlet at " + request.getContextPath() + "</h1>");
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
        String scheduleIdStr = request.getParameter("scheduleId");

        // Kiểm tra xem action, tourId và scheduleId có tồn tại không
        if (action != null && !action.isEmpty() && tourIdStr != null && !tourIdStr.isEmpty() && scheduleIdStr != null && !scheduleIdStr.isEmpty()) {
            try {
                int tourId = Integer.parseInt(tourIdStr);
                int scheduleId = Integer.parseInt(scheduleIdStr);

                // Tùy thuộc vào hành động, gọi phương thức tương ứng từ DAO
                ActivityScheduleDAO activityScheduleDAO = new ActivityScheduleDAO(DatabaseConnector.getConnection());
                if ("update".equals(action)) {
                    // Thực hiện update
                    ActivitySchedule activitySchedule = activityScheduleDAO.getActivityScheduleById(scheduleId);
                    // Chuyển hướng đến trang cập nhật với thông tin cụ thể
                    request.setAttribute("activitySchedule", activitySchedule);
                    request.setAttribute("tourId", tourId);
                    request.getRequestDispatcher("UpdateActivitySchedule.jsp").forward(request, response);
                } else if ("delete".equals(action)) {
                    // Thực hiện delete
                    activityScheduleDAO.deleteActivitySchedule(scheduleId);
                    // Chuyển hướng lại đến trang hiển thị tour detail hoặc trang chính
                    response.sendRedirect("ViewUpdateTourServlet?tourId=" + tourId);
                } else {
                    // Xử lý trường hợp không xác định hành động
                    response.sendRedirect("error.jsp");
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không thể chuyển đổi tourId hoặc scheduleId thành số nguyên
                e.printStackTrace();
                // Chuyển hướng đến trang lỗi nếu cần
                response.sendRedirect("error.jsp");
            }
        } else {
            // Xử lý khi action, tourId hoặc scheduleId không tồn tại
            response.sendRedirect("error.jsp");
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
        // Lấy thông tin từ request
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        int dayNumber = Integer.parseInt(request.getParameter("dayNumber"));
        String activityName = request.getParameter("activityName");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String location = request.getParameter("location");
        String description = request.getParameter("activityDescription");

        Time startTime = parseTime(startTimeStr);

        Time endTime = parseTime(endTimeStr);
        // Tạo đối tượng ActivitySchedule
        ActivitySchedule activitySchedule = new ActivitySchedule();
        activitySchedule.setScheduleId(scheduleId);
        activitySchedule.setTour(new Tour(tourId)); // Đặt tourId vào đối tượng Tour
        activitySchedule.setDayNumber(dayNumber);
        activitySchedule.setActivityName(activityName);
        activitySchedule.setStartTime(startTime);
        activitySchedule.setEndTime(endTime);
        activitySchedule.setLocation(location);
        activitySchedule.setDescription(description);

        // Gọi phương thức updateActivitySchedule từ DAO
        ActivityScheduleDAO activityScheduleDAO = new ActivityScheduleDAO(DatabaseConnector.getConnection());
        activityScheduleDAO.updateActivitySchedule(activitySchedule);

        // Chuyển hướng đến trang hiển thị chi tiết tour
        response.sendRedirect("ViewUpdateTourServlet?tourId=" + tourId);
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

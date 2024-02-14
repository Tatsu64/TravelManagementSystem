/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class ActivityScheduleServlet extends HttpServlet {

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
            out.println("<title>Servlet ActivityScheduleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActivityScheduleServlet at " + request.getContextPath() + "</h1>");
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
        Connection connection = DatabaseConnector.getConnection();

        // Nhận tourId và locationId từ request parameter
        String tourIdStr = request.getParameter("tourId");
        String locationIdStr = request.getParameter("locationId");

        if (tourIdStr != null) {
            // Chuyển đổi tourId từ string sang int
            int tourId = Integer.parseInt(tourIdStr);

            // Fetch the list of activity schedules for the specified tourId
            ActivityScheduleDAO activityScheduleDAO = new ActivityScheduleDAO(connection);
            List<ActivitySchedule> activityScheduleList = activityScheduleDAO.getActivityScheduleList(tourId);

            // Set the activityScheduleList as a request attribute
            request.setAttribute("activityScheduleList", activityScheduleList);

            // Forward to the JSP
            request.setAttribute("tourId", tourId);

            // Kiểm tra nếu locationId tồn tại thì đặt nó như một thuộc tính của yêu cầu
            if (locationIdStr != null && !locationIdStr.isEmpty()) {
                int locationId = Integer.parseInt(locationIdStr);
                request.setAttribute("locationId", locationId);
            }

            request.getRequestDispatcher("/ActivitySchedule.jsp").forward(request, response);
        } else {
            // Xử lý khi tourId không tồn tại trong request
            // Ví dụ: Hiển thị trang lỗi hoặc chuyển hướng sang trang khác
            response.sendRedirect("error.jsp");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ActivityScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("error.jsp"); // Redirect to an error page in case of exception
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
            String locationIdStr = request.getParameter("locationId");
            int locationId = -1; // Khởi tạo giá trị mặc định cho locationId
            if (locationIdStr != null && !locationIdStr.isEmpty()) {
                locationId = Integer.parseInt(locationIdStr);
            }

            // Lấy thông tin từ request
            String tourIdStr = request.getParameter("tourId");
            int tourId;
            if (tourIdStr != null && !tourIdStr.isEmpty()) {
                tourId = Integer.parseInt(tourIdStr);
            } else {
                // Xử lý khi tourId không tồn tại hoặc là chuỗi rỗng
                // Ví dụ: Hiển thị trang lỗi hoặc chuyển hướng sang trang khác
                response.sendRedirect("error.jsp");
                return;
            }
            int dayNumber = Integer.parseInt(request.getParameter("dayNumber"));
            String activityName = request.getParameter("activityName");
            String activityDateStr = request.getParameter("activityDate");
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");
            String location = request.getParameter("location");
            String description = request.getParameter("activityDescription");
            String imageUrl = request.getParameter("imageUrl");

            Date activityDate = parseDate(activityDateStr);

            // Chuyển đổi startTimeStr thành startTime có cùng ngày với activityDate
            Time startTime = parseTime(activityDateStr, startTimeStr);

            // Chuyển đổi endTimeStr thành endTime có cùng ngày với activityDate
            Time endTime = parseTime(activityDateStr, endTimeStr);

            // Tạo đối tượng ActivitySchedule
            ActivitySchedule activitySchedule = new ActivitySchedule();
            activitySchedule.setTour(new Tour(tourId)); // Đặt tourId vào đối tượng Tour
            activitySchedule.setDayNumber(dayNumber);
            activitySchedule.setActivityName(activityName);
            activitySchedule.setActivityDate(activityDate);
            activitySchedule.setStartTime(startTime);
            activitySchedule.setEndTime(endTime);
            activitySchedule.setLocation(location);
            activitySchedule.setDescription(description);
            activitySchedule.setImageUrl(imageUrl);

            // Gọi phương thức createActivitySchedule của ActivityScheduleDAO
            ActivityScheduleDAO activityScheduleDAO = new ActivityScheduleDAO(DatabaseConnector.getConnection());
            activityScheduleDAO.createActivitySchedule(activitySchedule);

            // Redirect hoặc forward đến trang khác sau khi thêm thành công
            response.sendRedirect("ActivityScheduleServlet?tourId=" + tourId + "&locationId=" + locationId);
        } catch (SQLException ex) {
            // Xử lý ngoại lệ SQLException
            ex.printStackTrace();
            response.sendRedirect("404.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(ActivityScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


// Helper method to parse a string into a Date
    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disallow lenient parsing
        return sdf.parse(dateStr);
    }

// Helper method to parse a string into a Time with the same date as activityDate
    private Time parseTime(String activityDateStr, String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null; // Trả về null nếu chuỗi thời gian là null hoặc rỗng
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = sdf.parse(activityDateStr + " " + timeStr);
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

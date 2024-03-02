/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ReviewDAO;
import model.database.DatabaseConnector;
import model.entity.Review;

/**
 *
 * @author ADMIN
 */
public class ManageReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageReviewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageReviewServlet at " + request.getContextPath() + "</h1>");
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
        if (action.equals("delete")) {
            deleteReview(request, response);
            return; // Ngăn chặn việc loadReviews sau khi xử lý delete
        }
    }
    // Load lại dữ liệu và chuyển hướng người dùng đến trang quản lý review
    loadReviews(request, response);
}

private void loadReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Khởi tạo ReviewDAO
        ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());

        // Gọi phương thức getAllReviews từ ReviewDAO để lấy danh sách reviews
        List<Review> reviewList = reviewDAO.getAllReviews();

        // Đặt danh sách reviews vào thuộc tính của request để hiển thị trên JSP
        request.setAttribute("reviewList", reviewList);

        // Chuyển hướng sang trang JSP để hiển thị danh sách reviews
        request.getRequestDispatcher("ManageReview.jsp").forward(request, response);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void deleteReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy reviewId từ request
    int reviewId = Integer.parseInt(request.getParameter("reviewId"));

    // Khởi tạo một đối tượng ReviewDAO
    ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());

    try {
        // Xoá review từ cơ sở dữ liệu bằng phương thức deleteReview
        reviewDAO.deleteReview(reviewId);
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý lỗi tùy theo yêu cầu, ví dụ: thông báo lỗi cho người dùng
    }
    // Sau khi xoá review thành công, load lại dữ liệu và chuyển hướng người dùng đến trang quản lý review
    loadReviews(request, response);
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
        processRequest(request, response);
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

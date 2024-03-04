/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.ReviewDAO;
import model.database.DatabaseConnector;
import model.entity.Review;

/**
 *
 * @author NPB
 */
public class UpdateReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateReviewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateReviewServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "update":
                    updateReview(request, response);
                    break;
                case "delete":
                    deleteReview(request, response);
                    break;
                default:
                    response.sendRedirect("404.jsp");
                    break;
            }
        } else {
            response.sendRedirect("404.jsp");
        }
    }

    private void updateReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        try {
            int reviewId = Integer.parseInt(request.getParameter("reviewId"));
            
            // Tạo đối tượng ReviewDAO
            ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());
            
            // Gọi phương thức getReviewById từ ReviewDAO để lấy thông tin của đánh giá
            Review review = reviewDAO.getReviewById(reviewId);
            
            // Kiểm tra nếu đánh giá không tồn tại, chuyển hướng đến trang 404
            if (review == null) {
                response.sendRedirect("404.jsp");
                return;
            }
            
            // Lưu thông tin của đánh giá vào request attribute để hiển thị trên trang jsp
            request.setAttribute("review", review);
            
            // Chuyển hướng đến trang cập nhật thông tin đánh giá, nơi có thể hiển thị thông tin và cho phép người dùng cập nhật
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateReview.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UpdateReviewServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
}


    private void deleteReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy reviewId từ request parameter
            int reviewId = Integer.parseInt(request.getParameter("reviewId"));

            // Tạo một đối tượng ReviewDAO để thực hiện thao tác xóa
            ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());

            // Gọi phương thức xóa đánh giá từ ReviewDAO
            reviewDAO.deleteReview(reviewId);

            // Chuyển hướng đến trang danh sách đánh giá sau khi xóa thành công
            response.sendRedirect("ViewReviewServlet?");
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
          // Retrieve parameters from the request
          int reviewId = Integer.parseInt(request.getParameter("reviewId"));
          String content = request.getParameter("content");
          int rating = Integer.parseInt(request.getParameter("rating"));

          // Create a new Review object with the updated data
          Review review = new Review();
          review.setReviewId(reviewId);
          review.setContent(content);
          review.setRating(rating);

          // Update the review in the database
          ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());
          boolean updated = reviewDAO.updateReview(review);

          if (updated) {
            HttpSession session = request.getSession();
            session.setAttribute("succMsg", "Update Review Successfully");
            // If the review is updated successfully, set success message and redirect to view review page    
            response.sendRedirect("ViewReviewServlet?");
          } else {
              // If update fails, redirect to error page
              response.sendRedirect("404.jsp");
          }
      } catch (Exception e) {
          // Handle any exceptions
          e.printStackTrace();
          response.sendRedirect("404.jsp");
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

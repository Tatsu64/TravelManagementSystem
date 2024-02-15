/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.Tour;

/**
 *
 * @author ADMIN
 */
public class ApprovalTourServlet extends HttpServlet {

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
            out.println("<title>Servlet ApprovalTourServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApprovalTourServlet at " + request.getContextPath() + "</h1>");
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
        TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
        List<Tour> tours = tourDAO.getToursWithApprovalStatus(0); // Lấy danh sách các tour với approval_status = 0
        request.setAttribute("tours", tours); // Đặt danh sách các tour vào thuộc tính "tours" của request
        request.getRequestDispatcher("/ManageTour.jsp").forward(request, response); 
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
        String action = request.getParameter("action");
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
        if ("Accept".equals(action)) {
            tourDAO.updateApprovalStatus(tourId, 1); // Cập nhật approval_status thành 1
        } else if ("Reject".equals(action)) {
            tourDAO.updateApprovalStatus(tourId, 2); // Cập nhật approval_status thành 2
        }

        // Sau khi xử lý yêu cầu, chuyển hướng người dùng trở lại trang quản lý tour
        response.sendRedirect(request.getContextPath() + "/ApprovalTourServlet");
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

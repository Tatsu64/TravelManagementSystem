/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import model.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.database.DatabaseConnector;
import model.entity.User;

/**
 *
 * @author Admin
 */
public class VerifyOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyOTPServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyOTPServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = DatabaseConnector.getConnection();
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String userOTP = request.getParameter("otp");
        

        String saveOTP = (String) request.getSession().getAttribute("otp");

        LocalDateTime expirationTime = (LocalDateTime) request.getSession().getAttribute("otpExpirationTime");
        if (isOTPValid(saveOTP, expirationTime) && saveOTP.equals(userOTP)) {
            if (userId != null) {
                request.getParameter("userId");
                response.sendRedirect("resetpassword.jsp?userId=" + userId);
            } else {
                try {
                    UserDAO udao = new UserDAO(connection);
                    System.out.println(udao);
                    
                    User user = new User(name, pass, email, address, phone, role);
                    udao.addUser(user);
                    
                    request.getSession().setAttribute("auth", user);
                    
                    request.setAttribute("mess", "Sign Up Success");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(VerifyOTPServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            response.sendRedirect("404.jsp");
        }
    }

    private boolean verifyOTP(String email, String userOTP) {
        String saveOTP = SendEmailServlet.emailToOTP.get(email);
        return userOTP.equals(saveOTP);

    }

    private boolean isOTPValid(String saveOTP, LocalDateTime expirationTime) {
        if (saveOTP != null && expirationTime != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            return currentTime.isBefore(expirationTime);
        }
        return false;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
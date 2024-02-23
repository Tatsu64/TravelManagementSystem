/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import model.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.database.DatabaseConnector;
import model.entity.User;

/**
 *
 * @author Admin
 */
public class ResetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
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
    int userId = Integer.parseInt(request.getParameter("userId"));
    String newPassword = request.getParameter("newPassword");
    String confirmPassword = request.getParameter("confirmPassword");

    try {
       
        
        if (!newPassword.equals(confirmPassword)) {
            // Passwords don't match, set error message
            request.setAttribute("mess", "Passwords do not match");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return; // End processing
        }
        
        // Get the user by ID
        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.getUserById(userId);
        
        
        
        if (user != null) {
            // Update the password
            userDAO.updatePassword(newPassword, userId);
            
            request.getRequestDispatcher("password-changed-successfully.jsp").forward(request, response);
            
            request.getSession().setAttribute("auth", user);
        } else {
            // User not found, redirect to error page
            response.sendRedirect("404.jsp");
        }
    } catch (NumberFormatException ex) {
        Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("404.jsp");
    } catch (Exception ex) {
        Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendRedirect("404.jsp");
    }
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import com.sun.jdi.connect.spi.Connection;
import static controller.account.SendEmailServlet.emailToExpirationTime;
import static controller.account.SendEmailServlet.emailToOTP;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.SendEmail;
import model.dao.UserDAO;
import model.database.DatabaseConnector;
import model.entity.User;

/**
 *
 * @author ADMIN
 */
public class SignUpServlet extends HttpServlet {

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
            out.println("<title>Servlet SignUpServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            java.sql.Connection connection = DatabaseConnector.getConnection();
            // Get a database connection
            // Get user input from request parameters
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String role = request.getParameter("role");

            // Create a User object
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setAddress(address);
            user.setRole(role);

            // Create a UserDAO with the obtained connection
            UserDAO dao = new UserDAO(DatabaseConnector.getConnection());
            
            // Check if the email already exists
            try {
            if (dao.checkUserExist(email, name)) {
                 request.setAttribute("mess", "User already existed");
                // Forward back to the signup page with an error message
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                    String otp = generateOTP();
                    saveOTP(email, otp);        
                    
                    SendEmail sendEmail = new SendEmail();
                    sendEmail.send(email, otp);
                    
                    HttpSession session = request.getSession();
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);
                    session.setAttribute("otpExpirationTime", expirationTime);

                    // Lưu mã OTP và thời điểm hết hạn vào Map
                    emailToOTP.put(email, otp);
                    emailToExpirationTime.put(email, expirationTime);
                    
                    
                    session.setAttribute("otp", otp);
                    
                    session.setAttribute("auth", user);
                        
                    response.sendRedirect("verifySignUp.jsp");
                    
                    
                }

           
        } catch (Exception ex) {
            Logger.getLogger(SendEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    
    private void saveOTP(String email, String otp) {

        emailToOTP.put(email, otp);

    }

    private String generateOTP() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

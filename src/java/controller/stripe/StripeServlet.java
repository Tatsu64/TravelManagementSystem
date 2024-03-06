/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class StripeServlet extends HttpServlet {

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
            out.println("<title>Servlet StripeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StripeServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        Stripe.apiKey = "sk_test_51O9M2LLnaKIPoSBvbWo0Oo9jU3u6OkHhJ13lnw1b44rpzprjENaYnPMXXS4lDTXuPy4SprizqnHwmkI7SrZCTTe300XfPEWvHh";

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice"));
        int amount = totalPrice.multiply(new BigDecimal(100)).intValue();


        try {
//            String total = (String)request.getAttribute("total");
//
//            if (total != null) {
//                // Kiểm tra xem chuỗi có null hay không trước khi thao tác
//                String trimmedString = total.trim();
//                out.print("k null");// Thực hiện phương thức trim()
//                // ... Tiếp tục thao tác với chuỗi đã được trim
//            } else {
//                out.println("null");
//            }
//--------------------------Tao account moi cho user ( trong signinServlet )
            Map<String, Object> customerParameter = new HashMap<String, Object>();
            customerParameter.put("email", email);
            customerParameter.put("name", name);

            Customer newCustomer = Customer.create(customerParameter);
            String customerId = newCustomer.getId();
//            out.println("Da tao moi user la: " + newCustomer.getEmail());
//------------------------------------------------------       
//-----------------------Lay ve thong tin cua User vừa tạo 

            Map<String, Object> retrieveParams
                    = new HashMap<>();
            List<String> expandList = new ArrayList<>();
            expandList.add("sources");

            retrieveParams.put("expand", expandList);
            Customer customer
                    = Customer.retrieve(
                            customerId,
                            retrieveParams,
                            null
                    );

//-------------------------------------------------------
//          Customer customer = Customer.retrieve("cus_OwJcnCobn7bQMf");
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            out.println(gson.toJson(customer));
// -------------------- ADD NEW CARD VAO USER Vua duoc tao
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("source", "tok_visa");

            Card card = (Card) customer.getSources().create(cardParams);
            out.println("Da add thanh cong the: " + card.getId());

// ------------------------------------------------------
// --------------------- Charge tien  user 
            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", amount);
            chargeParam.put("currency", "usd");
            chargeParam.put("customer", customer.getId());

            Charge charge = Charge.create(chargeParam);
            out.println("Da ghi no thanh cong so tien la: " + charge.getAmount());

//--------------------------------------------------
            response.sendRedirect("Home");
//--------------------------------
//--------------------Show thong tin user
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            out.println(gson.toJson(customer));
            //------------------------------------------------------           
        } catch (StripeException ex) {
            Logger.getLogger(StripeServlet.class.getName()).log(Level.SEVERE, null, ex);
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

package controller.account;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.ReviewDAO;
import model.entity.Review;
import java.util.List;
import model.database.DatabaseConnector;

public class ViewReviewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Your processing logic here
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");
            ReviewDAO reviewDAO = new ReviewDAO(DatabaseConnector.getConnection());
            List<Review> reviews = reviewDAO.getReviewsByUserId(userId);
            if (reviews.isEmpty()) {
                response.sendRedirect("View-Review-History.jsp");
                return;
            } else {
                // Check if the success message is present in the session and display it
                if (session.getAttribute("succMsg") != null) {
                    request.setAttribute("succMsg", session.getAttribute("succMsg"));
                    session.removeAttribute("succMsg"); // Remove the attribute after displaying
                }
                request.setAttribute("reviews", reviews);
                request.getRequestDispatcher("View-Review-History.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("404.jsp");
            e.printStackTrace();
        }
    }


    @Override
    public String getServletInfo() {
        return "Servlet to view review history";
    }
}

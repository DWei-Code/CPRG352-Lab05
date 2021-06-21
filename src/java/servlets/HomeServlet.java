package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get the current session
        HttpSession session = request.getSession();
        
        // checks if there is a session username, if so stay on home page
        // unless logout button is clicked
        if (session.getAttribute("userName") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}

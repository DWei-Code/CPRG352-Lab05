package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get the current session
        HttpSession session = request.getSession();
        // if the user logged out the session is invalidated and a new one is started
        String action = request.getParameter("logout");
        if (action != null) {
            session.invalidate();
            session = request.getSession();
            String logoutText = "You have succesfully logged out!";
            request.setAttribute("displayText", logoutText);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        String userNameCheck = (String) session.getAttribute("userName");
        // Redirects the user back to home page if they are already correctly logged in
        // trying to reach the login page without logging out first.
        // File not found exception caught.
        try {
            if (userNameCheck != null) {
                response.sendRedirect("home");
                return;

            }
        } catch (IOException e) {
            System.err.println("File not found");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");

        request.setAttribute("saveName", userName);

        // checks if user name and password is filled out
        if (userName == null || userName.equals("") || password == null || password.equals("")) {
            String errorText = "Please enter both password and username to login!";
            request.setAttribute("displayText", errorText);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        // checks if user is logging in with correct credentials
        AccountService loginCheck = new AccountService();
        User newUser = loginCheck.login(userName, password);

        // if the credentials are not correct error message pops up
        if (newUser == null) {
            String errorText = "Please enter the correct username and password combination to login!";
            request.setAttribute("displayText", errorText);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        // if the user login is correct the username is stored as a session variable
        // Nullpointer exception is caught
        try {
            String sessionUser = newUser.getUserName();
            String sessionPass = newUser.getPassword();
            if (sessionUser != null && sessionPass == null) {
                session.setAttribute("userName", sessionUser);
                // redirect to home page
                response.sendRedirect("home");
            }
        } catch (NullPointerException e) {
            System.err.println("No user name found");
        }
    }
}

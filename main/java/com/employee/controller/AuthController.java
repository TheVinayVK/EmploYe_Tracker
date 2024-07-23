package com.employee.controller;

import com.employee.dao.UserDAO;
import com.employee.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            handleLogin(request, response);
        } else if ("register".equals(action)) {
            handleRegister(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.getUserByEmail(email);
            if (user != null && user.getPasswordHash().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("index.jsp?error=Invalid email or password");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error in handleLogin", e);
            response.sendRedirect("index.jsp?error=Something went wrong");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int roleID = Integer.parseInt(request.getParameter("roleID"));

        LOGGER.log(Level.INFO, "Attempting to register user with RoleID: {0}", roleID);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(password);
        user.setRoleID(roleID);

        try {
            userDAO.addUser(user);
            response.sendRedirect("index.jsp?message=Registration successful");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error in handleRegister", e);
            response.sendRedirect("register.jsp?error=Something went wrong. SQL State: " + e.getSQLState() + " Error Code: " + e.getErrorCode());
        }
    }
}
package com.brief.java_simplon_clone_web_v.controllers.authentication;

import com.brief.java_simplon_clone_web_v.services.AdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        switch (role) {
            case "admin" -> {
                AdminService adminService = new AdminService();
                if (adminService.login(email, password)) {
                    response.sendRedirect("admin.jsp");
                } else {
                    request.setAttribute("error", "Invalid email or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }

    }
}

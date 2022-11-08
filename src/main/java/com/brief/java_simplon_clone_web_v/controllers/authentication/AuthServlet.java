package com.brief.java_simplon_clone_web_v.controllers.authentication;

import com.brief.java_simplon_clone_web_v.services.AdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(role == null) {
            request.setAttribute("error_login", "Role is required");
            request.getRequestDispatcher("auth/Login.jsp").forward(request, response);
        } else {
            switch (role) {
                case "admin" -> {
                    AdminService adminService = new AdminService();
                    if (adminService.login(email, password)) {
                        response.sendRedirect("admin/dashboard.jsp");
                    } else {
                        request.setAttribute("error_login", "Invalid email or password");
                        request.getRequestDispatcher("auth/Login.jsp").forward(request, response);
                    }
                }
            }
        }

    }
}

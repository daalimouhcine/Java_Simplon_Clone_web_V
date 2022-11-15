package com.brief.java_simplon_clone_web_v.controllers.authentication;

import com.brief.java_simplon_clone_web_v.entities.AdminsEntity;
import com.brief.java_simplon_clone_web_v.entities.PromosEntity;
import com.brief.java_simplon_clone_web_v.services.AdminService;
import com.brief.java_simplon_clone_web_v.services.PromoService;
import com.brief.java_simplon_clone_web_v.services.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet({"/", "/Login", "/Logout"})
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/":
                response.sendRedirect(request.getContextPath() + "/Login");
                break;
            case "/Login":
                // if session is not null, redirect to home
                if (request.getSession().getAttribute("admin") != null) {
                    response.sendRedirect("/admin");
                } else if(request.getSession().getAttribute("teacher") != null) {
                    response.sendRedirect("/teacher");
                } else {
                    request.getRequestDispatcher("auth/Login.jsp").forward(request, response);
                }
                break;
            case "/Logout":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/Login");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/Login" -> {
                String role = request.getParameter("roleType");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                if(role == null) {
                    request.setAttribute("email", email);
                    request.setAttribute("password", password);
                    request.setAttribute("error_login", "Role is required");
                    request.getRequestDispatcher("auth/Login.jsp").forward(request, response);
                } else {
                    switch (role) {
                        case "admin" -> {
                            AdminService adminService = new AdminService();
                            if (adminService.login(email, password)) {
                                AdminsEntity admin = adminService.getAdminByEmail(email);
                                HttpSession session = request.getSession();
                                session.setAttribute("admin", admin);
                                response.sendRedirect("/admin");
                            } else {
                                request.setAttribute("email", email);
                                request.setAttribute("password", password);
                                request.setAttribute("role", role);
                                request.setAttribute("error_login", "Invalid email or password");
                                request.getRequestDispatcher("auth/Login.jsp").forward(request, response);
                            }
                        }
                        case "teacher" -> {
                            TeacherService teacherService = new TeacherService();
                            if (teacherService.login(email, password)) {
                                HttpSession session = request.getSession();
                                PromoService promoService = new PromoService();
                                PromosEntity promo = promoService.getPromoByTeacherId(teacherService.getTeacherByEmail(email).getId());
                                session.setAttribute("teacher", teacherService.getTeacherByEmail(email));
                                session.setAttribute("promo", promo);
                                response.sendRedirect("/teacher");

                            } else {
                                request.setAttribute("email", email);
                                request.setAttribute("password", password);
                                request.setAttribute("role", role);
                                request.setAttribute("error_login", "Invalid email or password");
                                request.getRequestDispatcher("auth/Login.jsp").forward(request, response);
                            }
                        }

                    }
                }
            }
            case "/Logout" -> {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("/Login");
            }
        }


    }
}

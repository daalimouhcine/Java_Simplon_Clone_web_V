package com.brief.java_simplon_clone_web_v.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet({"/student"})
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("student") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");

        } else {
            String path = request.getServletPath();
            switch (path) {
                case "/student" -> {
                    request.getRequestDispatcher("/student/Dashboard.jsp").forward(request, response);
                }
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.brief.java_simplon_clone_web_v.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet({"/teacher", "/teacher/briefs", "/teacher/briefs/add"})
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/teacher" -> {
                request.getRequestDispatcher("/teacher/Dashboard.jsp").forward(request, response);
            }
            case "/teacher/briefs" -> {
                request.getRequestDispatcher("/teacher/Briefs.jsp").forward(request, response);
            }
            case "/teacher/briefs/add" -> {
                request.getRequestDispatcher("/teacher/AddBrief.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

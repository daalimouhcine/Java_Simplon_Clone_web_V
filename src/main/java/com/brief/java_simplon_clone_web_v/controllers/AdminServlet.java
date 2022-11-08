package com.brief.java_simplon_clone_web_v.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet({"admin/dashboard", "admin/updateProfile", "admin/teachers", "admin/students", "admin/promos"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mapping(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mapping(request, response);
    }

    public void Mapping(HttpServletRequest request, HttpServletResponse response) {
//        switch (request) {
//            case "admin/dashboard" -> {
//                return "admin/dashboard.jsp";
//            }
//            case "admin/updateProfile" -> {
//                return "admin/updateProfile.jsp";
//            }
//            case "admin/teachers" -> {
//                return "admin/teachers.jsp";
//            }
//            case "admin/students" -> {
//                return "admin/students.jsp";
//            }
//            case "admin/promos" -> {
//                return "admin/promos.jsp";
//            }
//        }
    }
}

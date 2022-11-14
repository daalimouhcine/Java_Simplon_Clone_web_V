package com.brief.java_simplon_clone_web_v.controllers;

import com.brief.java_simplon_clone_web_v.entities.BriefsEntity;
import com.brief.java_simplon_clone_web_v.entities.PromosEntity;
import com.brief.java_simplon_clone_web_v.entities.StudentsEntity;
import com.brief.java_simplon_clone_web_v.entities.TeachersEntity;
import com.brief.java_simplon_clone_web_v.services.BriefService;
import com.brief.java_simplon_clone_web_v.services.PromoService;
import com.brief.java_simplon_clone_web_v.services.StudentService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet({"/teacher", "/teacher/briefs", "/teacher/briefs/add", "/teacher/myStudents", "/teacher/students/assign"})
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService studentService = new StudentService();
        TeachersEntity teacher = (TeachersEntity) request.getSession().getAttribute("teacher");
        int studentCount = studentService.getStudentsByPromo(teacher.getId()).size();
        request.setAttribute("studentCount", studentCount);
        String path = request.getServletPath();
        switch (path) {
            case "/teacher" -> {
                request.getRequestDispatcher("/teacher/Dashboard.jsp").forward(request, response);
            }
            case "/teacher/briefs" -> {
                PromoService promoService = new PromoService();
                PromosEntity promo = promoService.getPromoByTeacherId(teacher.getId());
                BriefService briefService = new BriefService();
                List<BriefsEntity> briefList = briefService.getPromoBriefs(promo.getId());
                request.setAttribute("briefList", briefList);
                request.getRequestDispatcher("/teacher/Briefs.jsp").forward(request, response);
            }
            case "/teacher/briefs/add" -> {
                request.getRequestDispatcher("/teacher/AddBrief.jsp").forward(request, response);
            }
            case "/teacher/myStudents" -> {
                PromoService promoService = new PromoService();
                PromosEntity promo = promoService.getPromoById(teacher.getId());
                if(promo != null) {
                    List<StudentsEntity> studentList = studentService.getStudentsByPromo(promo.getId());
                    request.setAttribute("studentList", studentList);
                    request.getRequestDispatcher("/teacher/MyStudents.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/teacher/Dashboard.jsp").forward(request, response);
                }
            }
            case "/teacher/students/assign" -> {
                List<StudentsEntity> noPromoStudentList = studentService.getStudentsWithNoPromo();
                request.setAttribute("noPromoStudentList", noPromoStudentList);
                request.getRequestDispatcher("/teacher/AssignStudent.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/teacher/myStudents" -> {
                String action = request.getParameter("action");
                if(action.equals("unassign")) {
                    int studentId = Integer.parseInt(request.getParameter("studentId"));
                    StudentService studentService = new StudentService();
                    studentService.unassignStudent(studentId);
                }

                response.sendRedirect("/teacher/myStudents");
            }
            case "/teacher/students/assign" -> {
                TeachersEntity teacher = (TeachersEntity) request.getSession().getAttribute("teacher");
                PromoService promoService = new PromoService();
                PromosEntity promo = promoService.getPromoByTeacherId(teacher.getId());
                int studentId = Integer.parseInt(request.getParameter("studentId"));
                StudentService studentService = new StudentService();
                studentService.assignStudent(studentId, promo.getId());
                response.sendRedirect("/teacher/students/assign");
            }

        }
    }
}

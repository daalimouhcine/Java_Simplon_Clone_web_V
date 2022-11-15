package com.brief.java_simplon_clone_web_v.controllers;

import com.brief.java_simplon_clone_web_v.entities.BriefsEntity;
import com.brief.java_simplon_clone_web_v.entities.PromosEntity;
import com.brief.java_simplon_clone_web_v.entities.StudentsEntity;
import com.brief.java_simplon_clone_web_v.entities.TeachersEntity;
import com.brief.java_simplon_clone_web_v.services.BriefService;
import com.brief.java_simplon_clone_web_v.services.PromoService;
import com.brief.java_simplon_clone_web_v.services.StudentService;
import com.brief.java_simplon_clone_web_v.utils.Email;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet({"/teacher", "/teacher/myBriefs", "/teacher/briefs/add", "/teacher/myStudents", "/teacher/students/assign"})
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check for session if teacher is logged in
        if (request.getSession().getAttribute("teacher") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");

        } else {
            String path = request.getServletPath();
            if(request.getSession().getAttribute("promo") == null) {
                request.getRequestDispatcher("/teacher/Dashboard.jsp").forward(request, response);
            } else {
                StudentService studentService = new StudentService();
                BriefService briefService = new BriefService();
                TeachersEntity teacher = (TeachersEntity) request.getSession().getAttribute("teacher");
                PromoService promoService = new PromoService();
                PromosEntity promo = promoService.getPromoByTeacherId(teacher.getId());
                int studentCount = studentService.getStudentsByPromo(promo.getId()).size();
                request.setAttribute("studentCount", studentCount);
                int briefCount = briefService.getBriefsByPromoId(promo.getId()).size();
                request.setAttribute("briefCount", briefCount);
                switch (path) {
                    case "/teacher" -> {
                        request.getRequestDispatcher("/teacher/Dashboard.jsp").forward(request, response);
                    }
                    case "/teacher/myBriefs" -> {
                        List<BriefsEntity> briefList = briefService.getPromoBriefs(promo.getId());
                        request.setAttribute("briefList", briefList);
                        request.getRequestDispatcher("/teacher/MyBriefs.jsp").forward(request, response);
                    }
                    case "/teacher/briefs/add" -> {
                        request.getRequestDispatcher("/teacher/AddBrief.jsp").forward(request, response);
                    }
                    case "/teacher/myStudents" -> {
                        if (promo != null) {
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
            case "/teacher/myBriefs" -> {
                String action = request.getParameter("action");
                if(action.equals("delete")) {
                    int briefId = Integer.parseInt(request.getParameter("briefId"));
                    BriefService briefService = new BriefService();
                    briefService.delete(briefId);
                } else if(action.equals("launch")) {
                    int briefId = Integer.parseInt(request.getParameter("briefId"));
                    BriefService briefService = new BriefService();
                    BriefsEntity brief = briefService.getBriefById(briefId);
                    brief.setLaunched(true);
                    briefService.update(brief);
                    StudentService studentService = new StudentService();
                    List<StudentsEntity> studentList = studentService.getStudentsByPromo(brief.getPromoid());
                    for(StudentsEntity student : studentList) {
                        Email.sendEmail(brief, student);
                    }
                }
                response.sendRedirect("/teacher/myBriefs");
            }
            case "/teacher/briefs/add" -> {
                TeachersEntity teacher = (TeachersEntity) request.getSession().getAttribute("teacher");
                PromoService promoService = new PromoService();
                PromosEntity promo = promoService.getPromoByTeacherId(teacher.getId());
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String technologies = request.getParameter("technologies");
                int promoId = promo.getId();
                BriefsEntity newBrief = new BriefsEntity();
                newBrief.setTitle(title);
                newBrief.setDescription(description);
                newBrief.setTechnologies(technologies);
                newBrief.setPromoid(promoId);
                newBrief.setLaunched(false);
                BriefService briefService = new BriefService();
                briefService.add(newBrief);
                response.sendRedirect("/teacher/myBriefs");
            }

        }
    }
}

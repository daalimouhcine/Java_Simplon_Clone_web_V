package com.brief.java_simplon_clone_web_v.controllers;

import com.brief.java_simplon_clone_web_v.entities.BriefsEntity;
import com.brief.java_simplon_clone_web_v.entities.PromosEntity;
import com.brief.java_simplon_clone_web_v.entities.StudentsEntity;
import com.brief.java_simplon_clone_web_v.entities.TeachersEntity;
import com.brief.java_simplon_clone_web_v.services.BriefService;
import com.brief.java_simplon_clone_web_v.services.PromoService;
import com.brief.java_simplon_clone_web_v.services.StudentService;
import com.brief.java_simplon_clone_web_v.services.TeacherService;
import com.brief.java_simplon_clone_web_v.utils.HashPassword;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin", "/admin/promos", "/admin/addPromo", "/admin/briefs", "/admin/students", "/admin/addStudent", "/admin/teachers", "/admin/addTeacher"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check for session if admin is logged in
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");

        } else {
            PromoService promoService = new PromoService();
            TeacherService teacherService = new TeacherService();
            StudentService studentService = new StudentService();
            BriefService briefService = new BriefService();

            int nbPromo = promoService.count();
            int nbTeacher = teacherService.count();
            int nbStudent = studentService.count();
            int nbBrief = briefService.count();

            request.setAttribute("nbPromo", nbPromo);
            request.setAttribute("nbTeacher", nbTeacher);
            request.setAttribute("nbStudent", nbStudent);
            request.setAttribute("nbBrief", nbBrief);

            String path = request.getServletPath();
            switch (path) {
                case "/admin" -> request.getRequestDispatcher("admin/Dashboard.jsp").forward(request, response);
                case "/admin/promos" -> {
                    request.getRequestDispatcher("/admin/Promos.jsp").forward(request, response);
                }
                case "/admin/addPromo" -> {
                    request.getRequestDispatcher("/admin/AddPromo.jsp").forward(request, response);
                }
                case "/admin/briefs" -> {
                    BriefService briefService1 = new BriefService();
                    List<BriefsEntity> briefs = briefService1.getAllBriefs();
                    request.setAttribute("briefs", briefs);
                    request.getRequestDispatcher("/admin/Briefs.jsp").forward(request, response);
                }
                case "/admin/students" -> {
                    StudentService studentService1 = new StudentService();
                    List<StudentsEntity> students = studentService1.getAllStudents();
                    request.setAttribute("students", students);
                    request.getRequestDispatcher("/admin/Students.jsp").forward(request, response);
                }
                case "/admin/addStudent" -> request.getRequestDispatcher("/admin/AddStudent.jsp").forward(request, response);
                case "/admin/teachers" -> {
                    TeacherService teacherService1 = new TeacherService();
                    List<TeachersEntity> teachers = teacherService1.getAllTeachers();
                    request.setAttribute("teachers", teachers);
                    request.getRequestDispatcher("/admin/Teachers.jsp").forward(request, response);
                }
                case "/admin/addTeacher" -> request.getRequestDispatcher("/admin/AddTeacher.jsp").forward(request, response);
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch(path) {
            case "/admin/addPromo" -> {
                String promoName = request.getParameter("promoName");
                PromosEntity newPromo = new PromosEntity();
                newPromo.setName(promoName);
                PromoService promoService = new PromoService();
                promoService.add(newPromo);
                response.sendRedirect("/admin/promos");
            }
            case "/admin/promos" -> {
                String action = request.getParameter("action");
                String id = request.getParameter("id");
                PromoService promoService = new PromoService();
                if (action.equals("delete")) {
                    promoService.delete(Integer.parseInt(id));
                }
                response.sendRedirect("/admin/promos");
            }
            case "/admin/addStudent" -> {
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                StudentsEntity newStudent = new StudentsEntity();
                newStudent.setFullname(fullName);
                newStudent.setEmail(email);
                newStudent.setPhone(phone);
                newStudent.setPassword(HashPassword.hash(password));
                StudentService studentService = new StudentService();
                studentService.add(newStudent);
                response.sendRedirect("/admin/students");
            }
            case "/admin/students" -> {
                String action = request.getParameter("action");
                String id = request.getParameter("id");
                StudentService studentService = new StudentService();
                if (action.equals("delete")) {
                    studentService.delete(Integer.parseInt(id));
                } else if(action.equals("assignPromo")) {
                    StudentsEntity student = studentService.getStudentById(Integer.parseInt(id));
                    student.setPromoid(Integer.parseInt(request.getParameter("promo")));
                    studentService.update(student);
                } else if(action.equals("unassignPromo")) {
                    StudentsEntity student = studentService.getStudentById(Integer.parseInt(id));
                    student.setPromoid(null);
                    studentService.update(student);
                }
                response.sendRedirect("/admin/students");
            }
            case "/admin/addTeacher" -> {
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                TeachersEntity newTeacher = new TeachersEntity();
                newTeacher.setFullname(fullName);
                newTeacher.setEmail(email);
                newTeacher.setPhone(phone);
                newTeacher.setPassword(HashPassword.hash(password));
                TeacherService teacherService = new TeacherService();
                teacherService.add(newTeacher);
                response.sendRedirect("/admin/teachers");
            }
            case "/admin/teachers" -> {
                String action = request.getParameter("action");
                String id = request.getParameter("id");
                TeacherService teacherService = new TeacherService();
                if (action.equals("delete")) {
                    teacherService.delete(Integer.parseInt(id));
                    PromoService promoService = new PromoService();
                    PromosEntity promo = promoService.getPromoByTeacherId(Integer.parseInt(id));
                    if (promo != null) {
                        promo.setTeacherId(null);
                        promoService.update(promo);
                    }

                } else if(action.equals("assignPromo")) {
                    PromoService promoService = new PromoService();
                    PromosEntity promo = promoService.getPromoById(Integer.parseInt(request.getParameter("promo")));
                    promo.setTeacherId(Integer.parseInt(id));
                    promoService.update(promo);
                } else if(action.equals("unassignPromo")) {
                    PromoService promoService = new PromoService();
                    PromosEntity promo = promoService.getPromoByTeacherId(Integer.parseInt(id));
                    promo.setTeacherId(null);
                    promoService.update(promo);
                }
                response.sendRedirect("/admin/teachers");
            }
        }

    }


}

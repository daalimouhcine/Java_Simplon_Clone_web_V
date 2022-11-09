package com.brief.java_simplon_clone_web_v.services;

import com.brief.java_simplon_clone_web_v.config.EntityManagerConfig;
import com.brief.java_simplon_clone_web_v.entities.StudentsEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentService {

    public boolean add(StudentsEntity student) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(StudentsEntity student) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            StudentsEntity student = em.find(StudentsEntity.class, id);
            em.remove(student);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public StudentsEntity getStudentById(int id) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            StudentsEntity student = em.find(StudentsEntity.class, id);
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StudentsEntity> getAllStudents() {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            List<StudentsEntity> students = em.createQuery("SELECT s FROM StudentsEntity s", StudentsEntity.class).getResultList();
            em.getTransaction().commit();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int count() {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            int count = em.createQuery("SELECT COUNT(s) FROM StudentsEntity s", Long.class).getSingleResult().intValue();
            em.getTransaction().commit();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

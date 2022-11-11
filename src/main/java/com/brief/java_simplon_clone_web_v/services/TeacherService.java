package com.brief.java_simplon_clone_web_v.services;

import com.brief.java_simplon_clone_web_v.config.EntityManagerConfig;
import com.brief.java_simplon_clone_web_v.entities.AdminsEntity;
import com.brief.java_simplon_clone_web_v.entities.TeachersEntity;
import com.brief.java_simplon_clone_web_v.utils.HashPassword;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeacherService {

        public boolean add(TeachersEntity teacher) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                em.persist(teacher);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean update(TeachersEntity teacher) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                em.merge(teacher);
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
                TeachersEntity teacher = em.find(TeachersEntity.class, id);
                em.remove(teacher);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public TeachersEntity getTeacherById(int id) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                TeachersEntity teacher = em.find(TeachersEntity.class, id);
                em.getTransaction().commit();
                return teacher;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public TeachersEntity getTeacherByEmail(String email) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                TeachersEntity teacher = em.createQuery("SELECT t FROM TeachersEntity t WHERE t.email = :email", TeachersEntity.class)
                        .setParameter("email", email)
                        .getSingleResult();
                em.getTransaction().commit();
                return teacher;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public List<TeachersEntity> getAllTeachers() {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                List<TeachersEntity> teachers = em.createQuery("SELECT t FROM TeachersEntity t", TeachersEntity.class).getResultList();
                em.getTransaction().commit();
                return teachers;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public int count() {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                int count = em.createQuery("SELECT COUNT(t) FROM TeachersEntity t", Long.class).getSingleResult().intValue();
                em.getTransaction().commit();
                return count;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        public boolean login(String email, String password) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                TypedQuery<TeachersEntity> query = em.createQuery("SELECT t FROM TeachersEntity t WHERE t.email = :email", TeachersEntity.class);
                query.setParameter("email", email);
                TeachersEntity teacher = query.getSingleResult();
                em.getTransaction().commit();
                if(teacher != null) {
                    return HashPassword.check(password, teacher.getPassword());
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

}

package com.brief.java_simplon_clone_web_v.services;

import com.brief.java_simplon_clone_web_v.config.EntityManagerConfig;
import com.brief.java_simplon_clone_web_v.entities.AdminsEntity;
import com.brief.java_simplon_clone_web_v.utils.HashPassword;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.SQLException;

public class AdminService {
    public boolean update(AdminsEntity admin) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            em.merge(admin);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String email, String password) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            TypedQuery<AdminsEntity> query = em.createQuery("SELECT a FROM AdminsEntity a WHERE a.email = :email", AdminsEntity.class);
            query.setParameter("email", email);
            AdminsEntity admin = query.getSingleResult();
            if (admin != null) {
                return HashPassword.check(password, admin.getPassword());
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public AdminsEntity getAdminByEmail(String email) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            TypedQuery<AdminsEntity> query = em.createQuery("SELECT a FROM AdminsEntity a WHERE a.email = :email", AdminsEntity.class);
            query.setParameter("email", email);
            AdminsEntity admin = query.getSingleResult();
            return admin;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}

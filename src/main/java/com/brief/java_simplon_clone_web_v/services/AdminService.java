package com.brief.java_simplon_clone_web_v.services;

import com.brief.java_simplon_clone_web_v.config.EntityManagerConfig;
import com.brief.java_simplon_clone_web_v.entities.AdminsEntity;
import com.brief.java_simplon_clone_web_v.utils.HashPassword;
import jakarta.persistence.EntityManager;

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
            AdminsEntity admin = em.find(AdminsEntity.class, email);
            if (admin != null) {
                return HashPassword.check(password, admin.getPassword());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

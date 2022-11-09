package com.brief.java_simplon_clone_web_v.services;

import com.brief.java_simplon_clone_web_v.config.EntityManagerConfig;
import com.brief.java_simplon_clone_web_v.entities.BriefsEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BriefService {
    public boolean add(BriefsEntity brief) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            em.persist(brief);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(BriefsEntity brief) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            em.merge(brief);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(BriefsEntity brief) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            em.remove(brief);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BriefsEntity getBriefById(int id) {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            BriefsEntity brief = em.find(BriefsEntity.class, id);
            em.getTransaction().commit();
            return brief;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BriefsEntity> getAllBriefs() {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            List<BriefsEntity> briefs = em.createQuery("SELECT b FROM BriefsEntity b", BriefsEntity.class).getResultList();
            em.getTransaction().commit();
            return briefs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int count() {
        try {
            EntityManager em = EntityManagerConfig.getInstance().getEm();
            em.getTransaction().begin();
            int count = em.createQuery("SELECT COUNT(b) FROM BriefsEntity b", Long.class).getSingleResult().intValue();
            em.getTransaction().commit();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

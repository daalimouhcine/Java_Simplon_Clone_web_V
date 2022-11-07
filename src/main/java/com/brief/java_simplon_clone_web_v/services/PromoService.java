package com.brief.java_simplon_clone_web_v.services;

import com.brief.java_simplon_clone_web_v.config.EntityManagerConfig;
import com.brief.java_simplon_clone_web_v.entities.PromosEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PromoService {

        public boolean add(PromosEntity promo) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                em.persist(promo);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean update(PromosEntity promo) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                em.merge(promo);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean delete(PromosEntity promo) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                em.remove(promo);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public PromosEntity getPromoById(int id) {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                PromosEntity promo = em.find(PromosEntity.class, id);
                em.getTransaction().commit();
                return promo;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public List<PromosEntity> getAllPromos() {
            try {
                EntityManager em = EntityManagerConfig.getInstance().getEm();
                em.getTransaction().begin();
                List<PromosEntity> promos = em.createQuery("SELECT p FROM PromosEntity p", PromosEntity.class).getResultList();
                em.getTransaction().commit();
                return promos;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }




}

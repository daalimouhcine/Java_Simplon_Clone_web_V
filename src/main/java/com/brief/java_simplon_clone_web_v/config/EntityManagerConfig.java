package com.brief.java_simplon_clone_web_v.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerConfig {
    private EntityManagerFactory emf;
    private static EntityManagerConfig entityManagerConfigInstance = new EntityManagerConfig();

    private EntityManagerConfig() {
        emf = Persistence.createEntityManagerFactory("simplon_clone_web_v");
    }
    public EntityManagerFactory getEmf() {
        return emf;
    }
    public EntityManager getEm() {
        return emf.createEntityManager();
    }
    public static EntityManagerConfig getInstance() {
        return entityManagerConfigInstance;
    }

}

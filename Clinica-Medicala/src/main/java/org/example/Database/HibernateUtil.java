package org.example.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emf = buildEMF();

    private static EntityManagerFactory buildEMF(){
        try {
            return Persistence.createEntityManagerFactory("hibernate-jpa-unit");
        }catch (Exception ex){
            throw new ExceptionInInitializerError("Couldn't connect");
        }
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }



}

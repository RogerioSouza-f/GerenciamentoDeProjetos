package Persistence.Manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("MariaDB");

    public static EntityManager getEntityManager() {

        return emf.createEntityManager();
    }
}

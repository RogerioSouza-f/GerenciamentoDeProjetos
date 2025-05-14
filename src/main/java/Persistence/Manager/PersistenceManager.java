package Persistence.Manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("MariaDB");
    // rastreia todos os objetos de entidade dentro de um contexto de persistência
    public static EntityManager getEntityManager() {

        return emf.createEntityManager();
    }
}

import GerenciamentoDeProjeto.Model.Tarefas;
import Persistence.Manager.PersistenceManager;
import jakarta.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

        Tarefas tarefas = new Tarefas();
        tarefas.setNome("Kevyn");

        PersistenceManager pm = new PersistenceManager();

        EntityManager em = pm.getEntityManager();

        em.close();
    }
}

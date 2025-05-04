package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Tarefas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TarefasDao {

    private EntityManager entityManager;

    public TarefasDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarTarefa(Tarefas tarefa) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tarefa);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Tarefas buscarTarefaPorId(int id) {
        return entityManager.find(Tarefas.class, id);
    }

    public void atualizarTarefa(Tarefas tarefa) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(tarefa);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletarTarefa(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Tarefas tarefa = entityManager.find(Tarefas.class, id);
            if (tarefa != null) {
                entityManager.remove(tarefa);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Tarefas> buscarTodasTarefas() {
        TypedQuery<Tarefas> query = entityManager.createQuery("SELECT t FROM Tarefas t", Tarefas.class);
        return query.getResultList();
    }
}
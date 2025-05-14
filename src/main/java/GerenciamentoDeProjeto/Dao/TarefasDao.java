package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Tarefas;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TarefasDao {
    private EntityManager entityManager;

    public TarefasDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarTarefa(Tarefas tarefa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tarefa);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public List<Tarefas> buscarTodasTarefas(Long idProjeto) {
        return entityManager.createQuery(
            "SELECT t FROM Tarefas t WHERE t.projeto.idProjeto = :idProjeto",
            Tarefas.class)
            .setParameter("idProjeto", idProjeto)
            .getResultList();
    }

    public List<Tarefas> buscarTarefasDaEquipe(Long idEquipe) {
        return entityManager.createQuery(
            "SELECT t FROM Tarefas t WHERE t.equipe.idEquipe = :idEquipe",
            Tarefas.class)
            .setParameter("idEquipe", idEquipe)
            .getResultList();
    }

    public Tarefas buscarTarefaPorId(Long id) {
        return entityManager.find(Tarefas.class, id);
    }

    public void atualizarTarefa(Tarefas tarefa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tarefa);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void deletarTarefa(Long id) {
        try {
            entityManager.getTransaction().begin();
            Tarefas tarefa = entityManager.find(Tarefas.class, id);
            if (tarefa != null) {
                entityManager.remove(tarefa);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao excluir tarefa: " + e.getMessage(), e);
        }
    }
}
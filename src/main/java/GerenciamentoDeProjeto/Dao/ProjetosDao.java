package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Projetos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProjetosDao {

    private EntityManager entityManager;

    public ProjetosDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarProjeto(Projetos projeto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(projeto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Projetos buscarProjetoPorId(int id) {
        return entityManager.find(Projetos.class, id);
    }

    public void atualizarProjeto(Projetos projeto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(projeto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletarProjeto(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Projetos projeto = entityManager.find(Projetos.class, id);
            if (projeto != null) {
                entityManager.remove(projeto);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Projetos> buscarTodosProjetos() {
        TypedQuery<Projetos> query = entityManager.createQuery("SELECT p FROM Projetos p", Projetos.class);
        return query.getResultList();
    }
}
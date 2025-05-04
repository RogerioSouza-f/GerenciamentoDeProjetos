package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Membros;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MembrosDao {

    private EntityManager entityManager;

    public MembrosDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarMembro(Membros membro) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(membro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Membros buscarMembroPorId(int id) {
        return entityManager.find(Membros.class, id);
    }

    public void atualizarMembro(Membros membro) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(membro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletarMembro(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Membros membro = entityManager.find(Membros.class, id);
            if (membro != null) {
                entityManager.remove(membro);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Membros> buscarTodosMembros() {
        TypedQuery<Membros> query = entityManager.createQuery("SELECT m FROM Membros m", Membros.class);
        return query.getResultList();
    }
}
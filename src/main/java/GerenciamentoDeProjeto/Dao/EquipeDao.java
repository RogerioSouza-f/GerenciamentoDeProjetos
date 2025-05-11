package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Equipe;
import Persistence.Manager.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class EquipeDao {

    private EntityManager entityManager;

    public EquipeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarEquipe(Equipe equipe) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(equipe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Equipe buscarEquipePorId(int id) {
        return entityManager.find(Equipe.class, id);
    }

    public void atualizarEquipe(Equipe equipe) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(equipe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletarEquipe(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Equipe equipe = entityManager.find(Equipe.class, id);
            if (equipe != null) {
                entityManager.remove(equipe);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Equipe> buscarTodasEquipes() {
        TypedQuery<Equipe> query = entityManager.createQuery("SELECT e FROM Equipe e", Equipe.class);
        return query.getResultList();
    }
}

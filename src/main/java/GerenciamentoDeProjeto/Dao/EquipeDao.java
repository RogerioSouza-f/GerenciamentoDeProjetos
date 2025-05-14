package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Equipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EquipeDao {

    private EntityManager entityManager;

    public EquipeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Equipe> buscarTodasEquipes() {
        TypedQuery<Equipe> query = entityManager.createQuery("SELECT e FROM Equipe e", Equipe.class);
        return query.getResultList();
    }
}

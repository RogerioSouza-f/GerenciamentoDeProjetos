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
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(projeto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }


    public Projetos buscarProjetoPorId(long id) {
        return entityManager.find(Projetos.class, id);
    }

    public List<Projetos> buscarTodosProjetos() {
        TypedQuery<Projetos> query = entityManager.createQuery("SELECT p FROM Projetos p", Projetos.class);
        return query.getResultList();
    }

    public void atualizarProjeto(Projetos projeto) {
    try {
        entityManager.getTransaction().begin();
        entityManager.merge(projeto);
        entityManager.getTransaction().commit();
    } catch (Exception e) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        throw e;
    }
}

public void deletarProjeto(long id) {
    EntityTransaction transaction = entityManager.getTransaction();
    
    try {
        transaction.begin();

        entityManager.createQuery(
                "DELETE FROM Tarefas WHERE projeto.idProjeto = :projetoId")
                .setParameter("projetoId", id)
                .executeUpdate();

        // Busca e remove o projeto
        Projetos projeto = entityManager.find(Projetos.class, id);
        if (projeto != null) {
            entityManager.remove(projeto);
        }
        
        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw new RuntimeException("Erro ao excluir projeto: " + e.getMessage(), e);
    }
}

    public List<Projetos> buscarProjetosDoCliente(long idCliente) {
        try {
            TypedQuery<Projetos> query = entityManager.createQuery(
                    "SELECT p FROM Projetos p WHERE p.cliente.idCliente = :idCliente",
                    Projetos.class);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
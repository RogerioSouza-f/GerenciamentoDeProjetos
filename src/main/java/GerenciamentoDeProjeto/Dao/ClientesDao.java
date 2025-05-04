package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Clientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ClientesDao{

    private EntityManager entityManager;

    public ClientesDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarCliente(Clientes cliente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Clientes buscarporId(int id) {
        return entityManager.find(Clientes.class, id);
    }

    public void atualizarCliente(Clientes cliente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletetarCliente(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Clientes cliente = entityManager.find(Clientes.class, id);
            if (cliente != null) {
                entityManager.remove(cliente);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Clientes> buscartodosClientes() {
        TypedQuery<Clientes> query = entityManager.createQuery("SELECT c FROM Clientes c", Clientes.class);
        return query.getResultList();
    }
}
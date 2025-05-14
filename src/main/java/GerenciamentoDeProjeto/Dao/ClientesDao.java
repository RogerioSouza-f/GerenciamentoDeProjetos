package GerenciamentoDeProjeto.Dao;

import GerenciamentoDeProjeto.Model.Clientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ClientesDao {
    private EntityManager entityManager;

    public ClientesDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void criarCliente(Clientes cliente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Clientes buscarClientePorId(long id) {
        try {
            return entityManager.find(Clientes.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Clientes> buscartodosClientes() {
        try {
            TypedQuery<Clientes> query = entityManager.createQuery(
                "SELECT c FROM Clientes c", Clientes.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deletarCliente(long id) {
        try {
            entityManager.getTransaction().begin();
            Clientes cliente = entityManager.find(Clientes.class, id);
            if (cliente != null) {
                entityManager.remove(cliente);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }
}
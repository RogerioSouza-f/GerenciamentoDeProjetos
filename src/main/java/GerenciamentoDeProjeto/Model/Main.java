package GerenciamentoDeProjeto.Model;

import GerenciamentoDeProjeto.Dao.ClientesDao;
import GerenciamentoDeProjeto.Model.Clientes;
import Persistence.Manager.PersistenceManager;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager emf = PersistenceManager.getEntityManager();
        ClientesDao clientesDao = new ClientesDao(emf);

        try {


            // Criando o cliente
            Clientes cliente = new Clientes();
            cliente.setNome("João");
            cliente.setTelefone("11111111111");

            // Salvando no banco
            clientesDao.criarCliente(cliente);
            System.out.println("Cliente salvo com sucesso!");

            // Buscando o cliente
            Clientes clienteBuscado1 = clientesDao.buscarporId(1);
            System.out.println("Cliente encontrado: " + clienteBuscado1.getNome());

            Clientes clienteBuscado2 = clientesDao.buscarporId(2);
            System.out.println("Cliente encontrado: " + clienteBuscado2.getNome());


            //Só trocar pelo id no final
//            Clientes clienteParaDeletar = clientesDao.buscarporId(1);
//
//            if (clienteParaDeletar != null) {
//                // Deletar o cliente encontrado
//                clientesDao.deletetarCliente(clienteParaDeletar.getIdCliente());
//                System.out.println("Cliente " + clienteParaDeletar.getNome() + " deletado com sucesso!");
//            } else {
//                System.out.println("Cliente não encontrado!");
//            }




        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            emf.close();
        }
    }
}
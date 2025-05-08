package Swing;

import GerenciamentoDeProjeto.Dao.ClientesDao;
import GerenciamentoDeProjeto.Dao.EquipeDao;
import GerenciamentoDeProjeto.Dao.MembrosDao;
import GerenciamentoDeProjeto.Dao.ProjetosDao;
import Persistence.Manager.PersistenceManager;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class Swing extends JFrame{

    private EntityManager entityManager;
    private ClientesDao clientesDao;
    private ProjetosDao projetosDao;
    private EquipeDao equipeDao;
    private MembrosDao membrosDao;

    private JTable clientesTable;
    private JTable projetosTable;
    private JTable equipesTable;
    private JTable membrosTable;

    public Swing() {

        setTitle("Gerenciador de Projetos");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        entityManager = PersistenceManager.getEntityManager();
        clientesDao = new ClientesDao(entityManager);
        projetosDao = new ProjetosDao(entityManager);
        equipeDao = new EquipeDao(entityManager);
        membrosDao = new MembrosDao(entityManager);
        //teste

    }
}

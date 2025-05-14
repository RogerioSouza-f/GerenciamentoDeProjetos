package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Swing.View.*;
import GerenciamentoDeProjeto.Dao.*;
import GerenciamentoDeProjeto.Model.Clientes;
import Persistence.Manager.PersistenceManager;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

public class InicialController {
    private TelaInicial view;
    private EntityManager entityManager;

    public InicialController(TelaInicial view) {
        this.view = view;
        this.entityManager = PersistenceManager.getEntityManager();
        inicializarOuvintes();
    }

    private void inicializarOuvintes() {
        view.getBtnNovoCliente().addActionListener(e -> abrirTelaCadastroCliente());
        view.getBtnClienteExistente().addActionListener(e -> abrirTelaBuscaCliente());
        view.getBtnAdmin().addActionListener(e -> {
            TelaMenuAdministrador telaAdmin = new TelaMenuAdministrador();
            MenuAdministradorController controllerAdmin = new MenuAdministradorController(telaAdmin);
            telaAdmin.setVisible(true);
            view.dispose();
        });
        view.getBtnLider().addActionListener(e -> {
            TelaLider telaLider = new TelaLider();
            LiderController controller = new LiderController(telaLider); // Adicione esta linha
            telaLider.setVisible(true);
            view.dispose();
        });
    }

    private void abrirTelaCadastroCliente() {
        TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
        ClientesDao clientesDao = new ClientesDao(entityManager);
        CadastroClienteController controller = new CadastroClienteController(telaCadastroCliente, clientesDao);
        
        telaCadastroCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (controller.isClienteSalvo()) {
                    // Busca o último cliente cadastrado
                    List<Clientes> clientes = clientesDao.buscartodosClientes();
                    if (!clientes.isEmpty()) {
                        Clientes novoCliente = clientes.get(clientes.size() - 1);
                        abrirTelaCliente(novoCliente);
                    }
                }
            }
        });
        
        telaCadastroCliente.setVisible(true);
    }


    private void abrirTelaBuscaCliente() {
        TelaBuscaCliente telaBusca = new TelaBuscaCliente();
        ClientesDao clientesDao = new ClientesDao(entityManager);

        telaBusca.getBtnBuscar().addActionListener(e -> {
            String nome = telaBusca.getTxtNome().getText().trim();
            String telefone = telaBusca.getTxtTelefone().getText().trim();
            
            if (nome.isEmpty() && telefone.isEmpty()) {
                JOptionPane.showMessageDialog(telaBusca, 
                    "Por favor, preencha pelo menos um dos campos (Nome ou Telefone)!");
                return;
            }

            List<Clientes> clientes = clientesDao.buscartodosClientes();
            List<Clientes> clientesEncontrados = new ArrayList<>();

            for (Clientes cliente : clientes) {
                boolean matchNome = !nome.isEmpty() && 
                                  cliente.getNome().toLowerCase().contains(nome.toLowerCase());
                boolean matchTelefone = !telefone.isEmpty() && 
                                      cliente.getTelefone().equals(telefone);
                
                if (matchNome || matchTelefone) {
                    clientesEncontrados.add(cliente);
                }
            }

            if (!clientesEncontrados.isEmpty()) {
                if (clientesEncontrados.size() == 1) {
                    // Se encontrou apenas um cliente, abre direto
                    telaBusca.dispose();
                    abrirTelaCliente(clientesEncontrados.get(0));
                } else {
                    // Se encontrou mais de um, mostra lista para seleção
                    Clientes clienteSelecionado = selecionarCliente(clientesEncontrados, telaBusca);
                    if (clienteSelecionado != null) {
                        telaBusca.dispose();
                        abrirTelaCliente(clienteSelecionado);
                    }
                }
            } else {
                int opcao = JOptionPane.showConfirmDialog(telaBusca, 
                    "Cliente não encontrado! Deseja se cadastrar?",
                    "Cliente não encontrado",
                    JOptionPane.YES_NO_OPTION);
                
                if (opcao == JOptionPane.YES_OPTION) {
                    telaBusca.dispose();
                    abrirTelaCadastroCliente();
                }
            }
        });

        telaBusca.getBtnCancelar().addActionListener(e -> telaBusca.dispose());
        telaBusca.setVisible(true);
    }

    private Clientes selecionarCliente(List<Clientes> clientes, JFrame parentFrame) {
        String[] opcoes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            Clientes c = clientes.get(i);
            opcoes[i] = String.format("%s - Tel: %s", c.getNome(), c.getTelefone());
        }

        String selecao = (String) JOptionPane.showInputDialog(parentFrame,
            "Selecione o cliente:",
            "Múltiplos clientes encontrados",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]);

        if (selecao != null) {
            int index = -1;
            for (int i = 0; i < opcoes.length; i++) {
                if (opcoes[i].equals(selecao)) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                return clientes.get(index);
            }
        }
        return null;
    }

    private void abrirTelaCliente(Clientes cliente) {
        TelaCliente telaCliente = new TelaCliente();
        ProjetosDao projetosDao = new ProjetosDao(entityManager);
        ClientesDao clientesDao = new ClientesDao(entityManager);
        new ClienteController(telaCliente, clientesDao, projetosDao, cliente,view);
        telaCliente.setVisible(true);
        view.dispose();
    }
}
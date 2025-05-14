package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.ClientesDao;
import GerenciamentoDeProjeto.Dao.ProjetosDao;
import GerenciamentoDeProjeto.Model.Clientes;
import GerenciamentoDeProjeto.Model.Projetos;
import GerenciamentoDeProjeto.Swing.View.TelaGerenciarCliente;
import GerenciamentoDeProjeto.Swing.View.TelaMenuAdministrador;
import Persistence.Manager.PersistenceManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GerenciarClienteController {
    private TelaGerenciarCliente view;
    private TelaMenuAdministrador telaAnterior;
    private ClientesDao clientesDao;
    private ProjetosDao projetosDao;

    public GerenciarClienteController(TelaGerenciarCliente view, TelaMenuAdministrador telaAnterior) {
        this.view = view;
        this.telaAnterior = telaAnterior;
        this.clientesDao = new ClientesDao(PersistenceManager.getEntityManager());
        this.projetosDao = new ProjetosDao(PersistenceManager.getEntityManager());

        inicializarOuvintes();
        carregarClientes();
    }

    private void inicializarOuvintes() {
        view.getBtnCriar().addActionListener(e -> criarNovoCliente());
        view.getBtnExcluir().addActionListener(e -> excluirCliente());
        view.getBtnVoltar().addActionListener(e -> voltar());
    }

    private void carregarClientes() {
        DefaultTableModel modelo = view.getModeloTabela();
        modelo.setRowCount(0);

        List<Clientes> clientes = clientesDao.buscartodosClientes();
        for (Clientes cliente : clientes) {
            modelo.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getTelefone()
            });
        }
    }

private void criarNovoCliente() {
    String nome = view.getTxtNome().getText().trim();
    String telefone = view.getTxtTelefone().getText().trim();

    // Validações básicas
    if (nome.isEmpty()) {
        JOptionPane.showMessageDialog(view, 
            "O nome do cliente é obrigatório!", 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (telefone.isEmpty()) {
        JOptionPane.showMessageDialog(view, 
            "O telefone do cliente é obrigatório!", 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Clientes novoCliente = new Clientes();
        novoCliente.setNome(nome);
        novoCliente.setTelefone(telefone);

        // Salva o novo cliente
        clientesDao.criarCliente(novoCliente);

        // Limpa os campos
        view.getTxtNome().setText("");
        view.getTxtTelefone().setText("");

        // Atualiza a tabela
        carregarClientes();

        JOptionPane.showMessageDialog(view, 
            "Cliente cadastrado com sucesso!", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(view,
            "Erro ao cadastrar cliente: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
}

    private void excluirCliente() {
        int linhaSelecionada = view.getTabelaClientes().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um cliente para excluir.");
            return;
        }

        try {

            Object idObj = view.getTabelaClientes().getValueAt(linhaSelecionada, 0);
            long idCliente;
            
            if (idObj instanceof Integer) {
                idCliente = ((Integer) idObj).longValue();
            } else if (idObj instanceof Long) {
                idCliente = (Long) idObj;
            } else {
                idCliente = Long.parseLong(idObj.toString());
            }

            Clientes cliente = clientesDao.buscarClientePorId(idCliente);

            if (cliente != null) {
                // Verifica se o cliente possui projetos
                List<Projetos> projetos = projetosDao.buscarProjetosDoCliente(idCliente);
                
                if (!projetos.isEmpty()) {
                    int confirmacao = JOptionPane.showConfirmDialog(view,
                        "Este cliente possui " + projetos.size() + " projeto(s). Todos os projetos serão excluídos também.\n" +
                        "Deseja realmente excluir o cliente e seus projetos?",
                        "Confirmação de Exclusão",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                    if (confirmacao != JOptionPane.YES_OPTION) {
                        return;
                    }

                    // Exclui todos os projetos do cliente primeiro
                    for (Projetos projeto : projetos) {
                        projetosDao.deletarProjeto(projeto.getIdProjeto());
                    }
                }

                // Agora podemos excluir o cliente
                clientesDao.deletarCliente(idCliente);
                
                // Atualiza a tabela
                carregarClientes();
                
                JOptionPane.showMessageDialog(view, "Cliente excluído com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view,
                "Erro ao excluir cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltar() {
        view.dispose();
        telaAnterior.setVisible(true);
    }
}
package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.ClientesDao;
import GerenciamentoDeProjeto.Model.Clientes;
import GerenciamentoDeProjeto.Swing.View.TelaCadastroCliente;

import javax.swing.*;

public class CadastroClienteController {
    private TelaCadastroCliente view;
    private ClientesDao clientesDao;
    private boolean clienteSalvo = false;

    public CadastroClienteController(TelaCadastroCliente view, ClientesDao clientesDao) {
        this.view = view;
        this.clientesDao = clientesDao;
        inicializarBotoes();
    }

    private void inicializarBotoes() {
        view.getBtnSalvar().addActionListener(e -> salvarCliente());
        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void salvarCliente() {
        String nome = view.getTxtNome().getText().trim();
        String telefone = view.getTxtTelefone().getText().trim();

        if (nome.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos!");
            return;
        }

        try {
            Clientes cliente = new Clientes();
            cliente.setNome(nome);
            cliente.setTelefone(telefone);

            clientesDao.criarCliente(cliente);
            clienteSalvo = true; // Marca que o cliente foi salvo com sucesso
            JOptionPane.showMessageDialog(view, "Cliente cadastrado com sucesso!");
            view.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao cadastrar cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isClienteSalvo() {
        return clienteSalvo;
    }
}
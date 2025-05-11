package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Swing.View.*;
import javax.swing.*;

public class MenuAdministradorController {
    private TelaMenuAdministrador view;

    public MenuAdministradorController(TelaMenuAdministrador view) {
        this.view = view;
        inicializarOuvintes();
    }

    private void inicializarOuvintes() {
        view.getBtnGerenciarClientes().addActionListener(e -> abrirTelaGerenciarClientes());
        view.getBtnGerenciarProjetos().addActionListener(e -> abrirTelaGerenciarProjetos());
        view.getBtnSair().addActionListener(e -> sair());
    }

    private void abrirTelaGerenciarClientes() {
        try {
            TelaGerenciarCliente telaGerenciarCliente = new TelaGerenciarCliente();
            GerenciarClienteController controller = new GerenciarClienteController(telaGerenciarCliente, view);
            telaGerenciarCliente.setVisible(true);
            view.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Erro ao abrir tela de gerenciamento de clientes: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void abrirTelaGerenciarProjetos() {
        TelaGerenciarProjetos telaGerenciarProjetos = new TelaGerenciarProjetos();
        GerenciarProjetosController controller = new GerenciarProjetosController(telaGerenciarProjetos, view);
        telaGerenciarProjetos.setVisible(true);
        view.setVisible(false);
    }

    private void sair() {
        int confirmacao = JOptionPane.showConfirmDialog(view,
            "Deseja realmente voltar para a tela inicial?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            view.dispose();
            TelaInicial telaInicial = new TelaInicial();
            InicialController controller = new InicialController(telaInicial);
            telaInicial.setVisible(true);
        }
    }
}
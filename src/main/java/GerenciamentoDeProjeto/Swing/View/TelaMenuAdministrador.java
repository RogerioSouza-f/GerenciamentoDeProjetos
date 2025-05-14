package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import java.awt.*;

public class TelaMenuAdministrador extends JFrame {
    private JButton btnGerenciarClientes;
    private JButton btnGerenciarProjetos;
    private JButton btnSair;

    public TelaMenuAdministrador() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Gerenciamento - Menu Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);

        // Painel principal 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 220, 220)); // Cinza claro
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("Painel do Administrador");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnGerenciarClientes = criarBotao("Gerenciar Clientes");
        btnGerenciarProjetos = criarBotao("Gerenciar Projetos");
        btnSair = criarBotao("Sair");

        // Adiciona espaçamento entre componentes
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(btnGerenciarClientes);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(btnGerenciarProjetos);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(btnSair);

        // Adiciona o painel principal à janela
        add(mainPanel);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(250, 40));
        botao.setPreferredSize(new Dimension(250, 40));
        botao.setFont(new Font("Arial", Font.PLAIN, 14));
        return botao;
    }

    public JButton getBtnGerenciarClientes() {
        return btnGerenciarClientes;
    }

    public JButton getBtnGerenciarProjetos() {
        return btnGerenciarProjetos;
    }

    public JButton getBtnSair() {
        return btnSair;
    }
}
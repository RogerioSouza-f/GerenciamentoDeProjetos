
package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
        import java.awt.*;

public class TelaInicial extends JFrame {
    private JButton btnNovoCliente;
    private JButton btnClienteExistente;
    private JButton btnAdmin;
    private JButton btnLider;

    public TelaInicial() {
        setTitle("Sistema de Gerenciamento de Projetos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(150, 150, 150)); // Cinza médio

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Botões
        btnNovoCliente = new JButton("Novo Cliente");
        btnClienteExistente = new JButton("Já sou Cliente");
        btnAdmin = new JButton("Área do Administrador");
        btnLider = new JButton("Área do Líder");

        // Adiciona botões
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(btnNovoCliente, gbc);
        gbc.gridy = 1;
        mainPanel.add(btnClienteExistente, gbc);
        gbc.gridy = 2;
        mainPanel.add(btnAdmin, gbc);
        gbc.gridy = 3;
        mainPanel.add(btnLider, gbc);

        // Adiciona o painel principal ao JFrame
        add(mainPanel);

        // Configura a cor de fundo do JFrame para combinar com o painel
        getContentPane().setBackground(new Color(150, 150, 150)); // Cinza médio
    }

    public JButton getBtnNovoCliente() {
        return btnNovoCliente;
    }

    public JButton getBtnClienteExistente() {
        return btnClienteExistente;
    }

    public JButton getBtnAdmin() {
        return btnAdmin;
    }

    public JButton getBtnLider() {
        return btnLider;
    }
}
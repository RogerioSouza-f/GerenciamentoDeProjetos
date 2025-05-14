package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import java.awt.*;

public class TelaBuscaCliente extends JFrame {
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JButton btnBuscar;
    private JButton btnCancelar;

    public TelaBuscaCliente() {
        setTitle("Buscar Cliente");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtNome = new JTextField(20);
        mainPanel.add(txtNome, gbc);

        // Campo Telefone
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        mainPanel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtTelefone = new JTextField(15);
        mainPanel.add(txtTelefone, gbc);

        // Adiciona informação para o usuário
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel infoLabel = new JLabel("Preencha pelo menos um dos campos para buscar");
        infoLabel.setForeground(Color.BLACK);
        mainPanel.add(infoLabel, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBuscar = new JButton("Buscar");
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnCancelar);

        // Adiciona os painéis à janela
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtTelefone() { return txtTelefone; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
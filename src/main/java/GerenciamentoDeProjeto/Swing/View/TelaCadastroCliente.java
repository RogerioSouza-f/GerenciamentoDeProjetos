package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class TelaCadastroCliente extends JFrame {
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroCliente() {
        setTitle("Cadastro de Cliente");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Painel com imagem de fundo
        ImageIcon imagemIcon = new ImageIcon("src/main/resources/img.jpg");//aqui <-
        Image imagem = imagemIcon.getImage();
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(150, 150, 150)); // Cinza claro
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
        txtTelefone = new JTextField(20);
        mainPanel.add(txtTelefone, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

//        // Adiciona os painéis à janela
//        setLayout(new BorderLayout());
//        add(mainPanel, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona os painéis ao backgroundPanel
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o backgroundPanel à janela
        add(backgroundPanel);

    }

    // Getters
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtTelefone() { return txtTelefone; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
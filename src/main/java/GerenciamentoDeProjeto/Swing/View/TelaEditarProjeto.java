package GerenciamentoDeProjeto.Swing.View;

import GerenciamentoDeProjeto.Model.Projetos;

import javax.swing.*;
import java.awt.*;

public class TelaEditarProjeto extends JDialog {
    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private boolean confirmou = false;
    private Projetos projeto;

    public TelaEditarProjeto(Frame parent, Projetos projeto) {
        super(parent, "Editar Projeto", true);
        this.projeto = projeto;
        initComponents();
        preencherCampos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtNome = new JTextField(20);
        mainPanel.add(txtNome, gbc);

        // Descrição
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Descrição:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        txtDescricao = new JTextArea(5, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtDescricao);
        mainPanel.add(scrollPane, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnSalvar = new JButton("Salvar");
        btnVoltar = new JButton("Voltar");
        
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnVoltar);

        // Adiciona os painéis à janela
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ouvintes
        btnSalvar.addActionListener(e -> salvar());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void preencherCampos() {
        txtNome.setText(projeto.getNome());
        txtDescricao.setText(projeto.getDescricao());
    }

    private void salvar() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do projeto não pode estar vazio!");
            return;
        }

        projeto.setNome(txtNome.getText().trim());
        projeto.setDescricao(txtDescricao.getText().trim());
        confirmou = true;
        dispose();
    }

    public boolean isConfirmou() {
        return confirmou;
    }

    public Projetos getProjeto() {
        return projeto;
    }
}
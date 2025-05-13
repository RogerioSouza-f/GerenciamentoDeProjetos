package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaCliente extends JFrame {
    private JTextField txtNome;
    private JTextField txtDescricao;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnVoltar;
    private JTable tblProjetos;
    private DefaultTableModel tableModel;

    public TelaCliente() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Projetos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(220, 220, 220)); // Cinza claro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcc = new GridBagConstraints();
        gbcc.insets = new Insets(5, 5, 5, 5);

        // Campos de texto
        gbcc.gridx = 0; gbcc.gridy = 0;
        formPanel.add(new JLabel("Nome:"), gbcc);

        gbcc.gridx = 1; gbcc.gridy = 0;
        txtNome = new JTextField(30);
        formPanel.add(txtNome, gbcc);

        gbcc.gridx = 0; gbcc.gridy = 1;
        formPanel.add(new JLabel("Descrição:"), gbcc);

        gbcc.gridx = 1; gbcc.gridy = 1;
        txtDescricao = new JTextField(30);
        formPanel.add(txtDescricao, gbcc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnVoltar = new JButton("Voltar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnVoltar);



        String[] colunas = {"ID", "Nome", "Descrição", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblProjetos = new JTable(tableModel);

        // Esconde a coluna do ID
        tblProjetos.getColumnModel().getColumn(0).setMinWidth(0);
        tblProjetos.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProjetos.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(tblProjetos);

        // Adiciona os componentes ao painel principal
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        add(mainPanel);
    }

    // Getters para os componentes
    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtDescricao() {
        return txtDescricao;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JTable getTblProjetos() {
        return tblProjetos;
    }
    public JButton getBtnVoltar() {
        return btnVoltar;
    }

}
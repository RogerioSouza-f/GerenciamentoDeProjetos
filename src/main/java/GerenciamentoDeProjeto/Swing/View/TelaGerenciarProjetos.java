package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class TelaGerenciarProjetos extends JFrame {
    private JTable tabelaProjetos;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnVoltar;
    private DefaultTableModel modeloTabela;
    private JButton btnGerenciarTarefas;


    public TelaGerenciarProjetos() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Projetos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

//        // Painel principal
//        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0, 0, 0)); // Cinza claro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("Gerenciamento de Projetos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"ID", "Nome do Projeto", "Descrição", "Status", "Equipe"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProjetos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProjetos);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnNovo = new JButton("Novo Projeto");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnVoltar = new JButton("Voltar");

        botoesPanel.add(btnNovo);
        botoesPanel.add(btnEditar);
        botoesPanel.add(btnExcluir);
        botoesPanel.add(btnVoltar);

        mainPanel.add(botoesPanel, BorderLayout.SOUTH);

        // Adicionar novo botão no painel de botões
        btnGerenciarTarefas = new JButton("Gerenciar Tarefas");
        botoesPanel.add(btnGerenciarTarefas);


        // Adiciona o painel principal à janela
        add(mainPanel);
    }

    // Getters para os componentes
    public JTable getTabelaProjetos() {
        return tabelaProjetos;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnVoltar() {
        return btnVoltar;
    }

    public DefaultTableModel getModeloTabela() {
        return modeloTabela;
    }
    public JButton getBtnGerenciarTarefas() {
        return btnGerenciarTarefas;
    }

}
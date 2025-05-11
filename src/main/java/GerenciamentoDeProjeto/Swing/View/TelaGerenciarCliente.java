package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaGerenciarCliente extends JFrame {
    private JTable tabelaClientes;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JButton btnCriar;
    private JButton btnExcluir;
    private JButton btnVoltar;

    public TelaGerenciarCliente() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciar Clientes");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel para os campos de cadastro
        JPanel painelCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Novo Cliente"));

        // Campos de texto
        painelCadastro.add(new JLabel("Nome:"));
        txtNome = new JTextField(20);
        painelCadastro.add(txtNome);

        painelCadastro.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField(15);
        painelCadastro.add(txtTelefone);

        // Botão criar no painel de cadastro
        btnCriar = new JButton("Criar Cliente");
        painelCadastro.add(btnCriar);

        // Criação da tabela
        String[] colunas = {"ID", "Nome", "Telefone"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaClientes = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);

        // Painel de botões inferiores
        JPanel painelBotoes = new JPanel();
        btnExcluir = new JButton("Excluir Cliente");
        btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVoltar);

        // Layout
        setLayout(new BorderLayout());
        add(painelCadastro, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // Getters
    public JTable getTabelaClientes() { return tabelaClientes; }
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtTelefone() { return txtTelefone; }
    public JButton getBtnCriar() { return btnCriar; }
    public JButton getBtnExcluir() { return btnExcluir; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public DefaultTableModel getModeloTabela() { 
        return (DefaultTableModel) tabelaClientes.getModel(); 
    }
}
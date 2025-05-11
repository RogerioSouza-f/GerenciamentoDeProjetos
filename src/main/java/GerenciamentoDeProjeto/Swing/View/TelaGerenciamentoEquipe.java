package GerenciamentoDeProjeto.Swing.View;

import GerenciamentoDeProjeto.Model.Equipe;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class TelaGerenciamentoEquipe extends JFrame {
    private JTable tabelaTarefas;
    private DefaultTableModel modeloTabela;
    private JButton btnVoltar;
    private JButton btnAtualizarStatus;
    private JLabel lblEquipe;
    private JComboBox<String> comboStatus;
    
    public TelaGerenciamentoEquipe(Equipe equipe) {
        setTitle("Gerenciamento de Equipe - " + equipe.getNomeEquipe());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Cabeçalho com nome da equipe
        lblEquipe = new JLabel("Equipe: " + equipe.getNomeEquipe());
        lblEquipe.setFont(new Font("Arial", Font.BOLD, 24));
        lblEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblEquipe, BorderLayout.NORTH);
        
        // Tabela de tarefas
        String[] colunas = {"ID", "Tarefa", "Descrição", "Status", "Projeto"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaTarefas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaTarefas);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Painel de controles
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        // ComboBox para status
        comboStatus = new JComboBox<>(new String[]{
            "Pendente",
            "Em Andamento",
            "Em Revisão",
            "Concluída",
            "Bloqueada"
        });
        
        // Botão atualizar status
        btnAtualizarStatus = new JButton("Atualizar Status");
        
        controlPanel.add(new JLabel("Novo Status:"));
        controlPanel.add(comboStatus);
        controlPanel.add(btnAtualizarStatus);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnVoltar);
        
        // Painel inferior combinando controles e botão voltar
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(controlPanel, BorderLayout.CENTER);
        bottomPanel.add(painelBotoes, BorderLayout.EAST);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    // Getters
    public JTable getTabelaTarefas() {
        return tabelaTarefas;
    }
    
    public DefaultTableModel getModeloTabela() {
        return modeloTabela;
    }
    
    public JButton getBtnVoltar() {
        return btnVoltar;
    }
    
    public JButton getBtnAtualizarStatus() {
        return btnAtualizarStatus;
    }
    
    public JComboBox<String> getComboStatus() {
        return comboStatus;
    }
}
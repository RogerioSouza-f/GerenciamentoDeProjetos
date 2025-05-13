package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import java.awt.*;

public class TelaProjetosAdmin extends JFrame {
    private JList<String> listaProjetos;
    private JTextArea txtDetalheProjeto;
    private JButton btnVoltar;
    private JButton btnNovoProjeto;
    private JButton btnEditarProjeto;
    private JButton btnExcluirProjeto;

    public TelaProjetosAdmin() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Projetos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBackground(new Color(220, 220, 220)); // Cinza claro
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel esquerdo com lista de projetos
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Projetos"));
        
        listaProjetos = new JList<>();
        JScrollPane scrollProjetos = new JScrollPane(listaProjetos);
        leftPanel.add(scrollProjetos, BorderLayout.CENTER);

        // Botões de ação para projetos
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnNovoProjeto = new JButton("Novo Projeto");
        btnEditarProjeto = new JButton("Editar");
        btnExcluirProjeto = new JButton("Excluir");
        
        botoesPanel.add(btnNovoProjeto);
        botoesPanel.add(btnEditarProjeto);
        botoesPanel.add(btnExcluirProjeto);
        leftPanel.add(botoesPanel, BorderLayout.SOUTH);

        // Painel direito com detalhes do projeto
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Detalhes do Projeto"));
        
        txtDetalheProjeto = new JTextArea();
        txtDetalheProjeto.setEditable(false);
        JScrollPane scrollDetalhes = new JScrollPane(txtDetalheProjeto);
        rightPanel.add(scrollDetalhes, BorderLayout.CENTER);

        // Botão voltar no painel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVoltar = new JButton("Voltar ao Menu");
        bottomPanel.add(btnVoltar);

        // Adiciona os painéis ao painel principal
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        add(mainPanel);
    }

    // Getters
    public JList<String> getListaProjetos() {
        return listaProjetos;
    }

    public JTextArea getTxtDetalheProjeto() {
        return txtDetalheProjeto;
    }

    public JButton getBtnVoltar() {
        return btnVoltar;
    }

    public JButton getBtnNovoProjeto() {
        return btnNovoProjeto;
    }

    public JButton getBtnEditarProjeto() {
        return btnEditarProjeto;
    }

    public JButton getBtnExcluirProjeto() {
        return btnExcluirProjeto;
    }
}
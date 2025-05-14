package GerenciamentoDeProjeto.Swing.View;

import javax.swing.*;
import java.awt.*;

public class TelaLider extends JFrame {
    private JPanel painelEquipes;
    private JButton btnVoltar;
    
    public TelaLider() {
        setTitle("Área do Líder - Seleção de Equipe");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(220, 220, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Selecione uma Equipe");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitulo, BorderLayout.NORTH);
        
        // Painel para os botões das equipes com GridLayout
        painelEquipes = new JPanel(new GridLayout(0, 2, 10, 10));
        painelEquipes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Adiciona o painel de equipes em um JScrollPane
        JScrollPane scrollPane = new JScrollPane(painelEquipes);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Painel inferior com botão voltar
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVoltar = new JButton("Voltar");
        bottomPanel.add(btnVoltar);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    public JPanel getPainelEquipes() {
        return painelEquipes;
    }
    
    public JButton getBtnVoltar() {
        return btnVoltar;
    }
    
    // Método auxiliar para criar botões de equipe com estilo consistente
    public JButton criarBotaoEquipe(String nomeEquipe) {
        JButton btn = new JButton(nomeEquipe);
        btn.setPreferredSize(new Dimension(200, 80));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        return btn;
    }
}
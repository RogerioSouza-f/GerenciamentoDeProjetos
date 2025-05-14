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


        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        
        // Botões
        btnNovoCliente = new JButton("Novo Cliente");
        btnClienteExistente = new JButton("Já sou Cliente");
        btnAdmin = new JButton("Área do Administrador");
        btnLider = new JButton("Área do Líder");
        
        // Adiciona botões
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnNovoCliente, gbc);
        
        gbc.gridy = 1;
        add(btnClienteExistente, gbc);
        
        gbc.gridy = 2;
        add(btnAdmin, gbc);
        
        gbc.gridy = 3;
        add(btnLider, gbc);
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
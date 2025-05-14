package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.EquipeDao;
import GerenciamentoDeProjeto.Model.Equipe;
import GerenciamentoDeProjeto.Swing.View.TelaGerenciamentoEquipe;
import GerenciamentoDeProjeto.Swing.View.TelaLider;
import GerenciamentoDeProjeto.Swing.View.TelaInicial;
import Persistence.Manager.PersistenceManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LiderController {
    private TelaLider view;
    private EquipeDao equipeDao;
    
    public LiderController(TelaLider view) {
        this.view = view;
        this.equipeDao = new EquipeDao(PersistenceManager.getEntityManager());
        inicializarOuvintes();
        carregarEquipes();
    }
    
    private void inicializarOuvintes() {
        view.getBtnVoltar().addActionListener(e -> voltar());
    }
    
    private void carregarEquipes() {
        List<Equipe> equipes = equipeDao.buscarTodasEquipes();
        
        if (equipes.isEmpty()) {
            JLabel lblSemEquipes = new JLabel("Nenhuma equipe encontrada", SwingConstants.CENTER);
            lblSemEquipes.setFont(new Font("Arial", Font.BOLD, 16));
            view.getPainelEquipes().add(lblSemEquipes);
        } else {
            for (Equipe equipe : equipes) {
                JButton btnEquipe = view.criarBotaoEquipe(equipe.getNomeEquipe());
                btnEquipe.addActionListener(e -> abrirGerenciamentoEquipe(equipe));
                view.getPainelEquipes().add(btnEquipe);
            }
        }
    }
    
    private void abrirGerenciamentoEquipe(Equipe equipe) {
        TelaGerenciamentoEquipe telaGerenciamento = new TelaGerenciamentoEquipe(equipe);
        GerenciamentoEquipeController controller = new GerenciamentoEquipeController(telaGerenciamento, equipe);
        telaGerenciamento.setVisible(true);
        view.setVisible(false);
    }
    
    private void voltar() {
        view.dispose();
        TelaInicial telaInicial = new TelaInicial();
        InicialController controller = new InicialController(telaInicial);
        telaInicial.setVisible(true);
    }
}
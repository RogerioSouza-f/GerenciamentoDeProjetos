package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.TarefasDao;
import GerenciamentoDeProjeto.Dao.ProjetosDao;
import GerenciamentoDeProjeto.Model.Equipe;
import GerenciamentoDeProjeto.Model.Tarefas;
import GerenciamentoDeProjeto.Model.Projetos;
import GerenciamentoDeProjeto.Swing.View.TelaGerenciamentoEquipe;
import GerenciamentoDeProjeto.Swing.View.TelaLider;
import Persistence.Manager.PersistenceManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GerenciamentoEquipeController {
    private TelaGerenciamentoEquipe view;
    private Equipe equipe;
    private TarefasDao tarefasDao;
    private ProjetosDao projetosDao;
    
    public GerenciamentoEquipeController(TelaGerenciamentoEquipe view, Equipe equipe) {
        this.view = view;
        this.equipe = equipe;
        this.tarefasDao = new TarefasDao(PersistenceManager.getEntityManager());
        this.projetosDao = new ProjetosDao(PersistenceManager.getEntityManager());
        
        inicializarOuvintes();
        carregarTarefas();
    }
    
    private void inicializarOuvintes() {
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.getBtnAtualizarStatus().addActionListener(e -> atualizarStatusTarefa());
    }
    
    private void carregarTarefas() {
        DefaultTableModel modelo = view.getModeloTabela();
        modelo.setRowCount(0);
        
        List<Tarefas> tarefas = tarefasDao.buscarTarefasDaEquipe(equipe.getIdEquipe());
        
        for (Tarefas tarefa : tarefas) {
            modelo.addRow(new Object[]{
                tarefa.getIdTarefas(),
                tarefa.getNome(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getProjeto().getNome()
            });
        }
    }
    
    private void atualizarStatusTarefa() {
        int linhaSelecionada = view.getTabelaTarefas().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view,
                "Por favor, selecione uma tarefa para atualizar o status.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Object idObj = view.getTabelaTarefas().getValueAt(linhaSelecionada, 0);
            Long idTarefa;
            
            if (idObj instanceof Integer) {
                idTarefa = Long.valueOf((Integer) idObj);
            } else if (idObj instanceof Long) {
                idTarefa = (Long) idObj;
            } else {
                idTarefa = Long.valueOf(idObj.toString());
            }
            
            String novoStatus = (String) view.getComboStatus().getSelectedItem();
            
            Tarefas tarefa = tarefasDao.buscarTarefaPorId(idTarefa);
            if (tarefa != null) {
                // Atualiza o status da tarefa
                tarefa.setStatus(novoStatus);
                tarefasDao.atualizarTarefa(tarefa);
                
                // Atualiza a tabela
                view.getTabelaTarefas().setValueAt(novoStatus, linhaSelecionada, 3);
                
                // Verifica e atualiza o status do projeto se necessário
                verificarEAtualizarStatusProjeto(tarefa.getProjeto());
                
                JOptionPane.showMessageDialog(view,
                    "Status da tarefa atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Erro ao atualizar status da tarefa: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void verificarEAtualizarStatusProjeto(Projetos projeto) {
        List<Tarefas> tarefasProjeto = tarefasDao.buscarTodasTarefas(projeto.getIdProjeto());
        boolean todasConcluidas = true;
        
        for (Tarefas tarefa : tarefasProjeto) {
            if (!"Concluída".equals(tarefa.getStatus())) {
                todasConcluidas = false;
                break;
            }
        }
        
        if (todasConcluidas && !"Concluído".equals(projeto.getStatus())) {
            projeto.setStatus("Concluído");
            projetosDao.atualizarProjeto(projeto);
        }
    }
    
    private void voltar() {
        view.dispose();
        TelaLider telaLider = new TelaLider();
        LiderController controller = new LiderController(telaLider);
        telaLider.setVisible(true);
    }
}
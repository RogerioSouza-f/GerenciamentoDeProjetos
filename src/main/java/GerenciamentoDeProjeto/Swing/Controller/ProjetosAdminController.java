package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.ProjetosDao;
import GerenciamentoDeProjeto.Model.Projetos;
import GerenciamentoDeProjeto.Swing.View.TelaProjetosAdmin;
import GerenciamentoDeProjeto.Swing.View.TelaMenuAdministrador;
import Persistence.Manager.PersistenceManager;

import javax.swing.*;
import javax.swing.DefaultListModel;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProjetosAdminController {
    private TelaProjetosAdmin view;
    private TelaMenuAdministrador telaAnterior;
    private ProjetosDao projetosDao;
    private EntityManager entityManager;

    public ProjetosAdminController(TelaProjetosAdmin view, TelaMenuAdministrador telaAnterior) {
        this.view = view;
        this.telaAnterior = telaAnterior;
        this.entityManager = PersistenceManager.getEntityManager();
        this.projetosDao = new ProjetosDao(entityManager);
        
        inicializarOuvintes();
        carregarProjetos();
    }

    private void inicializarOuvintes() {
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.getBtnNovoProjeto().addActionListener(e -> novoProjeto());
        view.getBtnEditarProjeto().addActionListener(e -> editarProjeto());
        view.getBtnExcluirProjeto().addActionListener(e -> excluirProjeto());
        
        view.getListaProjetos().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarDetalheProjeto();
            }
        });
    }

    private void carregarProjetos() {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<Projetos> projetos = projetosDao.buscarTodosProjetos();
        
        for (Projetos projeto : projetos) {
            model.addElement(projeto.getNome());
        }
        
        view.getListaProjetos().setModel(model);
    }

    private void atualizarDetalheProjeto() {
        String projetoNome = view.getListaProjetos().getSelectedValue();
        if (projetoNome != null) {
            List<Projetos> projetos = projetosDao.buscarTodosProjetos();
            for (Projetos projeto : projetos) {
                if (projeto.getNome().equals(projetoNome)) {
                    StringBuilder detalhes = new StringBuilder();
                    detalhes.append("Nome: ").append(projeto.getNome()).append("\n");
                    detalhes.append("Cliente: ").append(projeto.getCliente().getNome()).append("\n");
                    detalhes.append("Descrição: ").append(projeto.getDescricao()).append("\n");
                    
                    view.getTxtDetalheProjeto().setText(detalhes.toString());
                    break;
                }
            }
        }
    }

    private void novoProjeto() {
        // Por enquanto mantemos a mensagem de desenvolvimento
        JOptionPane.showMessageDialog(view, 
            "Funcionalidade de Novo Projeto em desenvolvimento",
            "Em Desenvolvimento",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarProjeto() {
        if (view.getListaProjetos().getSelectedValue() == null) {
            JOptionPane.showMessageDialog(view, 
                "Por favor, selecione um projeto para editar",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(view, 
            "Funcionalidade de Edição de Projeto em desenvolvimento",
            "Em Desenvolvimento",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void excluirProjeto() {
        String projetoNome = view.getListaProjetos().getSelectedValue();
        if (projetoNome == null) {
            JOptionPane.showMessageDialog(view, 
                "Por favor, selecione um projeto para excluir",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(view,
            "Tem certeza que deseja excluir este projeto?",
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacao == JOptionPane.YES_OPTION) {
            List<Projetos> projetos = projetosDao.buscarTodosProjetos();
            for (Projetos projeto : projetos) {
                if (projeto.getNome().equals(projetoNome)) {
                    try {
                        projetosDao.deletarProjeto(projeto.getIdProjeto());
                        carregarProjetos();
                        view.getTxtDetalheProjeto().setText("");
                        JOptionPane.showMessageDialog(view,
                            "Projeto excluído com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(view,
                            "Erro ao excluir projeto: " + e.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }
    }

    private void voltar() {
        view.dispose();
        telaAnterior.setVisible(true);
    }
}
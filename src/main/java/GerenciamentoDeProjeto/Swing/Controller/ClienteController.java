package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.ClientesDao;
import GerenciamentoDeProjeto.Dao.ProjetosDao;
import GerenciamentoDeProjeto.Model.Projetos;
import GerenciamentoDeProjeto.Model.Clientes;
import GerenciamentoDeProjeto.Swing.View.TelaCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.Frame;
import GerenciamentoDeProjeto.Swing.View.TelaEditarProjeto;
import GerenciamentoDeProjeto.Swing.View.TelaInicial;

import javax.swing.SwingUtilities;

public class ClienteController {
    private TelaCliente view;
    private ProjetosDao projetosDao;
    private ClientesDao clientesDao;
    private Clientes clienteAtual;
    private TelaInicial telaAnterior;

    public ClienteController(TelaCliente view, ClientesDao clientesDao, ProjetosDao projetosDao,Clientes cliente, TelaInicial telaAnterior) {
        this.view = view;
        this.projetosDao = projetosDao;
        this.clienteAtual = cliente;
        this.telaAnterior = telaAnterior;

        inicializarOuvintes();
        atualizarListaProjetos();
    }

    private void inicializarOuvintes() {
        view.getBtnSalvar().addActionListener(e -> salvarProjeto());
        view.getBtnExcluir().addActionListener(e -> excluirProjeto());
        view.getBtnEditar().addActionListener(e -> editarProjeto());
        view.getBtnVoltar().addActionListener(e -> voltar());
    }



    private void atualizarListaProjetos() {
        try {
            List<Projetos> todosProjetos = projetosDao.buscarTodosProjetos();
            DefaultTableModel modelo = (DefaultTableModel) view.getTblProjetos().getModel();
            modelo.setRowCount(0);

            for (Projetos projeto : todosProjetos) {
                if (projeto.getCliente().getIdCliente() == clienteAtual.getIdCliente()) {
                    modelo.addRow(new Object[]{
                    projeto.getIdProjeto(),
                    projeto.getNome(),
                    projeto.getDescricao(),
                    projeto.getStatus()
                });
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Erro ao atualizar lista de projetos: " + e.getMessage());
    }
}


    private void salvarProjeto() {
        if (clienteAtual == null) {
            JOptionPane.showMessageDialog(view, "Erro: Cliente não encontrado!");
            return;
        }

        String nome = view.getTxtNome().getText().trim();
        String descricao = view.getTxtDescricao().getText().trim();

        if (nome.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos!");
            return;
        }

        try {
            Projetos projeto = new Projetos();
            projeto.setNome(nome);
            projeto.setDescricao(descricao);
            projeto.setCliente(clienteAtual);
            projeto.setStatus("Aguardando");

            projetosDao.criarProjeto(projeto);
            atualizarListaProjetos();
            JOptionPane.showMessageDialog(view, "Projeto salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void editarProjeto() {
        int linhaSelecionada = view.getTblProjetos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um projeto para editar!");
            return;
        }

        try {
            Long idProjeto = (Long) view.getTblProjetos().getValueAt(linhaSelecionada, 0);
            Projetos projeto = projetosDao.buscarProjetoPorId(idProjeto);

            TelaEditarProjeto telaEditar = new TelaEditarProjeto(
                    (Frame) SwingUtilities.getWindowAncestor(view),
                    projeto
            );
            telaEditar.setVisible(true);

            if (telaEditar.isConfirmou()) {
                projetosDao.atualizarProjeto(telaEditar.getProjeto());
                atualizarListaProjetos();
                JOptionPane.showMessageDialog(view, "Projeto atualizado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao editar projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void excluirProjeto() {
        int linhaSelecionada = view.getTblProjetos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um projeto para excluir!");
            return;
        }

        try {
            Long idProjeto = (Long) view.getTblProjetos().getValueAt(linhaSelecionada, 0);

            int confirmacao = JOptionPane.showConfirmDialog(view,
                    "Tem certeza que deseja excluir este projeto?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                projetosDao.deletarProjeto(idProjeto);
                atualizarListaProjetos();
                JOptionPane.showMessageDialog(view, "Projeto excluído com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao excluir projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void voltar() {
        view.dispose();
        telaAnterior.setVisible(true);
    }

}
package GerenciamentoDeProjeto.Swing.Controller;

import GerenciamentoDeProjeto.Dao.ProjetosDao;
import GerenciamentoDeProjeto.Dao.EquipeDao;
import GerenciamentoDeProjeto.Model.Projetos;
import GerenciamentoDeProjeto.Model.Equipe;
import GerenciamentoDeProjeto.Dao.TarefasDao;
import GerenciamentoDeProjeto.Swing.View.TelaGerenciarProjetos;
import GerenciamentoDeProjeto.Swing.View.TelaMenuAdministrador;
import Persistence.Manager.PersistenceManager;
import GerenciamentoDeProjeto.Model.Membros;
import GerenciamentoDeProjeto.Model.Tarefas;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GerenciarProjetosController {
    // Atributos para gerenciar a interface e acesso a dados
    private TelaGerenciarProjetos view;
    private TelaMenuAdministrador telaAnterior;
    private ProjetosDao projetosDao;
    private EquipeDao equipeDao;
    private TarefasDao tarefasDao;

    // Inicializa o controlador com suas dependências
    public GerenciarProjetosController(TelaGerenciarProjetos view, TelaMenuAdministrador telaAnterior) {
        this.view = view;
        this.telaAnterior = telaAnterior;
        this.projetosDao = new ProjetosDao(PersistenceManager.getEntityManager());
        this.equipeDao = new EquipeDao(PersistenceManager.getEntityManager());
        this.tarefasDao = new TarefasDao(PersistenceManager.getEntityManager());

        inicializarOuvintes();
        carregarProjetos();
    }

    // Configura os ouvintes dos botões
    private void inicializarOuvintes() {
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.getBtnEditar().addActionListener(e -> editarProjeto());
        view.getBtnExcluir().addActionListener(e -> excluirProjeto());
        view.getBtnGerenciarTarefas().addActionListener(e -> gerenciarTarefas());
    }

    // Interface para gerenciar tarefas do projeto
    private void gerenciarTarefas() {
        int linhaSelecionada = view.getTabelaProjetos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um projeto primeiro.");
            return;
        }

        try {
            Long idProjeto = (Long) view.getTabelaProjetos().getValueAt(linhaSelecionada, 0);
            Projetos projeto = projetosDao.buscarProjetoPorId(idProjeto);

            if (projeto == null) {
                JOptionPane.showMessageDialog(view, "Erro: Projeto não encontrado.");
                return;
            }

            if (projeto.getEquipe() == null) {
                JOptionPane.showMessageDialog(view,
                    "Este projeto ainda não tem uma equipe designada. Por favor, designe uma equipe primeiro.");
                return;
            }

            List<Tarefas> tarefasProjeto = tarefasDao.buscarTodasTarefas(idProjeto);

            // Cria diálogo para gerenciar tarefas
            JDialog dialogTarefas = new JDialog(view, "Gerenciar Tarefas do Projeto", true);
            dialogTarefas.setLayout(new BorderLayout());
            dialogTarefas.setSize(500, 400);
            dialogTarefas.setLocationRelativeTo(view);

            DefaultListModel<String> modeloLista = new DefaultListModel<>();
            JList<String> listaTarefas = new JList<>(modeloLista);

            // Preenche a lista de tarefas
            for (Tarefas tarefa : tarefasProjeto) {
                modeloLista.addElement(String.format("Tarefa: %s - Status: %s - Equipe: %s",
                    tarefa.getNome(),
                    tarefa.getStatus(),
                    projeto.getEquipe().getNomeEquipe()));
            }

            // Painel de botões
            JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton btnNovaTarefa = new JButton("Nova Tarefa");
            btnNovaTarefa.addActionListener(e -> {
                adicionarTarefa(projeto, modeloLista);
                // Atualiza a lista após adicionar
                tarefasProjeto.clear();
                tarefasProjeto.addAll(tarefasDao.buscarTodasTarefas(idProjeto));
            });
            painelBotoes.add(btnNovaTarefa);

            dialogTarefas.add(new JScrollPane(listaTarefas), BorderLayout.CENTER);
            dialogTarefas.add(painelBotoes, BorderLayout.SOUTH);

            dialogTarefas.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao gerenciar tarefas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Atribui membros da equipe a uma tarefa
    private void atribuirMembrosATarefa(Tarefas tarefa, Equipe equipe) {
        if (equipe == null || tarefa == null) {
            JOptionPane.showMessageDialog(view, "Erro: Equipe ou tarefa inválida.");
            return;
        }

        List<Membros> membrosEquipe = equipe.getMembros();
        if (membrosEquipe == null || membrosEquipe.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Esta equipe não possui membros.");
            return;
        }

        JDialog dialogMembros = new JDialog(view, "Selecionar Membros", true);
        dialogMembros.setLayout(new BorderLayout());
        dialogMembros.setSize(400, 300);
        dialogMembros.setLocationRelativeTo(view);

        DefaultListModel<String> modeloMembros = new DefaultListModel<>();
        JList<String> listaMembros = new JList<>(modeloMembros);
        listaMembros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        for (Membros membro : membrosEquipe) {
            modeloMembros.addElement(membro.getNome());
        }

        JButton btnConfirmar = new JButton("Confirmar Seleção");
        btnConfirmar.addActionListener(e -> {
            List<String> membrosSelecionados = listaMembros.getSelectedValuesList();
            if (!membrosSelecionados.isEmpty()) {
                List<Membros> membrosAtribuidos = new ArrayList<>();
                for (String nomeMembro : membrosSelecionados) {
                    membrosEquipe.stream()
                        .filter(membro -> membro.getNome().equals(nomeMembro))
                        .findFirst()
                        .ifPresent(membrosAtribuidos::add);
                }
                tarefa.setMembros(membrosAtribuidos);
                
                try {
                    tarefasDao.atualizarTarefa(tarefa);
                    JOptionPane.showMessageDialog(dialogMembros, "Membros atribuídos com sucesso!");
                    dialogMembros.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialogMembros, 
                        "Erro ao atribuir membros: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialogMembros, "Selecione pelo menos um membro.");
            }
        });

        dialogMembros.add(new JScrollPane(listaMembros), BorderLayout.CENTER);
        dialogMembros.add(btnConfirmar, BorderLayout.SOUTH);

        dialogMembros.setVisible(true);
    }

    // Carrega todos os projetos na tabela
    private void carregarProjetos() {
        try {
            DefaultTableModel modelo = view.getModeloTabela();
            modelo.setRowCount(0); // Limpa a tabela antes de carregar
            
            List<Projetos> projetos = projetosDao.buscarTodosProjetos();
            for (Projetos projeto : projetos) {
                modelo.addRow(new Object[]{
                    projeto.getIdProjeto(),  
                    projeto.getNome(),
                    projeto.getDescricao(),
                    projeto.getStatus(),
                    projeto.getEquipe() != null ? projeto.getEquipe().getNomeEquipe() : "Sem equipe"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao carregar projetos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Edita as informações de um projeto selecionado
    private void editarProjeto() {
        int linhaSelecionada = view.getTabelaProjetos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um projeto para editar.");
            return;
        }

        try {
            Long idProjeto = (Long) view.getTabelaProjetos().getValueAt(linhaSelecionada, 0);
            Projetos projeto = projetosDao.buscarProjetoPorId(idProjeto);
            
            if (projeto == null) {
                JOptionPane.showMessageDialog(view, "Erro: Projeto não encontrado.");
                return;
            }

            List<Equipe> equipes = equipeDao.buscarTodasEquipes();
            if (equipes.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Não há equipes cadastradas no sistema.");
                return;
            }

            String[] opcoesEquipe = equipes.stream()
                .map(Equipe::getNomeEquipe)
                .toArray(String[]::new);

            String equipeSelecionada = (String) JOptionPane.showInputDialog(
                view,
                "Selecione a equipe para este projeto:",
                "Atribuir Equipe",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesEquipe,
                projeto.getEquipe() != null ? projeto.getEquipe().getNomeEquipe() : opcoesEquipe[0]
            );

            if (equipeSelecionada != null) {
                equipes.stream()
                    .filter(equipe -> equipe.getNomeEquipe().equals(equipeSelecionada))
                    .findFirst()
                    .ifPresent(equipe -> {
                        projeto.setEquipe(equipe);
                        projeto.setStatus("Em andamento - " + equipeSelecionada);
                        try {
                            projetosDao.atualizarProjeto(projeto);
                            carregarProjetos();
                            JOptionPane.showMessageDialog(view, "Projeto atualizado com sucesso!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(view, 
                                "Erro ao atualizar projeto: " + e.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao editar projeto: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Remove um projeto do sistema
    private void excluirProjeto() {
        int linhaSelecionada = view.getTabelaProjetos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um projeto para excluir.");
            return;
        }

        try {
            Long idProjeto = (Long) view.getTabelaProjetos().getValueAt(linhaSelecionada, 0);
            
            // Verifica se existem tarefas associadas ao projeto
            List<Tarefas> tarefasProjeto = tarefasDao.buscarTodasTarefas(idProjeto);
            if (!tarefasProjeto.isEmpty()) {
                int confirmacao = JOptionPane.showConfirmDialog(view,
                    "Este projeto possui tarefas associadas. A exclusão removerá todas as tarefas. Deseja continuar?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirmacao != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            int confirmacaoFinal = JOptionPane.showConfirmDialog(view,
                "Tem certeza que deseja excluir este projeto?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION);

            if (confirmacaoFinal == JOptionPane.YES_OPTION) {
                projetosDao.deletarProjeto(idProjeto);
                carregarProjetos();
                JOptionPane.showMessageDialog(view, "Projeto excluído com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao excluir projeto: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Adiciona uma nova tarefa ao projeto
    private void adicionarTarefa(Projetos projeto, DefaultListModel<String> modeloLista) {
        if (projeto == null || projeto.getEquipe() == null) {
            JOptionPane.showMessageDialog(view, "Erro: Projeto ou equipe inválida.");
            return;
        }

        JDialog dialogNovaTarefa = new JDialog(view, "Nova Tarefa", true);
        dialogNovaTarefa.setLayout(new BorderLayout(10, 10));
        dialogNovaTarefa.setSize(400, 300);
        dialogNovaTarefa.setLocationRelativeTo(view);

        JPanel painelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNome = new JTextField();
        JTextArea txtDescricao = new JTextArea();
        txtDescricao.setLineWrap(true);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);

        JTextField txtEquipe = new JTextField(projeto.getEquipe().getNomeEquipe());
        txtEquipe.setEditable(false);

        painelForm.add(new JLabel("Nome da Tarefa:"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("Descrição:"));
        painelForm.add(scrollDescricao);
        painelForm.add(new JLabel("Equipe:"));
        painelForm.add(txtEquipe);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            if (txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialogNovaTarefa, 
                    "Por favor, insira um nome para a tarefa.");
                return;
            }

            try {
                Tarefas novaTarefa = new Tarefas();
                novaTarefa.setNome(txtNome.getText().trim());
                novaTarefa.setDescricao(txtDescricao.getText().trim());
                novaTarefa.setProjeto(projeto);
                novaTarefa.setStatus("Pendente");
                novaTarefa.setEquipe(projeto.getEquipe());
                
                tarefasDao.criarTarefa(novaTarefa);
                
                modeloLista.addElement(String.format("Tarefa: %s - Status: %s - Equipe: %s",
                    novaTarefa.getNome(),
                    novaTarefa.getStatus(),
                    projeto.getEquipe().getNomeEquipe()));
                
                JOptionPane.showMessageDialog(dialogNovaTarefa, 
                    "Tarefa criada com sucesso!");
                dialogNovaTarefa.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialogNovaTarefa,
                    "Erro ao criar tarefa: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnSalvar);

        dialogNovaTarefa.add(painelForm, BorderLayout.CENTER);
        dialogNovaTarefa.add(painelBotoes, BorderLayout.SOUTH);
        dialogNovaTarefa.setVisible(true);
    }

    // Retorna à tela anterior
    private void voltar() {
        view.dispose();
        telaAnterior.setVisible(true);
    }
}
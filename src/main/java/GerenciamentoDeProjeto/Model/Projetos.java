package GerenciamentoDeProjeto.Model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Projetos")
public class Projetos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProjeto;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String descricao;

    @Column (nullable = false)
    private String categoria;


    @Column (nullable = false, columnDefinition = "DATE")
    private LocalDate dataInicio;


    @Column (nullable = false, columnDefinition = "DATE")
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn (nullable = false, name = "idCliente")
    private Clientes cliente;

    @ManyToOne
    @JoinColumn (nullable = false, name = "idEquipe")
    private Equipe equipe;

    @OneToMany (mappedBy = "projeto")
    private List <Tarefas> tarefa;

    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<Tarefas> getTarefas() {
        return tarefa;
    }

    public void setTarefas(List<Tarefas> tarefa) {
        this.tarefa = tarefa;
    }


}
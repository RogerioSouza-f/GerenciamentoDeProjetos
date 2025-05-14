package GerenciamentoDeProjeto.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Projetos")
public class Projetos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjeto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String status = "Aguardando";

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false) // o JPA cria uma coluna de junção na tabela
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "idEquipe", nullable = true)
    private Equipe equipe;

    @OneToMany(mappedBy = "projeto")
    private List<Tarefas> tarefas;

    // Getters e Setters
    public long getIdProjeto() {
        return idProjeto;
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

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tarefas> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefas> tarefas) {
        this.tarefas = tarefas;
    }
    public Equipe getEquipe() {return equipe;}

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
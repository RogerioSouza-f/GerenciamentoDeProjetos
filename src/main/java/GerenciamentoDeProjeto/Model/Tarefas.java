package GerenciamentoDeProjeto.Model;

import jakarta.persistence.*;

@Entity
@Table(name = " Tarefas")
public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarefas;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idProjeto")
    private Projetos IdProjeto;

    public int getIdTarefas() {
        return idTarefas;
    }

    public void setIdTarefas(int idTarefas) {
        this.idTarefas = idTarefas;
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

    public Projetos getIdProjeto() {
        return IdProjeto;
    }

    public void setIdProjeto(Projetos idProjeto) {
        IdProjeto = idProjeto;
    }
}


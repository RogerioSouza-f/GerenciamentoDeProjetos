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
    private Projetos projeto;

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

    public Projetos getProjeto() {
        return projeto;
    }

    public void setProjeto(Projetos projeto) {
        this.projeto = projeto;
    }
}


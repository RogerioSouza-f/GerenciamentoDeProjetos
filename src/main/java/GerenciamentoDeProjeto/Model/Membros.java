package GerenciamentoDeProjeto.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Membros")
public class Membros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMembro;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String formacao;

//    @Column(nullable = false)
//    private NivelSenioridade nivel_senioridade;

    @ManyToMany
    @JoinTable(name = "Membros-Tarefas")
    private List<Tarefas> tarefa;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idEquipe")
    private Equipe idEquipe;

    public long getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(long idMembro) {
        this.idMembro = idMembro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public List<Tarefas> getTarefa() {
        return tarefa;
    }

    public void setTarefa(List<Tarefas> tarefa) {
        this.tarefa = tarefa;
    }

    public void setIdEquipe(Equipe equipe) {
    }
    public Equipe getIdEquipe() {return idEquipe;}

//    public void setNivel_senioridade(NivelSenioridade nivel_senioridade) {
//        this.nivel_senioridade = nivel_senioridade;
//    }
}

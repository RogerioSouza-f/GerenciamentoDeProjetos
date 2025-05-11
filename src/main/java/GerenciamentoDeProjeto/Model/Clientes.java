package GerenciamentoDeProjeto.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(nullable = false)
    private String nome;


    @Column( unique = true, nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Projetos> projetos;

    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Projetos> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projetos> projetos) {
        this.projetos = projetos;
    }
}
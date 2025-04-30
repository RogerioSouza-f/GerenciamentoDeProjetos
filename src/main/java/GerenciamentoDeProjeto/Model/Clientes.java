package GerenciamentoDeProjeto.Model;

import jakarta.persistence.*;

@Entity
@Table(name = " Clientes ")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column(nullable = false)
    private String nome;


    @Column( unique = true, nullable = false)
    private String telefone;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idProjeto")
    private Projetos IdProjeto;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
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

    public Projetos getIdProjeto() {
        return IdProjeto;
    }

    public void setIdProjeto(Projetos idProjeto) {
        IdProjeto = idProjeto;
    }
}


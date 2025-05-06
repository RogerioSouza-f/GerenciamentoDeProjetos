package GerenciamentoDeProjeto.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEquipe;

    @Column(unique = true, nullable = false)
    private String nomeEquipe;

    @Column(nullable = false)
    private String descricaoEquipe;

    @OneToMany(mappedBy = "idEquipe")
    private List<Membros> membros;

    public long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getDescricaoEquipe() {
        return descricaoEquipe;
    }

    public void setDescricaoEquipe(String descricaoEquipe) {
        this.descricaoEquipe = descricaoEquipe;
    }

    public List<Membros> getMembros() {
        return membros;
    }

    public void setMembros(List<Membros> membros) {
        this.membros = membros;
    }
}

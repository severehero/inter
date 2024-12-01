package dentist.com.app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id") // Usa o mesmo 'id' da tabela 'usuario'
@Table(name = "dentista")
public class Dentista extends Usuarios {

    @Column(name = "cro", length = 255)
    private String CRO;

    @Column(name = "especializacao", length = 255)
    private String especializacao; 

    public Dentista() {}

    // Chama o construtor da superclasse sem 'id'
    public Dentista(String nome, String email, String senha, String especializacao, String CRO) {
        super(nome, email, senha); // Chama o construtor da superclasse sem 'id'
        this.especializacao = especializacao;
        this.CRO = CRO;
    }

    // Getters and setters
    public String getCRO() {
        return CRO;
    }

    public void setCRO(String CRO) {
        this.CRO = CRO;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    @Override
    public String toString() {
        return "Dentista{id=" + getId() + ", nome='" + getNome() + "', email='" + getEmail() + 
               "', CRO='" + CRO + "', Especializacao=" + especializacao + "}";
    }
}
package dentist.com.app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "Id")
@Table(name = "cliente")
public class Cliente extends Usuarios {

    @Column(length = 11, nullable = false, unique = true)
    private String CPF;

    public Cliente() {}

    // Chamando o construtor da superclasse sem o 'id'
    public Cliente(String nome, String email, String senha, String CPF) {
        super(nome, email, senha); // Chama o construtor da superclasse sem 'id'
        this.CPF = CPF;
    }

    // Getters e setters
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + getId() + ", nome='" + getNome() + "', email='" + getEmail() + "', CPF='" + CPF + "'}";
    }
}

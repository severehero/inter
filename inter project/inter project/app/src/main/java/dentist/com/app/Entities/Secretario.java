package dentist.com.app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id") // Uses the same 'id' from the 'usuario' table
@Table(name = "secretario")
public class Secretario extends Usuarios {

    @Column(name = "cargahoraria", length = 255)
    private String cargahoraria;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;  // Keep BigDecimal for monetary values

    public Secretario() {}

    // Constructor that calls the superclass constructor without 'id'
    public Secretario(String nome, String email, String senha, BigDecimal salario, String cargahoraria) {
        super(nome, email, senha); // Calls the superclass constructor without 'id'
        this.salario = salario;
        this.cargahoraria = cargahoraria;
    }

    public String getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(String cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        // Adds validation for minimum salary (greater than 1400)
        if (salario.compareTo(new BigDecimal("1400.00")) <= 0) {
            throw new IllegalArgumentException("Salário deve ser maior que 1400");
        }
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Secretario{id=" + getId() + ", nome='" + getNome() + "', email='" + getEmail() +
                "', salario=" + salario + ", cargahoraria='" + cargahoraria + "'}";
    }
}

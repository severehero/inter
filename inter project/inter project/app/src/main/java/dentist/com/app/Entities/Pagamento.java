package dentist.com.app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "StatusPay", nullable = false)
    private boolean statusPay;

    @Column(name = "DataPay", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.time.LocalDateTime dataPay;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "QuantValor", nullable = true)
    private java.math.BigDecimal quantValor;

    public Pagamento() {}

   
    public Pagamento(boolean statusPay, java.time.LocalDateTime dataPay, String nome, java.math.BigDecimal quantValor) {
        this.statusPay = statusPay;
        this.dataPay = dataPay;
        this.nome = nome;
        this.quantValor = quantValor;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
               "id=" + id +
               ", statusPay=" + statusPay +
               ", dataPay=" + dataPay +
               ", nome='" + nome + '\'' +
               ", quantValor=" + quantValor +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return id == pagamento.id;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
}

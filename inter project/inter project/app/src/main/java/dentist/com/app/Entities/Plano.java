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
@Getter
@Setter
@Table(name = "plano") // Nome da tabela ajustado para coincidir com o banco
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração ajustada
    private int id;

    @Column(name = "status", length = 50, nullable = true)
    private String status;

    @Column(name = "descricao", nullable = false) // Removido o length, pois é um TEXT no banco
    private String descricao;

    @Column(name = "valormensal", nullable = false)
    private int valormensal;

    @Column(name = "valoranual", nullable = false)
    private int valoranual;
}

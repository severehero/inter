package dentist.com.app.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ficha")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Data de nascimento do paciente
    @Column(name = "DataNasc", nullable = false)
    private Date dataNasc;

    // Estado civil do paciente
    @Column(name = "EstadoCivil", length = 40, nullable = false)
    private String estadoCivil;

    // Endereço do paciente
    @Column(name = "Endereco", length = 255, nullable = false)
    private String endereco;

    // Alergias do paciente (campo opcional)
    @Column(name = "Alergia", columnDefinition = "TEXT")
    private String alergia;

    // Indica se o paciente possui sensibilidade a medicamentos
    @Column(name = "SensibilidadeMed", nullable = true)
    private Boolean sensibilidadeMed;

    // Indica se o paciente tem problemas de pressão sanguínea
    @Column(name = "PressaoSanguinea", nullable = true)
    private Boolean pressaoSanguinea;

    // Indica se o paciente está tomando algum medicamento atualmente
    @Column(name = "TomandoMed", nullable = true)
    private Boolean tomandoMed;

    // Descrição de problemas de saúde do paciente (campo opcional)
    @Column(name = "ProblemasSaude", columnDefinition = "TEXT", nullable = true)
    private String problemasSaude;

    // Observações adicionais sobre o paciente
    @Column(name = "Observacoes", columnDefinition = "TEXT", nullable = true)
    private String observacoes;

    // Histórico médico do paciente
    @Column(name = "Historico", columnDefinition = "TEXT", nullable = true)
    private String historico;

    // Relacionamento com a entidade Cliente
    @ManyToOne
    @JoinColumn(name = "idCli", nullable = false, referencedColumnName = "id", foreignKey = @jakarta.persistence.ForeignKey(name = "fk_ficha_cliente"))
    private Cliente cliente;
}

package dentist.com.app.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import dentist.com.app.Entities.Ficha;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Integer> {

    // Buscar fichas por nome do cliente e data de nascimento após uma data específica
    @Query("SELECT f FROM Ficha f WHERE f.cliente.nome LIKE %:nome% AND f.dataNasc >= :data")
    List<Ficha> findByClienteNomeAndDataNascAfter(@Param("nome") String nome, @Param("data") Date data);

    // Buscar a última ficha cadastrada
    @Query(value = "SELECT * FROM ficha WHERE id = (SELECT MAX(id) FROM ficha)", nativeQuery = true)
    Ficha findUltimaFichaCadastrada();

    // Buscar fichas por nome parcial do cliente
    List<Ficha> findByCliente_NomeContaining(String nome);

    // Buscar fichas com histórico médico não nulo
    @Query("SELECT f FROM Ficha f WHERE f.historico IS NOT NULL")
    List<Ficha> findWithHistorico();

    // Atualizar estado civil de uma ficha específica
    @Modifying
    @Transactional
    @Query("UPDATE Ficha f SET f.estadoCivil = :estadoCivil WHERE f.id = :id")
    int updateEstadoCivil(@Param("id") Integer id, @Param("estadoCivil") String estadoCivil);

    // Buscar fichas por alergias específicas (texto parcial)
    @Query("SELECT f FROM Ficha f WHERE f.alergia LIKE %:alergia%")
    List<Ficha> findByAlergiaContaining(@Param("alergia") String alergia);

    // Deletar fichas de um cliente específico
    @Modifying
    @Transactional
    @Query("DELETE FROM Ficha f WHERE f.cliente.id = :clienteId")
    void deleteByClienteId(@Param("clienteId") Integer clienteId);

    // Método para cadastrar uma nova ficha (usando stored procedure)
    @Procedure(procedureName = "sp_CadastrarFicha")
    void cadastrarFicha(
            @Param("DataNasc") Date dataNasc,
            @Param("EstadoCivil") String estadoCivil,
            @Param("Endereco") String endereco,
            @Param("Alergia") String alergia,
            @Param("SensibilidadeMed") Boolean sensibilidadeMed,
            @Param("PressaoSanguinea") Boolean pressaoSanguinea,
            @Param("TomandoMed") Boolean tomandoMed,
            @Param("ProblemasSaude") String problemasSaude,
            @Param("Observacoes") String observacoes,
            @Param("Historico") String historico,
            @Param("IdCli") Integer idCli);

    // Buscar fichas de pacientes com pressão sanguínea alta e sensibilidade a medicamentos
    @Query("SELECT f FROM Ficha f WHERE f.pressaoSanguinea = true AND f.sensibilidadeMed = true")
    List<Ficha> findSensibilidadeMedAndPressaoSanguinea();

    // Atualizar histórico médico de uma ficha
    @Modifying
    @Transactional
    @Query("UPDATE Ficha f SET f.historico = :historico WHERE f.id = :id")
    int updateHistorico(@Param("id") Integer id, @Param("historico") String historico);
}

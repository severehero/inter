package dentist.com.app.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Secretario;

@Repository
public interface SecretarioRepository extends JpaRepository<Secretario, Integer> {

    // Buscar secretário pela carga horária exata
    List<Secretario> findByCargahoraria(String cargahoraria);

    // Buscar secretário pela carga horária aproximada (contendo a string)
    List<Secretario> findByCargahorariaContaining(String cargahoraria);

    // Exemplo de consulta customizada com JPQL
    @Query("SELECT s FROM Secretario s WHERE s.cargahoraria = :cargahoraria")
    List<Secretario> buscarPorCargaHoraria(@Param("cargahoraria") String cargahoraria);

    // Buscar secretário pelo id (herdado do JpaRepository)
    Optional<Secretario> findById(Integer id);

    // Buscar secretário pelo nome (contendo a string)
    List<Secretario> findByNomeContaining(String nome);

    // Atualizar carga horária de um secretário (UPDATE)
    @Modifying
    @Transactional
    @Query("UPDATE Secretario s SET s.cargahoraria = :cargahoraria WHERE s.id = :id")
    int updateCargaHoraria(@Param("id") Integer id, @Param("cargahoraria") String cargahoraria);

    // Deletar um secretário pelo id (DELETE)
    @Modifying
    @Transactional
    @Query("DELETE FROM Secretario s WHERE s.id = :id")
    void deleteSecretarioById(@Param("id") Integer id);

    // Buscar todos os secretários com carga horária superior a um valor específico (consultas customizadas)
    @Query("SELECT s FROM Secretario s WHERE s.cargahoraria > :cargahoraria")
    List<Secretario> buscarPorCargaHorariaSuperior(@Param("cargahoraria") String cargahoraria);

    // Buscar o último secretário cadastrado (consultas com subconsulta)
    @Query(value = "SELECT * FROM secretario WHERE id = (SELECT MAX(id) FROM secretario)", nativeQuery = true)
    Secretario findUltimoSecretarioCadastrado();

    // Buscar secretários com salário superior a um valor específico (consulta com parâmetro)
    @Query("SELECT s FROM Secretario s WHERE s.salario > :salario")
    List<Secretario> buscarPorSalarioSuperior(@Param("salario") BigDecimal salario);

    // Atualizar salário de um secretário, garantindo que seja superior a 1400 (logicamente no repositório)
    @Modifying
    @Transactional
    @Query("UPDATE Secretario s SET s.salario = :salario WHERE s.id = :id AND :salario > 1400")
    int updateSalario(@Param("id") Integer id, @Param("salario") BigDecimal salario);
}

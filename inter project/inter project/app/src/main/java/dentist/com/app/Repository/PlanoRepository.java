package dentist.com.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {

    // Buscar planos pela descrição contendo um texto específico
    List<Plano> findByDescricaoContaining(String descricao);

    // Buscar planos com valor mensal acima de um valor específico
    List<Plano> findByValormensalGreaterThan(int valormensal);

    // Buscar um plano pelo ID (herdado do JpaRepository, mas explicitamente mostrado para clareza)
    Optional<Plano> findById(Integer id);

    // Buscar o último plano cadastrado (SELECT com subconsulta)
    @Query(value = "SELECT * FROM plano WHERE id = (SELECT MAX(id) FROM plano)", nativeQuery = true)
    Plano findUltimoPlanoCadastrado();

    // Método para cadastrar um novo plano (usando stored procedure)
    @Procedure(procedureName = "sp_CadastrarPlano")
    void cadastrarPlano(
            @Param("Status") String status,
            @Param("Descricao") String descricao,
            @Param("ValorMensal") int valormensal,
            @Param("ValorAnual") int valoranual);

    // Atualizar a descrição e o status de um plano pelo ID
    @Modifying
    @Transactional
    @Query("UPDATE Plano p SET p.descricao = :descricao, p.status = :status WHERE p.id = :id")
    int updatePlano(@Param("id") Integer id, @Param("descricao") String descricao, @Param("status") String status);

    // Deletar um plano pelo ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Plano p WHERE p.id = :id")
    void deletePlanoById(@Param("id") Integer id);
}

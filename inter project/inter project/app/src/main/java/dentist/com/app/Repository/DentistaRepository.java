package dentist.com.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Dentista;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

    // Buscar dentista pelo CRO (único)
    Optional<Dentista> findByCRO(String CRO);

    // Buscar dentistas por especialização
    List<Dentista> findByEspecializacaoContaining(String especializacao);

    // Buscar dentista pelo nome
    List<Dentista> findByNomeContaining(String nome);

    // Buscar dentista pelo id (herdado do JpaRepository)
    Optional<Dentista> findById(Integer id);

    // Buscar o último dentista cadastrado (SELECT com subconsulta)
    @Query(value = "SELECT * FROM dentista WHERE id = (SELECT MAX(id) FROM dentista)", nativeQuery = true)
    Dentista findUltimoDentistaCadastrado();

    // Método para cadastrar um novo dentista (usando stored procedure)
    // @Procedure(procedureName = "sp_CadastrarDentista")  // Se houver uma stored procedure, descomente esta linha
    // void cadastrarDentista(@Param("Nome") String nome, @Param("Email") String email, @Param("Senha") String senha, @Param("CRO") String CRO, @Param("Especializacao") String especializacao);

    // Atualizar dados de um dentista (UPDATE)
    @Modifying
    @Transactional
    @Query("UPDATE Dentista d SET d.nome = :nome, d.email = :email, d.especializacao = :especializacao WHERE d.id = :id")
    int updateDentista(@Param("id") Integer id, @Param("nome") String nome, @Param("email") String email, @Param("especializacao") String especializacao);

    // Deletar um dentista pelo id (DELETE)
    @Modifying
    @Transactional
    @Query("DELETE FROM Dentista d WHERE d.id = :id")
    void deleteDentistaById(@Param("id") Integer id);
}

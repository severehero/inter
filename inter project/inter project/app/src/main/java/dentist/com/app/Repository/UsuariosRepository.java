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

import dentist.com.app.Entities.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    // Método para buscar um usuário por email
    Optional<Usuarios> findByEmail(String email);

    // Método para buscar usuários por nome
    List<Usuarios> findByNomeContaining(String nome);

    // Método para buscar um usuário pelo ID (herdado do JpaRepository, mas explicitamente mostrado para clareza)
    Optional<Usuarios> findById(Integer id);

    // Método para buscar o último usuário cadastrado (SELECT com subconsulta)
    @Query(value = "SELECT * FROM usuarios WHERE id = (SELECT MAX(id) FROM usuarios)", nativeQuery = true)
    Usuarios findUltimoUsuarioCadastrado();

    // Método para cadastrar um novo usuário (usando stored procedure)
    @Procedure(procedureName = "sp_CadastrarUsuario")
    void cadastrarUsuario(
            @Param("Nome") String nome,
            @Param("Email") String email,
            @Param("Senha") String senha);

    // Método para atualizar um usuário (UPDATE)
    @Modifying
    @Transactional
    @Query("UPDATE Usuarios u SET u.nome = :nome, u.email = :email WHERE u.id = :id")
    int updateUsuario(@Param("id") Integer id, @Param("nome") String nome, @Param("email") String email);

    // Método para deletar um usuário (DELETE)
    @Modifying
    @Transactional
    @Query("DELETE FROM Usuarios u WHERE u.id = :id")
    void deleteUsuarioById(@Param("id") Integer id);
}

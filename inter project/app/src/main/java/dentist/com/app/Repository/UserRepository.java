package dentist.com.app.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dentist.com.app.Entities.Usuarios;

@Repository
public interface UserRepository extends JpaRepository<Usuarios, Integer> {
    

    @Query(value = "SELECT * FROM usuarios WHERE id = (SELECT MAX(id) FROM usuarios)", nativeQuery = true)
    Usuarios findUltimoUsuarioCadastrado();


    @Procedure(procedureName = "sp_CadastrarUsuario")
    void cadastrarUsuario(
            @Param("Nome") String nome,
            @Param("Email") String email,
            @Param("Senha") String senha);
}

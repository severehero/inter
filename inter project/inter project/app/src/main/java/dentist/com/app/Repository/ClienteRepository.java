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

import dentist.com.app.Entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Método para buscar um cliente por CPF (único)
    Optional<Cliente> findByCPF(String CPF);

    // Método para buscar clientes por nome (herdado de Usuarios)
    List<Cliente> findByNomeContaining(String nome);

    // Método para buscar clientes por email (herdado de Usuarios)
    List<Cliente> findByEmailContaining(String email);

    // Remover o método findById, pois ele já existe no JpaRepository
    // Optional<Cliente> findById(Integer id);

    // Método para buscar o último cliente cadastrado (SELECT com subconsulta)
    @Query(value = "SELECT * FROM cliente WHERE id = (SELECT MAX(id) FROM cliente)", nativeQuery = true)
    Cliente findUltimoClienteCadastrado();

    // Método para cadastrar um novo cliente via stored procedure
    @Procedure(procedureName = "sp_CadastrarCliente")
    void cadastrarCliente(
            @Param("Nome") String nome,
            @Param("Email") String email,
            @Param("Senha") String senha,
            @Param("CPF") String CPF);

    // Método para atualizar um cliente (UPDATE)
    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.nome = :nome, c.email = :email, c.CPF = :CPF WHERE c.id = :id")
    int updateCliente(@Param("id") Integer id, @Param("nome") String nome, @Param("email") String email, @Param("CPF") String CPF);

    // Método para deletar um cliente (DELETE)
    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente c WHERE c.id = :id")
    void deleteClienteById(@Param("id") Integer id);
}

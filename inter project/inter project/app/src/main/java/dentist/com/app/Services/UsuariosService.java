package dentist.com.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Usuarios;
import dentist.com.app.Repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Método para salvar um novo usuário
    public Usuarios salvarUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    // Método para encontrar um usuário por email
    public Optional<Usuarios> encontrarPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    // Método para recuperar todos os usuários
    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    // Método para encontrar um usuário por ID
    public Optional<Usuarios> findById(Integer id) {
        return usuariosRepository.findById(id);
    }

    // Método para atualizar um usuário
    @Transactional
    public int update(Integer id, String nome, String email) {
        return usuariosRepository.updateUsuario(id, nome, email);
    }

    // Método para deletar um usuário
    @Transactional
    public void delete(Integer id) {
        usuariosRepository.deleteUsuarioById(id);
    }

    // Método para buscar o último usuário cadastrado
    public Usuarios findUltimoUsuarioCadastrado() {
        return usuariosRepository.findUltimoUsuarioCadastrado();
    }

    // Método para cadastrar um novo usuário via stored procedure
    @Transactional
    public void cadastrarUsuario(String nome, String email, String senha) {
        usuariosRepository.cadastrarUsuario(nome, email, senha);
    }
}

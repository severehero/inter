package dentist.com.app.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Usuarios;
import dentist.com.app.Repository.UserRepository;

@Service
    public class UserService {

        @Autowired
        private UserRepository repository;

        public List<Usuarios> findAll() {
            return repository.findAll();
        };

        public Optional<Usuarios> findById(int id) {
            return repository.findById(id);
        }

        @Transactional
        public Usuarios create(Usuarios usuario) {
            try {
                // Chama o procedimento armazenado para cadastrar o usuário
                repository.cadastrarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
                // Retorna o último usuário cadastrado
                return repository.findUltimoUsuarioCadastrado();
            } catch (Exception e) {
                System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
                return null;  // Caso haja erro, retorna null
            }
        }
        

        public Usuarios update(int id, Usuarios usuarioDetails) {
            Optional<Usuarios> usuarioOptional = repository.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuarios usuario = usuarioOptional.get();
                usuario.setNome(usuarioDetails.getNome());
                usuario.setEmail(usuarioDetails.getEmail());
                usuario.setSenha(usuarioDetails.getSenha());

                // Adicione aqui a lógica para atualizar outras propriedades, se necessário
                return repository.save(usuario);
            } else {
                return null; // Ou lance uma exceção indicando que o usuário não foi encontrado
            }
        }

    public void delete(int id) {
        repository.deleteById(id);
    }



}

package dentist.com.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import dentist.com.app.Entities.Usuarios;
import dentist.com.app.Services.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    // Criar um novo usuário
    @PostMapping
    public ResponseEntity<Usuarios> salvarUsuario(@RequestBody Usuarios usuario) {
        Usuarios novoUsuario = usuariosService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Buscar usuário por e-mail
    @GetMapping("/email")
    public ResponseEntity<Usuarios> encontrarPorEmail(@RequestParam String email) {
        Optional<Usuarios> usuario = usuariosService.encontrarPorEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Recuperar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        List<Usuarios> usuarios = usuariosService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
public ResponseEntity<Usuarios> buscarPorId(@PathVariable String id) {
   
        Integer userId = Integer.parseInt(id);
        Optional<Usuarios> usuario = usuariosService.findById(userId);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   
}


    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(
            @PathVariable Integer id,
            @RequestParam String nome,
            @RequestParam String email) {
        int linhasAtualizadas = usuariosService.update(id, nome, email);
        if (linhasAtualizadas > 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        try {
            usuariosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar o último usuário cadastrado
    @GetMapping("/ultimo")
    public ResponseEntity<Usuarios> buscarUltimoUsuarioCadastrado() {
        Usuarios usuario = usuariosService.findUltimoUsuarioCadastrado();
        return ResponseEntity.ok(usuario);
    }

    // Cadastrar usuário via stored procedure
    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarUsuario(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha) {
        usuariosService.cadastrarUsuario(nome, email, senha);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
      @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("ID deve ser um número válido. Erro: " + ex.getMessage());
    }
}

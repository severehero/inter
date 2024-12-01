package dentist.com.app.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dentist.com.app.Entities.Secretario;
import dentist.com.app.Services.SecretarioService;

@RestController
@RequestMapping("/secretarios")
public class SecretarioController {

    @Autowired
    private SecretarioService secretarioService;

    // Endpoint para salvar um secretário
    @PostMapping
    public ResponseEntity<Secretario> salvarSecretario(@RequestBody Secretario secretario) {
        Secretario savedSecretario = secretarioService.salvarSecretario(secretario);
        return ResponseEntity.ok(savedSecretario);
    }

    // Endpoint para buscar secretários por carga horária exata
    @GetMapping("/cargahoraria/{cargahoraria}")
    public ResponseEntity<List<Secretario>> encontrarSecretariosPorCargahoraria(@PathVariable String cargahoraria) {
        List<Secretario> secretarios = secretarioService.encontrarPorCargahoraria(cargahoraria);
        return secretarios.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(secretarios);
    }

    // Endpoint para buscar secretários por carga horária contendo uma substring
    @GetMapping("/cargahoraria/contendo/{cargahoraria}")
    public ResponseEntity<List<Secretario>> encontrarSecretariosPorCargahorariaContendo(@PathVariable String cargahoraria) {
        List<Secretario> secretarios = secretarioService.encontrarPorCargahorariaContendo(cargahoraria);
        if (secretarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(secretarios);
    }

    // Endpoint para buscar secretários com uma consulta customizada (JPQL)
    @GetMapping("/cargahoraria/buscar/{cargahoraria}")
    public ResponseEntity<List<Secretario>> buscarSecretariosPorCargaHoraria(@PathVariable String cargahoraria) {
        List<Secretario> secretarios = secretarioService.buscarPorCargaHoraria(cargahoraria);
        if (secretarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(secretarios);
    }

    // Endpoint para buscar secretários com salário superior a um valor específico
    @GetMapping("/salario/maior-que")
    public ResponseEntity<List<Secretario>> buscarSecretariosPorSalarioSuperior(@RequestParam("salario") BigDecimal salario) {
        List<Secretario> secretarios = secretarioService.buscarPorSalarioSuperior(salario);
        if (secretarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(secretarios);
    }

    // Endpoint para atualizar a carga horária de um secretário
    @PutMapping("/atualizar/cargahoraria/{id}")
    public ResponseEntity<Integer> atualizarCargaHoraria(@PathVariable Integer id, @RequestBody String cargahoraria) {
        int updated = secretarioService.atualizarCargaHoraria(id, cargahoraria);
        if (updated > 0) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar o salário de um secretário
    @PutMapping("/atualizar/salario/{id}")
    public ResponseEntity<Integer> atualizarSalario(@PathVariable Integer id, @RequestBody BigDecimal salario) {
        try {
            int updated = secretarioService.atualizarSalario(id, salario);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Retorna erro de salário inválido
        }
    }

    // Endpoint para deletar um secretário pelo ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSecretarioPorId(@PathVariable Integer id) {
        secretarioService.deletarSecretarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para buscar o último secretário cadastrado
    @GetMapping("/ultimo")
    public ResponseEntity<Secretario> buscarUltimoSecretarioCadastrado() {
        Secretario ultimoSecretario = secretarioService.buscarUltimoSecretarioCadastrado();
        if (ultimoSecretario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ultimoSecretario);
    }
}

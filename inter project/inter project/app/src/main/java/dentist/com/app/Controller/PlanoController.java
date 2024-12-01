package dentist.com.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import dentist.com.app.Entities.Plano;
import dentist.com.app.Services.PlanoService;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    // Listar todos os planos
    @GetMapping
    public List<Plano> listarPlanos() {
        return planoService.listarPlanos();
    }

    // Buscar plano por ID (rota ajustada para evitar conflitos)
    @GetMapping("/id/{id}")
    public ResponseEntity<Plano> buscarPlano(@PathVariable Integer id) {
        Optional<Plano> plano = planoService.buscarPlanoPorId(id);
        return plano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar um novo plano
    @PostMapping
    public ResponseEntity<Plano> criarPlano(@RequestBody Plano plano) {
        Plano novoPlano = planoService.criarPlano(plano);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }

    // Atualizar plano existente
    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizarPlano(@PathVariable Integer id, @RequestBody Plano plano) {
        try {
            Plano planoAtualizado = planoService.atualizarPlano(id, plano);
            return ResponseEntity.ok(planoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar plano
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Integer id) {
        try {
            planoService.deletarPlano(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar planos por descrição
    @GetMapping("/descricao")
    public ResponseEntity<List<Plano>> buscarPlanosPorDescricao(@RequestParam String descricao) {
        List<Plano> planos = planoService.buscarPlanosPorDescricao(descricao);
        if (planos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planos);
    }

    // Buscar planos por valor mensal acima de um valor específico
    @GetMapping("/valormensal")
    public ResponseEntity<List<Plano>> buscarPlanosPorValorMensalMaiorQue(@RequestParam int valor) {
        List<Plano> planos = planoService.buscarPlanosPorValorMensalMaiorQue(valor);
        if (planos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planos);
    }

    // Buscar o último plano cadastrado
    @GetMapping("/ultimo")
    public ResponseEntity<Plano> buscarUltimoPlanoCadastrado() {
        Plano plano = planoService.buscarUltimoPlanoCadastrado();
        return ResponseEntity.ok(plano);
    }

    // Atualizar apenas status e descrição (rota específica para evitar ambiguidades)
    @PutMapping("/id/{id}/atualizar")
    public ResponseEntity<Plano> atualizarStatusEDescricao(
            @PathVariable Integer id,
            @RequestParam String status,
            @RequestParam String descricao) {
        try {
            Plano planoAtualizado = planoService.atualizarStatusEDescricao(id, status, descricao);
            return ResponseEntity.ok(planoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

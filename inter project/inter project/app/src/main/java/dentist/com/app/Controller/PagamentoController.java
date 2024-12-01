package dentist.com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dentist.com.app.Entities.Pagamento;
import dentist.com.app.Services.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    // Criar ou atualizar um pagamento
    @PostMapping
    public ResponseEntity<Pagamento> saveOrUpdate(@RequestBody Pagamento pagamento) {
        Pagamento savedPagamento = pagamentoService.saveOrUpdate(pagamento);
        return ResponseEntity.ok(savedPagamento);
    }

    // Buscar todos os pagamentos
    @GetMapping
    public ResponseEntity<List<Pagamento>> findAll() {
        List<Pagamento> pagamentos = pagamentoService.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    // Buscar um pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> findById(@PathVariable int id) {
        return pagamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar um pagamento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        pagamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

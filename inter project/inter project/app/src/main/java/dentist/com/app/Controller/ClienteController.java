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

import dentist.com.app.Entities.Cliente;
import dentist.com.app.Services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para salvar ou atualizar um cliente
    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente savedCliente = clienteService.saveCliente(cliente);
            return ResponseEntity.ok(savedCliente);  // Retorna o cliente salvo
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Retorna erro 400 se algum campo obrigatório estiver ausente
        }
    }

    // Endpoint para buscar cliente por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> encontrarClientePorCPF(@PathVariable String cpf) {
        Cliente cliente = clienteService.findByCPF(cpf);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);  // Retorna o cliente encontrado
        } else {
            return ResponseEntity.notFound().build();  // Retorna 404 caso não encontre o cliente
        }
    }

    // Endpoint para buscar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.findAllClientes();
        return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
    }

    // Endpoint para deletar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        try {
            clienteService.deleteClienteById(id);
            return ResponseEntity.noContent().build();  // Retorna 204 caso a deleção seja bem-sucedida
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o cliente não for encontrado
        }
    }
}

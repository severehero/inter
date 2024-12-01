package dentist.com.app.Controller;

import java.util.List;  // Import necessário para usar List

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;  // Import para usar @PutMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dentist.com.app.Entities.Dentista;
import dentist.com.app.Services.DentistaService;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    // Endpoint para salvar ou atualizar um dentista
    @PostMapping
    public ResponseEntity<Dentista> salvarDentista(@RequestBody Dentista dentista) {
        try {
            Dentista savedDentista = dentistaService.saveDentista(dentista);
            return ResponseEntity.ok(savedDentista);  // Retorna o dentista salvo
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Retorna erro se algum campo obrigatório estiver ausente
        }
    }

    // Endpoint para buscar dentista por CRO
    @GetMapping("/cro/{cro}")
    public ResponseEntity<Dentista> encontrarDentistaPorCRO(@PathVariable String cro) {
        try {
            Dentista dentista = dentistaService.findByCRO(cro);
            return ResponseEntity.ok(dentista);  // Retorna o dentista encontrado
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 caso não encontre o dentista
        }
    }

    // Endpoint para buscar todos os dentistas
    @GetMapping
    public ResponseEntity<List<Dentista>> getAllDentistas() {
        return ResponseEntity.ok(dentistaService.findAllDentistas());
    }

    // Endpoint para atualizar um dentista
    @PutMapping("/{id}")
    public ResponseEntity<Dentista> atualizarDentista(@PathVariable Integer id, 
                                                      @RequestBody Dentista dentista) {
        try {
            Dentista updatedDentista = dentistaService.updateDentista(id, 
                                                                      dentista.getNome(), 
                                                                      dentista.getEmail(), 
                                                                      dentista.getEspecializacao());
            return ResponseEntity.ok(updatedDentista);  // Retorna o dentista atualizado
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o dentista não for encontrado
        }
    }

    // Endpoint para deletar dentista por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentista(@PathVariable Integer id) {
        try {
            dentistaService.deleteDentistaById(id);
            return ResponseEntity.noContent().build();  // Retorna 204 caso a deleção seja bem-sucedida
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o dentista não for encontrado
        }
    }

    // Endpoint para buscar dentistas por especialização
    @GetMapping("/especializacao/{especializacao}")
    public ResponseEntity<List<Dentista>> encontrarDentistasPorEspecializacao(@PathVariable String especializacao) {
        List<Dentista> dentistas = dentistaService.findByEspecializacao(especializacao);
        if (dentistas.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retorna 404 se não encontrar dentistas para a especialização
        }
        return ResponseEntity.ok(dentistas);  // Retorna a lista de dentistas encontrados
    }
}

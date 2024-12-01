package dentist.com.app.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

import dentist.com.app.Entities.Agenda;
import dentist.com.app.Services.AgendaService;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    // Criar ou atualizar uma agenda
    @PostMapping
    public ResponseEntity<Agenda> saveOrUpdate(@RequestBody Agenda agenda) {
        try {
            Agenda savedAgenda = agendaService.save(agenda);
            return ResponseEntity.ok(savedAgenda);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Se data ou hora não estiverem presentes
        }
    }

    // Buscar todas as agendas
    @GetMapping
    public ResponseEntity<List<Agenda>> findAll() {
        List<Agenda> agendas = agendaService.findAll();
        return ResponseEntity.ok(agendas);
    }

    // Buscar uma agenda por ID
    @GetMapping("/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable int id) {
        return agendaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar agendas por data
    @GetMapping("/data/{dataDia}")
    public ResponseEntity<List<Agenda>> findByDataDia(@PathVariable LocalDate dataDia) {
        List<Agenda> agendas = agendaService.findByDataDia(dataDia);
        return ResponseEntity.ok(agendas);
    }

    // Buscar agendas por horário
    @GetMapping("/hora/{hora}")
    public ResponseEntity<List<Agenda>> findByHora(@PathVariable LocalTime hora) {
        List<Agenda> agendas = agendaService.findByHora(hora);
        return ResponseEntity.ok(agendas);
    }

    // Deletar uma agenda por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        try {
            agendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Se a agenda não for encontrada
        }
    }
}

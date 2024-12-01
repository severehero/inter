package dentist.com.app.Controller;

import java.sql.Date;
import java.util.List;

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

import dentist.com.app.Entities.Ficha;
import dentist.com.app.Services.FichaService;

@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    // Endpoint para buscar todas as fichas
    @GetMapping
    public List<Ficha> getAllFichas() {
        return fichaService.findAll();
    }

    // Endpoint para buscar uma ficha por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ficha> getFichaById(@PathVariable Integer id) {
        try {
            Ficha ficha = fichaService.findById(id);
            return new ResponseEntity<>(ficha, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar fichas por nome do cliente
    @GetMapping("/cliente/{nome}")
    public List<Ficha> getFichasByClienteNome(@PathVariable String nome) {
        return fichaService.findByClienteNome(nome);
    }

    // Endpoint para buscar fichas por nome do cliente e data de nascimento após uma data específica
    @GetMapping("/cliente/{nome}/dataNascAfter")
    public List<Ficha> getFichasByClienteNomeAndDataNascAfter(@PathVariable String nome, @RequestParam Date data) {
        return fichaService.findByClienteNomeAndDataNascAfter(nome, data);
    }

    // Endpoint para salvar ou atualizar uma ficha
    @PostMapping
public ResponseEntity<Ficha> saveFicha(@RequestBody Ficha ficha) {
    try {
        Ficha savedFicha = fichaService.save(ficha);
        return new ResponseEntity<>(savedFicha, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


    // Endpoint para atualizar estado civil de uma ficha
    @PutMapping("/{id}/estadoCivil")
    public ResponseEntity<Void> updateEstadoCivil(@PathVariable Integer id, @RequestParam String estadoCivil) {
        int rowsUpdated = fichaService.updateEstadoCivil(id, estadoCivil);
        if (rowsUpdated > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para buscar fichas com histórico médico
    @GetMapping("/historico")
    public List<Ficha> getFichasWithHistorico() {
        return fichaService.findWithHistorico();
    }

    // Endpoint para deletar uma ficha por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFichaById(@PathVariable Integer id) {
        try {
            fichaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para deletar todas as fichas de um cliente específico
    @DeleteMapping("/cliente/{clienteId}")
    public ResponseEntity<Void> deleteFichasByClienteId(@PathVariable Integer clienteId) {
        fichaService.deleteByClienteId(clienteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para buscar a última ficha cadastrada
    @GetMapping("/ultima")
    public Ficha getUltimaFichaCadastrada() {
        return fichaService.findUltimaFichaCadastrada();
    }

    // Endpoint para buscar fichas por alergia
    @GetMapping("/alergia/{alergia}")
    public List<Ficha> getFichasByAlergia(@PathVariable String alergia) {
        return fichaService.findByAlergia(alergia);
    }

    // Endpoint para buscar fichas de pacientes com pressão sanguínea alta e sensibilidade a medicamentos
    @GetMapping("/sensibilidadeMedPressao")
    public List<Ficha> getFichasWithSensibilidadeMedAndPressaoSanguinea() {
        return fichaService.findSensibilidadeMedAndPressaoSanguinea();
    }

    // Endpoint para atualizar histórico médico de uma ficha
    @PutMapping("/{id}/historico")
    public ResponseEntity<Void> updateHistorico(@PathVariable Integer id, @RequestParam String historico) {
        int rowsUpdated = fichaService.updateHistorico(id, historico);
        if (rowsUpdated > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

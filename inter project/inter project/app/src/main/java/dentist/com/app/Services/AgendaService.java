package dentist.com.app.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dentist.com.app.Entities.Agenda;
import dentist.com.app.Repository.AgendaRepository;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    // Salvar ou atualizar uma agenda com validação
    public Agenda save(Agenda agenda) {
        if (agenda.getDataDia() == null || agenda.getHora() == null) {
            throw new IllegalArgumentException("Data e hora da agenda são obrigatórios.");
        }
        return agendaRepository.save(agenda);
    }

    // Buscar uma agenda por ID
    public Optional<Agenda> findById(int id) {
        return agendaRepository.findById(id);
    }

    // Buscar todas as agendas
    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    // Buscar agendas por data
    public List<Agenda> findByDataDia(LocalDate dataDia) {
        if (dataDia == null) {
            throw new IllegalArgumentException("Data é obrigatória para a busca.");
        }
        return agendaRepository.findByDataDia(dataDia);
    }

    // Buscar agendas por horário
    public List<Agenda> findByHora(LocalTime hora) {
        if (hora == null) {
            throw new IllegalArgumentException("Horário é obrigatório para a busca.");
        }
        return agendaRepository.findByHora(hora);
    }

    // Deletar uma agenda por ID com verificação
    public void deleteById(int id) {
        if (!agendaRepository.existsById(id)) {
            throw new IllegalArgumentException("Agenda com ID " + id + " não encontrada.");
        }
        agendaRepository.deleteById(id);
    }
}

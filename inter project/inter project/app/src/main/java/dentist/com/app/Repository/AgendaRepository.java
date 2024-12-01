package dentist.com.app.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dentist.com.app.Entities.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    // Buscar agendamentos por data específica
    List<Agenda> findByDataDia(LocalDate dataDia);

    // Buscar agendamentos por horário específico
    List<Agenda> findByHora(LocalTime hora);

    // Buscar agendamentos por data e horário
    List<Agenda> findByDataDiaAndHora(LocalDate dataDia, LocalTime hora);

    // Buscar agendamentos futuros
    List<Agenda> findByDataDiaAfter(LocalDate dataDia);
}

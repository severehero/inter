package dentist.com.app.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "agenda")
@Getter
@Setter
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DataDia", nullable = false)
    private LocalDate dataDia;

    @Column(name = "Hora", nullable = false)
    private LocalTime hora;

    public Agenda() {}

    public Agenda(LocalDate dataDia, LocalTime hora) {
        this.dataDia = dataDia;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Agenda{" +
               "id=" + id +
               ", dataDia=" + dataDia +
               ", hora=" + hora +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return id == agenda.id;
    }
}

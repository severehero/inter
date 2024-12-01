package dentist.com.app.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dentist.com.app.Entities.Ficha;
import dentist.com.app.Repository.FichaRepository;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    // Buscar todas as fichas
    public List<Ficha> findAll() {
        return fichaRepository.findAll();
    }

    // Buscar ficha por ID
    public Ficha findById(Integer id) {
        return fichaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ficha com ID " + id + " não encontrada."));
    }

    // Buscar fichas por nome do cliente
    public List<Ficha> findByClienteNome(String nome) {
        return fichaRepository.findByCliente_NomeContaining(nome);
    }

    // Buscar fichas por nome do cliente e data de nascimento após uma data específica
    public List<Ficha> findByClienteNomeAndDataNascAfter(String nome, Date data) {
        return fichaRepository.findByClienteNomeAndDataNascAfter(nome, data);
    }

    // Salvar ou atualizar uma ficha
    public Ficha save(Ficha ficha) {
        if (ficha.getCliente() == null || ficha.getCliente().getId() == 0) {
            throw new IllegalArgumentException("É obrigatório associar um cliente à ficha.");
        }
        return fichaRepository.save(ficha);
    }

    // Atualizar estado civil de uma ficha
    public int updateEstadoCivil(Integer id, String estadoCivil) {
        return fichaRepository.updateEstadoCivil(id, estadoCivil);
    }

    // Buscar fichas com histórico médico
    public List<Ficha> findWithHistorico() {
        return fichaRepository.findWithHistorico();
    }

    // Deletar uma ficha por ID com validação
    public void deleteById(Integer id) {
        if (!fichaRepository.existsById(id)) {
            throw new IllegalArgumentException("Ficha com ID " + id + " não encontrada.");
        }
        fichaRepository.deleteById(id);
    }

    // Deletar fichas de um cliente específico
    public void deleteByClienteId(Integer clienteId) {
        fichaRepository.deleteByClienteId(clienteId);
    }

    // Buscar a última ficha cadastrada
    public Ficha findUltimaFichaCadastrada() {
        return fichaRepository.findUltimaFichaCadastrada();
    }

    // Buscar fichas por alergia
    public List<Ficha> findByAlergia(String alergia) {
        return fichaRepository.findByAlergiaContaining(alergia);
    }

    // Buscar fichas de pacientes com pressão sanguínea alta e sensibilidade a medicamentos
    public List<Ficha> findSensibilidadeMedAndPressaoSanguinea() {
        return fichaRepository.findSensibilidadeMedAndPressaoSanguinea();
    }

    // Atualizar histórico médico de uma ficha
    public int updateHistorico(Integer id, String historico) {
        return fichaRepository.updateHistorico(id, historico);
    }

    // Outros métodos personalizados podem ser adicionados aqui, se necessário
}

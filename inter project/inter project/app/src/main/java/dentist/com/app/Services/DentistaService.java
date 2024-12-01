package dentist.com.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dentist.com.app.Entities.Dentista;
import dentist.com.app.Repository.DentistaRepository;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    // Método para salvar ou atualizar um dentista
    public Dentista saveDentista(Dentista dentista) {
        // Validações antes de salvar
        if (dentista.getCRO() == null || dentista.getCRO().isEmpty()) {
            throw new IllegalArgumentException("CRO é obrigatório.");
        }
        if (dentista.getNome() == null || dentista.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do dentista é obrigatório.");
        }
        // Salva ou atualiza o dentista
        return dentistaRepository.save(dentista);
    }

    // Método para encontrar um dentista por CRO
    public Dentista findByCRO(String CRO) {
        return dentistaRepository.findByCRO(CRO)
                .orElseThrow(() -> new IllegalArgumentException("Dentista com CRO " + CRO + " não encontrado."));
    }

    // Método para buscar todos os dentistas
    public List<Dentista> findAllDentistas() {
        return dentistaRepository.findAll();
    }

    // Método para atualizar dados de um dentista
    public Dentista updateDentista(Integer id, String nome, String email, String especializacao) {
        Dentista dentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dentista com ID " + id + " não encontrado."));
        
        // Atualiza os campos do dentista
        dentista.setNome(nome);
        dentista.setEmail(email);
        dentista.setEspecializacao(especializacao);

        return dentistaRepository.save(dentista);
    }

    // Método para deletar um dentista por ID
    public void deleteDentistaById(Integer id) {
        if (!dentistaRepository.existsById(id)) {
            throw new IllegalArgumentException("Dentista com ID " + id + " não encontrado.");
        }
        dentistaRepository.deleteById(id);
    }

    // Método para buscar o último dentista cadastrado
    public Dentista findUltimoDentistaCadastrado() {
        return dentistaRepository.findUltimoDentistaCadastrado();
    }

    // Método para buscar dentistas por especialização
    public List<Dentista> findByEspecializacao(String especializacao) {
        return dentistaRepository.findByEspecializacaoContaining(especializacao);
    }
}

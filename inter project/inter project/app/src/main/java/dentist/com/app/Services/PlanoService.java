package dentist.com.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Plano;
import dentist.com.app.Repository.PlanoRepository;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    @Autowired
    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    // Listar todos os planos
    public List<Plano> listarPlanos() {
        return planoRepository.findAll();
    }

    // Buscar plano por ID
    public Optional<Plano> buscarPlanoPorId(Integer id) {
        return planoRepository.findById(id);
    }

    // Criar um novo plano
    public Plano criarPlano(Plano plano) {
        return planoRepository.save(plano);
    }

    // Atualizar um plano existente
    @Transactional
    public Plano atualizarPlano(Integer id, Plano plano) {
        Optional<Plano> planoExistente = planoRepository.findById(id);
        if (planoExistente.isPresent()) {
            plano.setId(id); // Certifica que o ID está correto
            return planoRepository.save(plano);
        }
        throw new IllegalArgumentException("Plano com ID " + id + " não encontrado.");
    }

    // Deletar um plano por ID
    @Transactional
    public void deletarPlano(Integer id) {
        if (planoRepository.existsById(id)) {
            planoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Plano com ID " + id + " não encontrado.");
        }
    }

    // Buscar planos pela descrição contendo um texto
    public List<Plano> buscarPlanosPorDescricao(String descricao) {
        return planoRepository.findByDescricaoContaining(descricao);
    }

    // Buscar planos com valor mensal acima de um valor específico
    public List<Plano> buscarPlanosPorValorMensalMaiorQue(int valor) {
        return planoRepository.findByValormensalGreaterThan(valor);
    }

    // Buscar o último plano cadastrado
    public Plano buscarUltimoPlanoCadastrado() {
        return planoRepository.findUltimoPlanoCadastrado();
    }

    // Atualizar status e descrição de um plano
    @Transactional
    public Plano atualizarStatusEDescricao(Integer id, String status, String descricao) {
        int linhasAtualizadas = planoRepository.updatePlano(id, descricao, status);
        if (linhasAtualizadas > 0) {
            return planoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Erro ao atualizar o plano com ID " + id));
        }
        throw new IllegalArgumentException("Plano com ID " + id + " não encontrado.");
    }
}

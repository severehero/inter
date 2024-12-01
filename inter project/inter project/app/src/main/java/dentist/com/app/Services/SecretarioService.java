package dentist.com.app.Services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dentist.com.app.Entities.Secretario;
import dentist.com.app.Repository.SecretarioRepository;

@Service
public class SecretarioService {

    @Autowired
    private SecretarioRepository secretarioRepository;

    // Método para salvar um novo secretário
    public Secretario salvarSecretario(Secretario secretario) {
        // O salário já é validado no repositório, então podemos remover a validação aqui
        return secretarioRepository.save(secretario);
    }

    // Método para encontrar secretário por carga horária exata
    public List<Secretario> encontrarPorCargahoraria(String cargahoraria) {
        return secretarioRepository.findByCargahoraria(cargahoraria);
    }

    // Método para encontrar secretário por carga horária contendo a string
    public List<Secretario> encontrarPorCargahorariaContendo(String cargahoraria) {
        return secretarioRepository.findByCargahorariaContaining(cargahoraria);
    }

    // Método para buscar secretário com consulta customizada (JPQL)
    public List<Secretario> buscarPorCargaHoraria(String cargahoraria) {
        return secretarioRepository.buscarPorCargaHoraria(cargahoraria);
    }

    // Método para buscar secretários com salário superior a um valor específico
    public List<Secretario> buscarPorSalarioSuperior(BigDecimal salario) {
        return secretarioRepository.buscarPorSalarioSuperior(salario);
    }

    // Método para atualizar a carga horária de um secretário
    public int atualizarCargaHoraria(Integer id, String cargahoraria) {
        return secretarioRepository.updateCargaHoraria(id, cargahoraria);
    }

    // Método para atualizar o salário de um secretário
    public int atualizarSalario(Integer id, BigDecimal salario) {
        // A validação do salário é feita no repositório, então removemos essa parte daqui
        return secretarioRepository.updateSalario(id, salario);
    }

    // Método para deletar um secretário pelo ID
    public void deletarSecretarioPorId(Integer id) {
        secretarioRepository.deleteSecretarioById(id);
    }

    // Método para buscar o último secretário cadastrado
    public Secretario buscarUltimoSecretarioCadastrado() {
        return secretarioRepository.findUltimoSecretarioCadastrado();
    }

    // Outros métodos de lógica de negócios podem ser adicionados aqui
}

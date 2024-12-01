package dentist.com.app.Services;

import dentist.com.app.Entities.Pagamento;
import dentist.com.app.Repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Salvar ou atualizar um pagamento
    public Pagamento saveOrUpdate(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // Buscar um pagamento por ID
    public Optional<Pagamento> findById(int id) {
        return pagamentoRepository.findById(id);
    }

    // Buscar todos os pagamentos
    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    // Deletar um pagamento por ID
    public void deleteById(int id) {
        pagamentoRepository.deleteById(id);
    }
}

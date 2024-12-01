package dentist.com.app.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dentist.com.app.Entities.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    // Encontrar pagamentos pelo nome exato
    List<Pagamento> findByNome(String nome);

    // Encontrar pagamentos por parte do nome
    List<Pagamento> findByNomeContaining(String nome);

    // Encontrar pagamentos acima de um valor específico
    List<Pagamento> findByQuantValorGreaterThan(BigDecimal quantValor);

    // Encontrar pagamentos com status específico
    List<Pagamento> findByStatusPay(boolean statusPay);

    // Encontrar pagamentos com data igual ou posterior
    List<Pagamento> findByDataPayAfter(LocalDateTime dataPay);
}

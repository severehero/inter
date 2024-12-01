package dentist.com.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Cliente;
import dentist.com.app.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para salvar ou atualizar um cliente
    public Cliente saveCliente(Cliente cliente) {
        if (cliente.getCPF() == null || cliente.getCPF().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        return clienteRepository.save(cliente);
    }

    // Método para encontrar um cliente por CPF
    public Cliente findByCPF(String CPF) {
        // Busca o cliente pelo CPF (o repositório retorna um Optional)
        Optional<Cliente> clienteOptional = clienteRepository.findByCPF(CPF);
        
        // Verifica se o cliente foi encontrado e, caso não, lança uma exceção
        return clienteOptional.orElseThrow(() -> 
            new IllegalArgumentException("Cliente com CPF " + CPF + " não encontrado.")
        );
    }
    
    
    

    // Método para buscar todos os clientes
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    // Método para encontrar um cliente por ID
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    // Método para buscar o último cliente cadastrado
    public Cliente findUltimoClienteCadastrado() {
        return clienteRepository.findUltimoClienteCadastrado();
    }

    // Método para cadastrar um novo cliente via stored procedure
    @Transactional
    public void cadastrarCliente(String nome, String email, String senha, String CPF) {
        clienteRepository.cadastrarCliente(nome, email, senha, CPF);
    }

    // Método para atualizar um cliente
    @Transactional
    public int updateCliente(Integer id, String nome, String email, String CPF) {
        return clienteRepository.updateCliente(id, nome, email, CPF);
    }

    // Método para deletar um cliente por ID
    public void deleteClienteById(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteClienteById(id);
    }
}

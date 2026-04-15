package br.com.adacourse.services;

import br.com.adacourse.models.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ClienteService {

    //Apenas para teste sem persistência
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final AtomicLong numeroId = new AtomicLong();

    // Recebe e retorna ENTIDADE Cliente
    @Transactional
    public Cliente cadastrarCliente(Cliente cliente){
        cliente.persist();
        return cliente;
    }

    // Retorna lista de entidades
    public List<Cliente> listarClientes(){
        return Cliente.listAll();
    }

    // Buscar entidade por id
    public Cliente buscarClientePorId(Long id) {
        return Cliente.findById(id);
    }

    @Transactional
    // Atualizar e retornar entidade
    public Cliente atualizarCliente(Long id, Cliente atualizado){
        Cliente existente = Cliente.findById(id);
        if(existente == null){
            throw new EntityNotFoundException("Cliente não encontrado com id");
        }
        return validarEAtualizar(existente, atualizado);
    }

    private Cliente validarEAtualizar(Cliente existente, Cliente atualizado){
        if (atualizado.getCpf() != null && !atualizado.getCpf().equals(existente.getCpf())){
            throw new IllegalArgumentException("CPF não pode ser atualizado");
        }
        if (atualizado.getNome() != null) {
            existente.setNome(atualizado.getNome());
        }
        if (atualizado.getEmail() != null) {
            existente.setEmail(atualizado.getEmail());
        }
        if (atualizado.getSenha() != null) {
            String novaHash = BCrypt.hashpw(atualizado.getSenha(), BCrypt.gensalt(10));
            existente.setSenha(novaHash);
        }
         return existente;
    }
}

package br.com.adacourse.services.cliente;

import br.com.adacourse.models.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ClienteService {

    private static final List<Cliente> clientes = new ArrayList<>();
    private static final AtomicLong numeroId = new AtomicLong();

    // Recebe e retorna ENTIDADE Cliente
    public Cliente cadastrarCliente(Cliente cliente){
        cliente.setId(numeroId.incrementAndGet());
        clientes.add(cliente);
        return cliente;
    }

    // Retorna lista de entidades
    public List<Cliente> listarClientes(){
        return List.copyOf(clientes);
    }

    // Buscar entidade por id
    public Cliente buscarClientePorId(Long id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Atualizar e retornar entidade
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado){
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> validarEAtualizar(c, clienteAtualizado))
                .orElse(null);
    }

    private Cliente validarEAtualizar(Cliente existente, Cliente novo){
        if (novo.getCpf() != null && !novo.getCpf().equals(existente.getCpf())){
            throw new IllegalArgumentException("CPF não pode ser atualizado");
        }
        if (novo.getNome() != null) existente.setNome(novo.getNome());
        if (novo.getEmail() != null) existente.setEmail(novo.getEmail());
        if (novo.getSenha() != null) existente.setSenha(novo.getSenha());
        return existente;
    }
}

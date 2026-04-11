package br.com.adacourse.services.cliente;

import br.com.adacourse.models.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteService {

//    Para teste ainda sem banco de dados
    private static List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrarCliente(Cliente cliente){
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes(){
        return clientes;
    }

    public Cliente buscarClientePorId(Long id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente){
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> {
                    if (cliente.getCpf() != null && !cliente.getCpf().equals(c.getCpf())){
                        throw new IllegalArgumentException("CPF não pode ser atualizado");
                    }
                    c.setNome(cliente.getNome());
                    c.setEmail(cliente.getEmail());
                    c.setSenha(cliente.getSenha());
                    return c;
                })
                .orElse(null);
    }
}

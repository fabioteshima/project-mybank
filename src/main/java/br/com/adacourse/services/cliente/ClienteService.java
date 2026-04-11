package br.com.adacourse.services.cliente;

import br.com.adacourse.dto.cliente.ClienteRequestDTO;
import br.com.adacourse.dto.cliente.ClienteResponseDTO;
import br.com.adacourse.models.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ClienteService {

//    Para teste ainda sem banco de dados
    private static List<Cliente> clientes = new ArrayList<>();

    private static final AtomicLong numeroId = new AtomicLong();

    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO cliente){
        Cliente obj = new Cliente();
        obj.setId(numeroId.incrementAndGet());
        obj.setNome(cliente.nome());
        obj.setCpf(cliente.cpf());
        obj.setEmail(cliente.email());
        obj.setSenha(cliente.senha());
        clientes.add(obj);
        return ClienteResponseDTO.converterParaDTO(obj);
    }

    public List<ClienteResponseDTO> listarClientes(){
        return clientes.stream().map(ClienteResponseDTO::converterParaDTO).toList();
    }

    public ClienteResponseDTO buscarClientePorId(Long id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(ClienteResponseDTO::converterParaDTO)
                .orElse(null);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO cliente){
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> validarEAtualizar(c, cliente))
                .map(ClienteResponseDTO::converterParaDTO)
                .orElse(null);
    }

    private Cliente validarEAtualizar(Cliente c, ClienteRequestDTO cliente){
        if (cliente.cpf() != null && !cliente.cpf().equals(c.getCpf())){
            throw new IllegalArgumentException("CPF não pode ser atualizado");
        }
        c.setNome(cliente.nome());
        c.setEmail(cliente.email());
        c.setSenha(cliente.senha());
        return c;
    }
}

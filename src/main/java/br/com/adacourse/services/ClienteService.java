package br.com.adacourse.services;

import br.com.adacourse.models.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteService {

//    Para teste ainda sem banco de dados
    private static List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrar(Cliente cliente){
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listarTodos(){
        return clientes;
    }

    public Optional<Cliente> buscarPorId(Long id){
        Optional<Cliente> obj = clientes.stream().filter(x -> x.getId().equals(id)).findFirst();
        return obj;
    }


}

package br.com.adacourse.services;

import br.com.adacourse.models.Cliente;
import br.com.adacourse.models.Conta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ContaService {


    @Inject
    ClienteService clienteService;

//    public Conta criarConta(Conta conta){
//        if (conta == null || conta.getTitular() == null || conta.getTitular().getId() == null) {
//            throw new IllegalArgumentException("Requisição inválida: titular.id é obrigatório");
//        }
//        Cliente titular = clienteService.buscarClientePorId(conta.getTitular().getId());
//        if (titular == null) {
//            throw new IllegalArgumentException("Cliente com id " + conta.getTitular().getId() + " não encontrado");
//        }
//        conta.setNumero(String.valueOf(numeroConta.incrementAndGet()));
//        conta.setTitular(titular);
//        conta.setSaldo(0.00);
//        contas.add(conta);
//        return conta;
//    }

    public List<Conta> listarContas(){
        return Conta.listAll();
    }

    public Conta buscarContaPorId(Long id){
        return Conta.findById(id);
    }
}


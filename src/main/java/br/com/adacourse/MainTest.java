package br.com.adacourse;

import br.com.adacourse.models.Cliente;
import br.com.adacourse.services.ClienteService;

public class MainTest {

    public void main(String[] args) {

        Cliente cliente = new Cliente(1L, "Fabio", "123.456.789-00", "xxx", "123456");
        Cliente cliente1 = new Cliente(1L, "Ana", "123.456.789-00", "xxx", "123456");
        Cliente cliente2 = new Cliente(1L, "Gustavo", "123.456.789-00", "xxx", "123456");

        ClienteService cs = new ClienteService();

        Cliente clientCadastrado = cs.cadastrar(cliente);
        System.out.println(clientCadastrado);
        clientCadastrado = cs.cadastrar(cliente1);
        System.out.println(clientCadastrado);
        clientCadastrado = cs.cadastrar(cliente2);
        System.out.println(clientCadastrado);


//        Conta conta = new Conta(1L, "123456", TipoConta.CORRENTE, 1L);
//        Transacao transacao = new Transacao(1L, TipoTransacao.DEPOSITO, 3000.00, LocalDateTime.now(),
//                "12345", "67890");

        System.out.println(cs.listarTodos());
    }
}

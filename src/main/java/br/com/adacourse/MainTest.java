package br.com.adacourse;

import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Cliente;
import br.com.adacourse.models.Conta;
import br.com.adacourse.models.Transacao;

import java.time.LocalDateTime;

public class MainTest {

    public void main(String[] args) {

        Cliente cliente = new Cliente(1L, "Fabio", "123.456.789-00", "xxx", "123456");
        Conta conta = new Conta(1L, "123456", TipoConta.CORRENTE, 1L);
        Transacao transacao = new Transacao(1L, TipoTransacao.DEPOSITO, 3000.00, LocalDateTime.now(),
                "12345", "67890");

        System.out.println(conta);
        System.out.println(cliente);
        System.out.println(transacao);
    }
}

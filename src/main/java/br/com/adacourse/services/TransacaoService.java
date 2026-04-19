package br.com.adacourse.services;

import br.com.adacourse.models.Transacao;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TransacaoService {

//    public Transacao cadastrarTransacao(Transacao transacao){
//        Transacao entidade = new Transacao();
//        entidade.setId(numeroId.incrementAndGet());
//        entidade.setTipo(transacao.getTipo());
//        entidade.setValor(transacao.getValor());
//        entidade.setDataHora(transacao.getDataHora());
//        entidade.setConta_origem_id(transacao.getConta_origem_id());
//        entidade.setConta_destino_id(transacao.getConta_destino_id());
//        transacoes.add(entidade);
//        return entidade;
//    }

    public List<Transacao> consultaTransacao(){
        return Transacao.listAll();
    }
}

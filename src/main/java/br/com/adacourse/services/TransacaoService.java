package br.com.adacourse.services;

import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Conta;
import br.com.adacourse.models.Transacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TransacaoService {

    @Inject
    EntityManager em;

    @Transactional
    public Transacao depositar(Long contaId, Double valor){
        Conta entidade = Conta.findById(contaId);
        if(entidade == null){
            throw new IllegalArgumentException("Conta não encontrada");
        }
        if(entidade.getTipo() == TipoConta.ELETRONICA){
            throw new UnsupportedOperationException(("Não disponível para contas do tipo ELETRONICA"));
        }

        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.DEPOSITO);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setContaDestino(entidade);
        transacao.persist();
        em.flush();
        em.refresh(entidade); // força recarregar do banco
        return transacao;
    }

    @Transactional
    public Transacao sacar(Long contaId, Double valor){
        Conta entidade = Conta.findById(contaId);
        if(entidade == null){
            throw new IllegalArgumentException("Conta não encontrada");
        }
        if(entidade.getTipo() == TipoConta.ELETRONICA){
            throw new UnsupportedOperationException(("Conta do tipo ELETRONICA não permite saques"));
        }
        if(entidade.getSaldo() < valor){
            throw new IllegalStateException("Saldo insuficiente para realizar o saque");
        }

        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.SAQUE);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setContaOrigem(entidade);
        transacao.persist();
        em.flush();
        em.refresh(entidade);
        return transacao;
    }

    public List<Transacao> listarTransacoes(){
        return Transacao.listAll();
    }

    public Transacao buscarTransacaoPorId(Long id){
      return Transacao.findById(id);
    }
}

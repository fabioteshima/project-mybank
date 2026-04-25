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
            throw new UnsupportedOperationException(("Conta do tipo ELETRONICA não permite depósitos."));
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
            throw new IllegalArgumentException("ContaId não encontrada");
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

    @Transactional
    public Transacao transferir(Long contaOrigemId, Long contaDestinoId, Double valor){
        Conta contaOrigem = Conta.findById(contaOrigemId);
        if((contaOrigem == null)){
            throw new IllegalArgumentException("ContaId origem não encontrado");
        }
        Conta contaDestino = Conta.findById(contaDestinoId);
        if((contaDestino == null)){
            throw new IllegalArgumentException("ContaId destino não encontrado");
        }
        if (contaOrigem.getSaldo() < valor) {
            throw new IllegalStateException("Saldo insuficiente para realizar a transferência");
        }
        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.TRANSFERENCIA);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestino(contaDestino);
        transacao.persist();
        em.flush();
        // Recarrega as contas para atualizar saldo via @Formula
        em.refresh(contaOrigem);
        em.refresh(contaDestino);
        return transacao;
    }

    public List<Transacao> listarTransacoes(){
        return Transacao.listAll();
    }

    public Transacao buscarTransacaoPorId(Long id){
      return Transacao.findById(id);
    }

    public List<Transacao> buscarTransacoesPorConta(Long contaId) {
        return em.createQuery(
                        "SELECT t FROM Transacao t " +
                                "WHERE t.contaOrigem.id = :contaId OR t.contaDestino.id = :contaId " +
                                "ORDER BY t.dataHora ASC", Transacao.class)
                .setParameter("contaId", contaId)
                .getResultList();
    }
}

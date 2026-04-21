package br.com.adacourse.dto.transacao;

import br.com.adacourse.dto.cliente.ClienteRespDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Conta;
import br.com.adacourse.models.Transacao;

import java.time.LocalDateTime;

public record TransacaoRespDetalhadoDTO(
        Long id,
        TipoTransacao tipo,
        Double valor,
        Double saldoAtual,
        LocalDateTime dataHora,
        ContaResumoDTO conta,
        ContaResumoDTO contaDestino
) {

    // Conversão detalhada (para TransacaoResource)
    public static TransacaoRespDetalhadoDTO converterParaDTO(Transacao transacao){
        Conta contaOrigem = transacao.getContaOrigem();
        Conta contaDestino = transacao.getContaDestino();

        Double saldoAtual = contaDestino != null ? contaDestino.getSaldo() : null;

        return new TransacaoRespDetalhadoDTO(
                transacao.getId(),
                transacao.getTipo(),
                transacao.getValor(),
                saldoAtual,
                transacao.getDataHora(),
                contaOrigem != null ? ContaResumoDTO.converter(transacao.getContaOrigem()) : null,
                contaDestino != null ? ContaResumoDTO.converter(transacao.getContaDestino()) : null
        );
    }

    // DTO simplificado da conta
    public record ContaResumoDTO(Long id, String numero, TipoConta tipo, ClienteRespDTO titular) {
        public static ContaResumoDTO converter(Conta conta) {
            return new ContaResumoDTO(
                    conta.getId(),
                    conta.getNumero(),
                    conta.getTipo(),
                    ClienteRespDTO.converterParaDTO(conta.getTitular())
            );
        }
    }
}
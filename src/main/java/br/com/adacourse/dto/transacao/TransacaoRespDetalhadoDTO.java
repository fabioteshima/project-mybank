package br.com.adacourse.dto.transacao;

import br.com.adacourse.dto.conta.ContaRespResumidoDTO;
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
        ContaRespResumidoDTO conta,
        ContaRespResumidoDTO contaDestino
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
                    transacao.getContaOrigem() != null
                            ? ContaRespResumidoDTO.converterParaDTO(transacao.getContaOrigem())
                            : ContaRespResumidoDTO.converterParaDTO(transacao.getContaDestino()),
                    transacao.getContaDestino() != null
                            ? ContaRespResumidoDTO.converterParaDTO(transacao.getContaDestino())
                            : null
            );
        }
}
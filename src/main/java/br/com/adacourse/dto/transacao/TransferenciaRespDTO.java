package br.com.adacourse.dto.transacao;

import br.com.adacourse.dto.cliente.ClienteRespResumoDTO;
import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Conta;
import br.com.adacourse.models.Transacao;

import java.time.LocalDateTime;

public record TransferenciaRespDTO(

        Long id,
        TipoTransacao transacao,
        Double valor,
        Double saldoAtual,
        LocalDateTime dataHora,
        ContaResumoDTO contaOrigem,
        ContaResumoDTO contaDestino
) {
    public static TransferenciaRespDTO converterParaDTO(Transacao transacao, Conta contaOrigemAtualizada, Conta contaDestinoAtualizada) {
        return new TransferenciaRespDTO(
                transacao.getId(),
                transacao.getTipo(),
                transacao.getValor(),
                contaOrigemAtualizada.getSaldo(), // saldo da origem após a transferência
                transacao.getDataHora(),
                ContaResumoDTO.converter(contaOrigemAtualizada),
                ContaResumoDTO.converter(contaDestinoAtualizada)
        );
    }

    public record ContaResumoDTO(Long id, String numero, String tipo, ClienteRespResumoDTO titular) {
        public static ContaResumoDTO converter(Conta conta) {
            return new ContaResumoDTO(
                    conta.getId(),
                    conta.getNumero(),
                    conta.getTipo().toString(),
                    ClienteRespResumoDTO.converterParaDTO(conta.getTitular())
            );
        }
    }
}

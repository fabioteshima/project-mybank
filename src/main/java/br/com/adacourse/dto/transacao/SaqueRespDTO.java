package br.com.adacourse.dto.transacao;

import br.com.adacourse.dto.cliente.ClienteRespResumoDTO;
import br.com.adacourse.models.Conta;
import br.com.adacourse.models.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaqueRespDTO(

        Long id,
        String tipo,
        BigDecimal valor,
        BigDecimal saldoAtual,
        LocalDateTime dataHora,
        ContaResumoDTO conta
) {

        public static SaqueRespDTO converterParaDTO(Transacao transacao, Conta contaAtualizada) {
            return new SaqueRespDTO(
                    transacao.getId(),
                    transacao.getTipo().toString(),
                    transacao.getValor(),
                    contaAtualizada.getSaldo(), // saldo atualizado via @Formula
                    transacao.getDataHora(),
                    ContaResumoDTO.converter(contaAtualizada)
            );
        }

        public record ContaResumoDTO(Long id, String numero, ClienteRespResumoDTO titular) {
            public static ContaResumoDTO converter(Conta conta) {
                return new ContaResumoDTO(
                        conta.getId(),
                        conta.getNumero(),
                        ClienteRespResumoDTO.converterParaDTO(conta.getTitular())
                );
            }
        }
}

package br.com.adacourse.dto.transacao;

import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Transacao;

import java.time.LocalDateTime;

public record TransacaoResponseResumidoDTO(
        Long id,
        TipoTransacao tipo,
        Double valor,
        LocalDateTime dataHora
) {

    // Versão resumida para ContaResponseDTO
    public static TransacaoResponseResumidoDTO converterParaDTO(Transacao transacao){
        return new TransacaoResponseResumidoDTO(
                transacao.getId(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDataHora()
        );
    }
}

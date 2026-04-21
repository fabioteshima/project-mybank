package br.com.adacourse.dto.transacao;

import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Transacao;

import java.time.LocalDateTime;

public record TransacaoRespResumidoDTO(
        Long id,
        TipoTransacao tipo,
        Double valor,
        LocalDateTime dataHora
) {

    // Versão resumida para ContaResponseDTO
    public static TransacaoRespResumidoDTO converterParaDTO(Transacao transacao){
        return new TransacaoRespResumidoDTO(
                transacao.getId(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDataHora()
        );
    }
}

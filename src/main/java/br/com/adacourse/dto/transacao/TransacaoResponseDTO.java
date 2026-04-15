package br.com.adacourse.dto.transacao;

import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Transacao;

import java.time.LocalDateTime;

public record TransacaoResponseDTO(Long id, TipoTransacao tipo, Double valor, LocalDateTime dataHora){

    public TransacaoResponseDTO converterParaDTO(Transacao transacao){
        return new TransacaoResponseDTO(
                transacao.getId(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDataHora());
    }
}
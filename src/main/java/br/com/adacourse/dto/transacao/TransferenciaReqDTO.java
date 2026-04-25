package br.com.adacourse.dto.transacao;

import jakarta.validation.constraints.NotNull;

public record TransferenciaReqDTO(

        @NotNull
        ContaIdDTO contaDestino,

        @NotNull
        Double valor
){}

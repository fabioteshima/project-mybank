package br.com.adacourse.dto.transacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferenciaReqDTO(

        @NotNull
        ContaIdDTO contaDestino,

        @NotNull
        BigDecimal valor
){}

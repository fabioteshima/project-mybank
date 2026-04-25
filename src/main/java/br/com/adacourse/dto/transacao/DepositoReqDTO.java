package br.com.adacourse.dto.transacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositoReqDTO(

        @NotNull(message = "Valor é obrigatório")
        BigDecimal valor
) {}

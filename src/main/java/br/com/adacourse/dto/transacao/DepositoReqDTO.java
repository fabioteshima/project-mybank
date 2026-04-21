package br.com.adacourse.dto.transacao;

import jakarta.validation.constraints.NotNull;

public record DepositoReqDTO(

        @NotNull(message = "Valor é obrigatório")
        Double valor
) {}

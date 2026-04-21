package br.com.adacourse.dto.transacao;

import jakarta.validation.constraints.NotNull;

public record SaqueReqDTO(

        @NotNull(message = "Valor é obrigatório")
        Double valor
) {}

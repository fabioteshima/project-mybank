package br.com.adacourse.dto.conta;

import br.com.adacourse.enums.TipoConta;
import jakarta.validation.constraints.NotNull;

public record ContaReqDTO(

        @NotNull(message = "Tipo da conta é necessário")
        TipoConta tipo,

        @NotNull
        ClienteIdDTO cliente) {
}


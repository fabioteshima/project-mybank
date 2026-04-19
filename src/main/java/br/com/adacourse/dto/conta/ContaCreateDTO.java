package br.com.adacourse.dto.conta;

import br.com.adacourse.enums.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaCreateDTO(

        @NotNull(message = "Tipo da conta é necessário")
        TipoConta tipo,

        @NotNull
        ClienteIdDTO cliente) {
}


package br.com.adacourse.dto.transacao;

import jakarta.validation.constraints.NotNull;

public record ContaIdDTO(

        @NotNull
        Long id
) {}

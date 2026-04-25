package br.com.adacourse.dto.conta;

import jakarta.validation.constraints.NotNull;

public record ClienteIdDTO(

        @NotNull
        Long id
) {}


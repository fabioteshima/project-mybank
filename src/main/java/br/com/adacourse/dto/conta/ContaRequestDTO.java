package br.com.adacourse.dto.conta;

import br.com.adacourse.enums.TipoConta;

public record ContaRequestDTO(String numero, TipoConta tipo, Double saldo, ClienteIdDTO cliente) {
}


package br.com.adacourse.dto.cliente;

import br.com.adacourse.models.Cliente;

public record ClienteRespResumoDTO(Long id, String nome) {

    public static ClienteRespResumoDTO converterParaDTO(Cliente cliente) {
        return new ClienteRespResumoDTO(
                cliente.getId(),
                cliente.getNome()
        );
    }
}

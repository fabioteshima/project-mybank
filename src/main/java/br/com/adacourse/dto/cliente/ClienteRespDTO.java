package br.com.adacourse.dto.cliente;

import br.com.adacourse.models.Cliente;

public record ClienteRespDTO(Long id, String nome, String email) {

    public static ClienteRespDTO converterParaDTO(Cliente cliente) {
        return new ClienteRespDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}

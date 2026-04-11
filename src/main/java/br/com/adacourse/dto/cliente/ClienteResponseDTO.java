package br.com.adacourse.dto.cliente;

import br.com.adacourse.models.Cliente;

public record ClienteResponseDTO(Long id, String nome, String email) {

    public static ClienteResponseDTO converterParaDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}

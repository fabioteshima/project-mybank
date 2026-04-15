package br.com.adacourse.dto.conta;

import br.com.adacourse.dto.cliente.ClienteResponseDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.models.Conta;

public record ContaResponseDTO(Long id, String numero, TipoConta tipo, Double saldo, ClienteResponseDTO titular) {

    public static ContaResponseDTO converteParaDTO(Conta conta){
        return new ContaResponseDTO(
                conta.getId(),
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo(),
                ClienteResponseDTO.converterParaDTO(conta.getTitular())
        );
    }
}


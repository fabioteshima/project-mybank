package br.com.adacourse.dto.conta;

import br.com.adacourse.dto.cliente.ClienteResponseDTO;
import br.com.adacourse.dto.transacao.TransacaoResponseDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.models.Conta;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ContaResponseDTO(
        Long id,
        String numero,
        TipoConta tipo,
        ClienteResponseDTO titular,
        List<TransacaoResponseDTO> transacoes) {

    public static ContaResponseDTO converteParaDTO(Conta conta){
        List<TransacaoResponseDTO> todasTransacoes = Stream.concat(
                conta.getTransacoesOrigem().stream(),
                conta.getTransacaosDestino().stream())
                .map(TransacaoResponseDTO::converterParaDTO)
                .collect(Collectors.toList());

        return new ContaResponseDTO(
                conta.getId(),
                conta.getNumero(),
                conta.getTipo(),
                ClienteResponseDTO.converterParaDTO(conta.getTitular()),
                todasTransacoes
        );
    }
}


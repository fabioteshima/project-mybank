package br.com.adacourse.dto.conta;

import br.com.adacourse.dto.cliente.ClienteRespDTO;
import br.com.adacourse.dto.transacao.TransacaoRespResumidoDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.models.Conta;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ContaRespDTO(
        Long id,
        String numero,
        TipoConta tipo,
        Double saldo,
        ClienteRespDTO titular,
        List<TransacaoRespResumidoDTO> transacoes) {

    public static ContaRespDTO converteParaDTO(Conta conta){
        List<TransacaoRespResumidoDTO> todasTransacoes = Stream.concat(
                conta.getTransacoesOrigem().stream(),
                conta.getTransacoesDestino().stream())
                .map(TransacaoRespResumidoDTO::converterParaDTO)
                .collect(Collectors.toList());

        return new ContaRespDTO(
                conta.getId(),
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo(),
                ClienteRespDTO.converterParaDTO(conta.getTitular()),
                todasTransacoes
        );
    }
}


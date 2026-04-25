package br.com.adacourse.dto.conta;

import br.com.adacourse.dto.cliente.ClienteRespDTO;
import br.com.adacourse.dto.transacao.TransacaoRespResumidoDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.models.Conta;
import br.com.adacourse.models.Transacao;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ContaRespDetalhadoDTO(
        Long id,
        String numero,
        TipoConta tipo,
        Double saldo,
        ClienteRespDTO titular,
        List<TransacaoRespResumidoDTO> transacoes,
        Map<String, String> _links) {

        public static ContaRespDetalhadoDTO converteParaDTO(Conta conta){
            LocalDate hoje = LocalDate.now();
            List<TransacaoRespResumidoDTO> todasTransacoes = Stream.concat(
                    conta.getTransacoesOrigem().stream(),
                    conta.getTransacoesDestino().stream())
                    .filter(t -> t.getDataHora().toLocalDate().equals(hoje))
                    .sorted(Comparator.comparing(Transacao::getDataHora))
                    .map(TransacaoRespResumidoDTO::converterParaDTO)
                    .collect(Collectors.toList());

            Map<String, String> links = new HashMap<>();
            links.put("transacoes", "/transacoes?contaId=" + conta.getId());

            return new ContaRespDetalhadoDTO(
                    conta.getId(),
                    conta.getNumero(),
                    conta.getTipo(),
                    conta.getSaldo(),
                    ClienteRespDTO.converterParaDTO(conta.getTitular()),
                    todasTransacoes,
                    links
            );
        }
}


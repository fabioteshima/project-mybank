package br.com.adacourse.dto.transacao;

public record TransferenciaReqDTO(

        ContaIdDTO contaDestino,
        Double valor
){}

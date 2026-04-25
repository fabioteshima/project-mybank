package br.com.adacourse.dto.transacao;

import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoReqDTO(

        TipoTransacao tipo,
        BigDecimal valor,
        LocalDateTime dataHora,
        Conta conta_origem_id,
        Conta conta_destino_id){
}

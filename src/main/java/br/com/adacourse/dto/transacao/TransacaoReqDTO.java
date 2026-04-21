package br.com.adacourse.dto.transacao;

import br.com.adacourse.enums.TipoTransacao;
import br.com.adacourse.models.Conta;

import java.time.LocalDateTime;

public record TransacaoReqDTO(TipoTransacao tipo, Double valor, LocalDateTime dataHora,
                              Conta conta_origem_id, Conta conta_destino_id){
}

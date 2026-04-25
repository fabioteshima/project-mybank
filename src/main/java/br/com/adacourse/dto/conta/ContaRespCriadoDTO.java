package br.com.adacourse.dto.conta;

import br.com.adacourse.dto.cliente.ClienteRespDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.models.Conta;

import java.math.BigDecimal;

public record ContaRespCriadoDTO(

        Long id,
        String numero,
        TipoConta tipo,
        BigDecimal Saldo,
        ClienteRespDTO titular
) {

        public static ContaRespCriadoDTO converterParaDTO(Conta conta){
            return new ContaRespCriadoDTO(
                    conta.getId(),
                    conta.getNumero(),
                    conta.getTipo(),
                    conta.getSaldo(),
                    ClienteRespDTO.converterParaDTO(conta.getTitular())
            );
        }
}

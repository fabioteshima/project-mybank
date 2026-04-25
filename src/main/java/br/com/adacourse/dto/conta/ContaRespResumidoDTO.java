package br.com.adacourse.dto.conta;

import br.com.adacourse.dto.cliente.ClienteRespDTO;
import br.com.adacourse.enums.TipoConta;
import br.com.adacourse.models.Conta;

public record ContaRespResumidoDTO(

        Long id,
        String numero,
        TipoConta tipo,
        ClienteRespDTO titular
) {

        public static ContaRespResumidoDTO converterParaDTO(Conta conta){
            return new ContaRespResumidoDTO(
                    conta.getId(),
                    conta.getNumero(),
                    conta.getTipo(),
                    ClienteRespDTO.converterParaDTO(conta.getTitular())
            );
        }
}

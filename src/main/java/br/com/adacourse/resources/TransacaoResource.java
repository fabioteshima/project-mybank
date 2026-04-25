package br.com.adacourse.resources;

import br.com.adacourse.dto.transacao.TransacaoRespDetalhadoDTO;
import br.com.adacourse.models.Transacao;
import br.com.adacourse.services.TransacaoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoResource {

    @Inject
    TransacaoService service;

    @GET
    @RolesAllowed("GERENTE")
    public Response buscarTransacoes(@QueryParam("contaId") Long contaId) {
        if (contaId != null) {
            List<Transacao> transacoes = service.buscarTransacoesPorConta(contaId);
            List<TransacaoRespDetalhadoDTO> historico = transacoes.stream()
                    .sorted(Comparator.comparing(Transacao::getDataHora))
                    .map(TransacaoRespDetalhadoDTO::converterParaDTO)
                    .collect(Collectors.toList());
            return Response.ok(historico).build();
        } else {
              List<TransacaoRespDetalhadoDTO> transacoes = service.listarTransacoes().stream()
                    .map(TransacaoRespDetalhadoDTO::converterParaDTO)
                    .collect(Collectors.toList());
            return Response.ok(transacoes).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"GERENTE", "CLIENTE"})
    public Response buscarTransacaoPorId(@PathParam("id") Long id){
        Transacao entidade = service.buscarTransacaoPorId(id);
        if(entidade == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Transação não encontrada\"}")
                    .build();
        }
        return Response.ok(TransacaoRespDetalhadoDTO.converterParaDTO(entidade)).build();
    }
}

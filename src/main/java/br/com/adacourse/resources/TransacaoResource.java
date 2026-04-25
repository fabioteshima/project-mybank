package br.com.adacourse.resources;

import br.com.adacourse.dto.transacao.TransacaoRespDetalhadoDTO;
import br.com.adacourse.models.Transacao;
import br.com.adacourse.services.TransacaoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
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
    @RolesAllowed({"GERENTE", "CLIENTE"})
    public Response buscarTransacoes(@QueryParam("contaId") Long contaId, @Context SecurityContext sc) {
        if (contaId != null) {
            List<Transacao> transacoes = service.buscarTransacoesPorConta(contaId);
            String usuarioLogado = sc.getUserPrincipal().getName();
            if(sc.isUserInRole("GERENTE")) {
                List<TransacaoRespDetalhadoDTO> historico = transacoes.stream()
                        .sorted(Comparator.comparing(Transacao::getDataHora))
                        .map(TransacaoRespDetalhadoDTO::converterParaDTO)
                        .collect(Collectors.toList());
                return Response.ok(historico).build();
            }
            boolean autorizado = transacoes.stream().anyMatch(t ->
                    (t.getContaOrigem() != null && usuarioLogado.equals(t.getContaOrigem().getTitular().getEmail())) ||
                            (t.getContaDestino() != null && usuarioLogado.equals(t.getContaDestino().getTitular().getEmail()))
            );
            if (sc.isUserInRole("CLIENTE") && autorizado) {
                List<TransacaoRespDetalhadoDTO> historico = transacoes.stream()
                        .sorted(Comparator.comparing(Transacao::getDataHora))
                        .map(TransacaoRespDetalhadoDTO::converterParaDTO)
                        .collect(Collectors.toList());
                return Response.ok(historico).build();
            }
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"erro\":\"Acesso não autorizado\"}")
                    .build();
        } else {
            if(sc.isUserInRole("GERENTE")){
                List<TransacaoRespDetalhadoDTO> transacoes = service.listarTransacoes().stream()
                        .map(TransacaoRespDetalhadoDTO::converterParaDTO)
                        .collect(Collectors.toList());
                return Response.ok(transacoes).build();
            }
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"erro\":\"Apenas gerente pode listar todas as transações\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"GERENTE", "CLIENTE"})
    public Response buscarTransacaoPorId(@PathParam("id") Long id, @Context SecurityContext sc){
        Transacao entidade = service.buscarTransacaoPorId(id);
        if(entidade == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Transação não encontrada\"}")
                    .build();
        }
        String usuarioLogado = sc.getUserPrincipal().getName();
        if (sc.isUserInRole("GERENTE")) {
            return Response.ok(TransacaoRespDetalhadoDTO.converterParaDTO(entidade)).build();
        }
        boolean autorizado =
                (entidade.getContaOrigem() != null && usuarioLogado.equals(entidade.getContaOrigem().getTitular().getEmail())) ||
                        (entidade.getContaDestino() != null && usuarioLogado.equals(entidade.getContaDestino().getTitular().getEmail()));
        if (sc.isUserInRole("CLIENTE") && autorizado) {
            return Response.ok(TransacaoRespDetalhadoDTO.converterParaDTO(entidade)).build();
        }
        return Response.status(Response.Status.FORBIDDEN)
                .entity("{\"erro\":\"Acesso não autorizado\"}")
                .build();
    }
}

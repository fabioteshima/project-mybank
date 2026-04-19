package br.com.adacourse.resources;


import br.com.adacourse.dto.transacao.TransacaoRequestDTO;
import br.com.adacourse.models.Transacao;
import br.com.adacourse.services.TransacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoResource {

    @Inject
    TransacaoService service;

//    @POST
//    public Response cadastrarTransacao(TransacaoRequestDTO dto){
//        Transacao entidade = new Transacao();
//        entidade.setTipo(dto.tipo());
//
//
//        // Continuar aqui......
//
//
//        return null;
//    }

    @GET
    public Response listarTransacoes(Transacao transacao){
        return Response.ok(transacao).build();
    }
}

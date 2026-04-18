package br.com.adacourse.resources;

import br.com.adacourse.dto.conta.ContaResponseDTO;
import br.com.adacourse.services.ContaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/contas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContaResource {

    @Inject
    ContaService service;

//    @POST
//    public Response criarConta(ContaRequestDTO dto){
//        try {
//            Conta entidade = new Conta();
//            entidade.setNumero(dto.numero());
//            entidade.setTipo(dto.tipo());
//            entidade.setSaldo(dto.saldo());
//            Cliente titular = new Cliente();
//            titular.setId(dto.cliente().id());
//            entidade.setTitular(titular);
//
//            Conta criada = service.criarConta(entidade);
//            ContaResponseDTO responseDTO = ContaResponseDTO.converteParaDTO(criada);
//            return Response.created(URI.create("/contas/" + criada.getId())).entity(responseDTO).build();
//        } catch (IllegalArgumentException e) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("{\"erro\":\"" + e.getMessage() + "\"}")
//                    .build();
//        }
//    }
//
    @GET
    public Response listarContas(){
        List<ContaResponseDTO> lista = service.listarContas()
                .stream()
                .map(ContaResponseDTO::converteParaDTO)
                .collect(Collectors.toList());
        return Response.ok(lista).build();
    }
//
//    @Path("/{id}")
//    @GET
//    public Response buscarContaPorId(@PathParam("id") Long id){
//        Conta entidade = service.buscarContaPorId(id);
//        if (entidade == null) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("{\"erro\":\"Conta não encontrada\"}")
//                    .build();
//        }
//        ContaResponseDTO responseDTO = ContaResponseDTO.converteParaDTO(entidade);
//        return Response.ok(responseDTO).build();
//    }
}
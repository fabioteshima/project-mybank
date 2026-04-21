package br.com.adacourse.resources;

import br.com.adacourse.dto.conta.ContaCreateDTO;
import br.com.adacourse.dto.conta.ContaResponseDTO;
import br.com.adacourse.models.Cliente;
import br.com.adacourse.models.Conta;
import br.com.adacourse.services.ContaService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/contas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContaResource {

    @Inject
    ContaService service;

    @POST
    @RolesAllowed("GERENTE")
    public Response criarConta(@Valid ContaCreateDTO dto){
        try {
            Conta entidade = new Conta();
            entidade.setTipo(dto.tipo());

            Cliente titular = new Cliente();
            titular.setId(dto.cliente().id());

            entidade.setTitular(titular);

            Conta criada = service.criarConta(entidade);
            ContaResponseDTO responseDTO = ContaResponseDTO.converteParaDTO(criada);
            return Response.created(URI.create("/contas/" + criada.getId())).entity(responseDTO).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @RolesAllowed("GERENTE")
    public Response listarContas(){
        List<ContaResponseDTO> lista = service.listarContas()
                .stream()
                .map(ContaResponseDTO::converteParaDTO)
                .collect(Collectors.toList());
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"GERENTE", "CLIENTE"})
    public Response buscarContaPorId(@PathParam("id") Long id, @Context SecurityContext sc){
        Conta entidade = service.buscarContaPorId(id);
        if(entidade == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Conta Id não encontrada\"}")
                    .build();
        }
        String usuarioLogado = sc.getUserPrincipal().getName();
        if (sc.isUserInRole("GERENTE")) {
            return Response.ok(ContaResponseDTO.converteParaDTO(entidade)).build();
        }
        if (sc.isUserInRole("CLIENTE") && entidade.getTitular().getEmail().equals(usuarioLogado)) {
            return Response.ok(ContaResponseDTO.converteParaDTO(entidade)).build();
        }
        return Response.status(Response.Status.FORBIDDEN)
                .entity("{\"erro\":\"Acesso não autorizado\"}")
                .build();
    }
}
package br.com.adacourse.resources;

import br.com.adacourse.dto.cliente.ClienteCreateDTO;
import br.com.adacourse.dto.cliente.ClienteResponseDTO;
import br.com.adacourse.dto.cliente.ClienteUpdateDTO;
import br.com.adacourse.enums.TipoCliente;
import br.com.adacourse.models.Cliente;
import br.com.adacourse.services.ClienteService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @POST
    @RolesAllowed("GERENTE")
    public Response cadastrarCliente(@Valid ClienteCreateDTO dto){
        Cliente entidade = new Cliente();
        entidade.setNome(dto.nome());
        entidade.setCpf(dto.cpf());
        entidade.setEmail(dto.email());
        entidade.setSenha(dto.senha());
        entidade.setRole(TipoCliente.CLIENTE);

        Cliente criado = service.cadastrarCliente(entidade);
        ClienteResponseDTO responseDTO = ClienteResponseDTO.converterParaDTO(criado);
        return Response.created(URI.create("/clientes/" + criado.getId())).entity(responseDTO).build();
    }

    @GET
    @RolesAllowed("GERENTE")
    public Response listarClientes(){
        List<ClienteResponseDTO> lista = service.listarClientes()
                .stream()
                .map(ClienteResponseDTO::converterParaDTO)
                .collect(Collectors.toList());
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("GERENTE")
    public Response buscarClientePorId(@PathParam("id") Long id){
        Cliente entidade = service.buscarClientePorId(id);
        if (entidade == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Cliente não encontrado\"}")
                    .build();
        }
        return Response.ok(ClienteResponseDTO.converterParaDTO(entidade)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("GERENTE")
    public Response atualizarCliente(@PathParam("id") Long id, @Valid ClienteUpdateDTO dto) {
        try {
            Cliente entidade = new Cliente();
            entidade.setNome(dto.nome());
            entidade.setEmail(dto.email());
            entidade.setSenha(dto.senha());

            Cliente atualizado = service.atualizarCliente(id, entidade);
            if (atualizado == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\":\"Cliente Id não encontrado\"}")
                        .build();
            }
            return Response.ok(ClienteResponseDTO.converterParaDTO(atualizado)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}

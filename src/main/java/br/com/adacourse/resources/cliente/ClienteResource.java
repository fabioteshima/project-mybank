package br.com.adacourse.resources.cliente;

import br.com.adacourse.dto.cliente.ClienteRequestDTO;
import br.com.adacourse.dto.cliente.ClienteResponseDTO;
import br.com.adacourse.models.Cliente;
import br.com.adacourse.services.cliente.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @POST
    public Response cadastrarCliente(ClienteRequestDTO cliente){
        ClienteResponseDTO obj = service.cadastrarCliente(cliente);
        return Response.ok(obj).build();
    }

    @GET
    public Response listarClientes(){
        List<ClienteResponseDTO> obj = service.listarClientes();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id){
        ClienteResponseDTO obj = service.buscarClientePorId(id);
        return Response.ok(obj).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") Long id, ClienteRequestDTO cliente){
        try {
            ClienteResponseDTO obj = service.atualizarCliente(id, cliente);
            if(obj == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\":\"Cliente não encontrado\"}")
                        .build();
            }
            return Response.ok(obj).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
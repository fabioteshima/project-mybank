package br.com.adacourse.resources.cliente;

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
    public Response cadastrarCliente(Cliente cliente){
        Cliente obj = service.cadastrarCliente(cliente);
        return Response.ok(obj).build();
    }

    @GET
    public Response listarClientes(){
        List<Cliente> obj = service.listarClientes();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id){
        Cliente obj = service.buscarClientePorId(id);
        return Response.ok(obj).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") Long id, Cliente cliente){
        try {
            Cliente obj = service.atualizarCliente(id, cliente);
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
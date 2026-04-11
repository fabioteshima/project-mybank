package br.com.adacourse.resources.cliente;

import br.com.adacourse.models.Cliente;
import br.com.adacourse.services.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @POST
    public Response cadastrar(Cliente cliente){
        Cliente obj = service.cadastrar(cliente);
        return Response.ok(obj).build();
    }

    @GET
    public Response listar(){
        List<Cliente> clientes = service.listarTodos();
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id){
        Optional<Cliente> obj = service.buscarPorId(id);
        return Response.ok(obj).build();
    }
}

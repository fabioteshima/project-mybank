package br.com.adacourse.resources;

import br.com.adacourse.dto.auth.AuthCreateDTO;
import br.com.adacourse.services.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService service;

    @POST
    @Path("/login")
    public Response login(@Valid AuthCreateDTO dto){
        String token = service.autenticacao(dto.email(), dto.senha());
        return Response.ok(Map.of("token", token)).build();
    }
}

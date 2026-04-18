package br.com.adacourse.exceptions.mappers;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CampoNaoPermitidoExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

    @Override
    public Response toResponse(UnrecognizedPropertyException exception) {
        String campo = exception.getPropertyName();
        String mensagem = "Campo '" + campo + "' não é permitido na atualização.";
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"" + mensagem + "\"}")
                .build();
    }
}

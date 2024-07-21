package org.acme.exception;

import java.util.List;

import org.acme.utils.ResponseEnvelope;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        log.error("Handling WebApplicationException: {}", exception.getMessage(), exception);

        int statusCode = exception.getResponse().getStatus();
        String errorMessage = exception.getMessage();

        ResponseEnvelope<String> responseEnvelope = ResponseEnvelope.<String>builder().errors(List.of(errorMessage))
                .build();

        return Response.status(statusCode)
                .entity(responseEnvelope)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

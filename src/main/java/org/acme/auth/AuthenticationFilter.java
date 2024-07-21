package org.acme.auth;

import java.io.IOException;
import java.util.List;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authorizationHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER);

        if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String authorizationHeader = authorizationHeaders.get(0);
        if (!authorizationHeader.startsWith(BEARER_PREFIX)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length());

        if (!isTokenValid(token))
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private boolean isTokenValid(String token) {
        // Implement your token validation logic here
        // For example, decode and verify the token
        return "valid_token".equals(token);
    }

}

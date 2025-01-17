package org.acme;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api/v1")
@OpenAPIDefinition(info = @Info(title = "Car sharing", version = "1.0.0"))
public class ApiApplication extends Application {

}

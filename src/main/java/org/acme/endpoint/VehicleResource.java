package org.acme.endpoint;

import java.util.List;

import org.acme.dto.VehicleDto;
import org.acme.service.VehicleService;
import org.acme.utils.PageRequest;
import org.acme.utils.ResponseEnvelope;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ApplicationScoped
@Path("/vehicles")
@Tag(name = "Vehicles")
public class VehicleResource {

    private final VehicleService vehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope<List<VehicleDto>> getAllVehicles(@BeanParam PageRequest pageRequest) {
        List<VehicleDto> vehicles = vehicleService
                .getAllVehicles(Page.of(pageRequest.getPageIndex(), pageRequest.getPageSize()));
        return ResponseEnvelope.<List<VehicleDto>>builder().data(vehicles).build();
    }

    @GET
    @Path("{vehicleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope<VehicleDto> getVehicle(@PathParam("vehicleId") long vehicleId) {
        VehicleDto vehicle = vehicleService.getVehicle(vehicleId).orElseThrow(NotFoundException::new);
        return ResponseEnvelope.<VehicleDto>builder().data(vehicle).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(VehicleDto vehicleDto) {
        VehicleDto vehicle = vehicleService.createVehicle(vehicleDto);
        ResponseEnvelope<VehicleDto> responseEnvelope = ResponseEnvelope.<VehicleDto>builder().data(vehicle).build();
        return Response.status(Status.CREATED).entity(responseEnvelope).build();
    }

    @PUT
    @Path("{vehicleId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope<VehicleDto> updateVehicle(@PathParam("vehicleId") long vehicleId, VehicleDto vehicleDto) {
        VehicleDto vehicle = vehicleService.updateVehicle(vehicleId, vehicleDto).orElseThrow(NotFoundException::new);
        return ResponseEnvelope.<VehicleDto>builder().data(vehicle).build();
    }

    @DELETE
    @Path("{vehicleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVehicle(@PathParam("vehicleId") long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return Response.status(Status.NO_CONTENT).build();
    }

}

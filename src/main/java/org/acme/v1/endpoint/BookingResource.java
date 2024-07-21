package org.acme.v1.endpoint;

import java.util.List;

import org.acme.dto.BookingDto;
import org.acme.service.BookingService;
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
@Path("/bookings")
@Tag(name = "Bookings")
public class BookingResource {

    private final BookingService bookingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope<List<BookingDto>> getAllBookings(@BeanParam PageRequest pageRequest) {
        List<BookingDto> bookings = bookingService
                .getAllBookings(Page.of(pageRequest.getPageIndex(), pageRequest.getPageSize()));
        return ResponseEnvelope.<List<BookingDto>>builder().data(bookings).build();
    }

    @GET
    @Path("{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope<BookingDto> getBooking(@PathParam("bookingId") long bookingId) {
        BookingDto booking = bookingService.getBooking(bookingId).orElseThrow(NotFoundException::new);
        return ResponseEnvelope.<BookingDto>builder().data(booking).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(BookingDto bookingDto) {
        BookingDto booking = bookingService.createBooking(bookingDto);
        ResponseEnvelope<BookingDto> responseEnvelope = ResponseEnvelope.<BookingDto>builder().data(booking).build();
        return Response.status(Status.CREATED).entity(responseEnvelope).build();
    }

    @PUT
    @Path("{bookingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope<BookingDto> updateBooking(@PathParam("bookingId") long bookingId, BookingDto bookingDto) {
        BookingDto booking = bookingService.updateBooking(bookingId, bookingDto).orElseThrow(NotFoundException::new);
        return ResponseEnvelope.<BookingDto>builder().data(booking).build();
    }

    @DELETE
    @Path("{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBooking(@PathParam("bookingId") long bookingId) {
        bookingService.deleteBooking(bookingId);
        return Response.status(Status.NO_CONTENT).build();
    }

}

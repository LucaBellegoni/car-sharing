package org.acme.repository;

import org.acme.model.Booking;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {

}

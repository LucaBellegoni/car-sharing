package org.acme.repository;

import org.acme.model.Vehicle;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ApplicationScoped
public class VehicleRepository implements PanacheRepository<Vehicle> {

}

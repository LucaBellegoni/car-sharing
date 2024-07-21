package org.acme.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.acme.dto.VehicleDto;
import org.acme.mapping.VehicleMapper;
import org.acme.model.Vehicle;
import org.acme.repository.VehicleRepository;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@RequiredArgsConstructor
@ApplicationScoped
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<VehicleDto> getAllVehicles(Page page) {
        log.info("Fetching all vehicles with page index: {} and page size: {}", page.index, page.size);

        List<Vehicle> vehicles = vehicleRepository.findAll().page(page).list();
        List<VehicleDto> vehicleDtos = vehicles.stream()
                .map(VehicleMapper.INSTANCE::vehicleToVehicleDto)
                .collect(Collectors.toList());

        log.info("Retrieved {} vehicles", vehicleDtos.size());

        return vehicleDtos;
    }

    public Optional<VehicleDto> getVehicle(long vehicleId) {
        log.info("Fetching vehicle with ID: {}", vehicleId);

        Optional<VehicleDto> vehicleDto = vehicleRepository.findByIdOptional(vehicleId)
                .map(VehicleMapper.INSTANCE::vehicleToVehicleDto);

        log.info("Vehicle with ID {} retrieved", vehicleId);

        return vehicleDto;
    }

    @Transactional
    public VehicleDto createVehicle(@NonNull VehicleDto vehicleDto) {
        log.info("Creating a new vehicle");

        Vehicle vehicle = VehicleMapper.INSTANCE.vehicleDtoToVehicle(vehicleDto);
        vehicle.setLastModifiedDate(Instant.now());

        vehicleRepository.persist(vehicle);
        VehicleDto createdVehicleDto = VehicleMapper.INSTANCE.vehicleToVehicleDto(vehicle);

        log.info("Created vehicle with ID: {}", createdVehicleDto.getId());

        return createdVehicleDto;
    }

    @Transactional
    public Optional<VehicleDto> updateVehicle(long vehicleId, @NonNull VehicleDto vehicleDto) {
        log.info("Updating vehicle with ID: {}", vehicleId);

        return vehicleRepository.findByIdOptional(vehicleId).map(vehicle -> {
            VehicleMapper.INSTANCE.updateVehicleFromVehicleDto(vehicleDto, vehicle);
            vehicleRepository.persist(vehicle);
            VehicleDto updatedVehicleDto = VehicleMapper.INSTANCE.vehicleToVehicleDto(vehicle);

            log.info("Updated vehicle with ID: {}", vehicleId);

            return updatedVehicleDto;
        });
    }

    @Transactional
    public boolean deleteVehicle(long vehicleId) {
        log.info("Deleting vehicle with ID: {}", vehicleId);

        boolean deleted = vehicleRepository.deleteById(vehicleId);

        if (deleted) {
            log.info("Deleted vehicle with ID: {}", vehicleId);
        } else {
            log.warn("Failed to delete vehicle with ID: {}", vehicleId);
        }

        return deleted;
    }
}

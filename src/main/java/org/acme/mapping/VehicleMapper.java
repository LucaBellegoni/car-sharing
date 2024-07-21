package org.acme.mapping;

import org.acme.dto.VehicleDto;
import org.acme.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDto vehicleToVehicleDto(Vehicle vehicle);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedUserId", ignore = true)
    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);
    
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedUserId", ignore = true)
    void updateVehicleFromVehicleDto(VehicleDto vehicleDto, @MappingTarget Vehicle vehicle);

}

package org.acme.mapping;

import org.acme.dto.BookingDto;
import org.acme.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDto bookingToBookingDto(Booking booking);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedUserId", ignore = true)
    Booking bookingDtoToBooking(BookingDto bookingDto);
    
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedUserId", ignore = true)
    void updateBookingFromBookingDto(BookingDto bookingDto, @MappingTarget Booking booking);

}

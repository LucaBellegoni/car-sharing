package org.acme.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.acme.dto.BookingDto;
import org.acme.mapping.BookingMapper;
import org.acme.model.Booking;
import org.acme.repository.BookingRepository;

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
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<BookingDto> getAllBookings(Page page) {
        log.info("Fetching all bookings with page index: {} and page size: {}", page.index, page.size);

        List<Booking> bookings = bookingRepository.findAll().page(page).list();
        List<BookingDto> bookingDtos = bookings.stream()
                .map(BookingMapper.INSTANCE::bookingToBookingDto)
                .collect(Collectors.toList());

        log.info("Retrieved {} bookings", bookingDtos.size());

        return bookingDtos;
    }

    public Optional<BookingDto> getBooking(long bookingId) {
        log.info("Fetching booking with ID: {}", bookingId);

        Optional<BookingDto> bookingDto = bookingRepository.findByIdOptional(bookingId)
                .map(BookingMapper.INSTANCE::bookingToBookingDto);

        log.info("Booking with ID {} retrieved", bookingId);

        return bookingDto;
    }

    @Transactional
    public BookingDto createBooking(@NonNull BookingDto bookingDto) {
        log.info("Creating a new booking");

        Booking booking = BookingMapper.INSTANCE.bookingDtoToBooking(bookingDto);
        booking.setLastModifiedDate(Instant.now());

        bookingRepository.persist(booking);
        BookingDto createdBookingDto = BookingMapper.INSTANCE.bookingToBookingDto(booking);

        log.info("Created booking with ID: {}", createdBookingDto.getId());

        return createdBookingDto;
    }

    @Transactional
    public Optional<BookingDto> updateBooking(long bookingId, @NonNull BookingDto bookingDto) {
        log.info("Updating booking with ID: {}", bookingId);

        return bookingRepository.findByIdOptional(bookingId).map(booking -> {
            BookingMapper.INSTANCE.updateBookingFromBookingDto(bookingDto, booking);
            bookingRepository.persist(booking);
            BookingDto updatedBookingDto = BookingMapper.INSTANCE.bookingToBookingDto(booking);

            log.info("Updated booking with ID: {}", bookingId);

            return updatedBookingDto;
        });
    }

    @Transactional
    public boolean deleteBooking(long bookingId) {
        log.info("Deleting booking with ID: {}", bookingId);

        boolean deleted = bookingRepository.deleteById(bookingId);

        if (deleted) {
            log.info("Deleted booking with ID: {}", bookingId);
        } else {
            log.warn("Failed to delete booking with ID: {}", bookingId);
        }

        return deleted;
    }
}

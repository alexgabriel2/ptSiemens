package com.siemens.backend.service;


import com.siemens.backend.entity.Booking;
import com.siemens.backend.entity.Room;
import com.siemens.backend.repository.BookingRepository;
import com.siemens.backend.request.BookingRequest;
import com.siemens.backend.response.BookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    public String bookRoom(BookingRequest bookingRequest) {

        Room room=roomService.findById(bookingRequest.getRoomId());
       List<Booking> existingBookingId = bookingRepository.findAllByRoomId(bookingRequest.getRoomId());
        List<Booking> existingBooking = bookingRepository.findAllById(existingBookingId.stream().map(Booking::getId).toList());
        boolean isDateAvailable = existingBooking.stream().noneMatch(booking ->
                bookingRequest.getCheckIn().equals(booking.getCheckIn())
                        || bookingRequest.getCheckOut().isBefore(booking.getCheckOut())
                        || (bookingRequest.getCheckIn().isAfter(booking.getCheckIn())
                        && bookingRequest.getCheckIn().isBefore(booking.getCheckOut()))
                        || (bookingRequest.getCheckIn().isBefore(booking.getCheckIn())

                        && bookingRequest.getCheckOut().equals(booking.getCheckOut()))
                        || (bookingRequest.getCheckIn().isBefore(booking.getCheckIn())

                        && bookingRequest.getCheckOut().isAfter(booking.getCheckOut()))

                        || (bookingRequest.getCheckIn().equals(booking.getCheckOut())
                        && bookingRequest.getCheckOut().equals(booking.getCheckIn()))

                        || (bookingRequest.getCheckIn().equals(booking.getCheckOut())
                        && bookingRequest.getCheckOut().equals(bookingRequest.getCheckIn()))
        );

        if (isDateAvailable && room.isAvailable()) {
            var booking = Booking.builder()
                    .checkIn(bookingRequest.getCheckIn())
                    .checkOut(bookingRequest.getCheckOut())
                    .rooms(Set.of(room))
                    .build();
            bookingRepository.save(booking);
            return "Room is booked";
        }
        return "Room is not available";
    }

    public String cancelBooking(long bookingId) {
        var booking = bookingRepository.findById(bookingId).orElseThrow();
        var checkIn= booking.getCheckIn();

        if(LocalDateTime.now().isBefore(checkIn.minusHours(2))){
            bookingRepository.deleteById(booking.getId());
            return "Booking is cancelled";
        }
        return "Booking cannot be cancelled";
    }

    public List<BookingResponse> findAllBookings() {
        var bookings = bookingRepository.findAll();
        return bookings.stream().map(booking -> BookingResponse.builder()
                .bookingId(booking.getId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .build()).toList();
    }
    public List<BookingResponse> findByHotel(long bookingId) {
        var bookings = bookingRepository.findAllByHotelId(bookingId);
        return bookings.stream().map(booking -> BookingResponse.builder()
                .bookingId(booking.getId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .build()).toList();

    }


    public List<BookingResponse> findByRoom(long roomId) {
        var bookings = bookingRepository.findAllByRoomId(roomId);
        return bookings.stream().map(booking -> BookingResponse.builder()
                .bookingId(booking.getId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .build()).toList();
    }
}

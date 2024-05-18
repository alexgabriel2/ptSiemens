package com.siemens.backend.controller;

import com.siemens.backend.entity.Booking;
import com.siemens.backend.request.BookingRequest;
import com.siemens.backend.response.BookingResponse;
import com.siemens.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingsController {

     private final BookingService bookingService;

     @PostMapping("/book")
     public ResponseEntity<?> bookRoom(@RequestBody BookingRequest bookingControllerRequest){
         return ResponseEntity.ok(bookingService.bookRoom(bookingControllerRequest));
     }
    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelBooking(
            @RequestParam long bookingId
    ){
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
     @GetMapping("/all")
     public ResponseEntity<List<BookingResponse>> getAllBookings(){
         return ResponseEntity.ok(bookingService.findAllBookings());
     }
     @GetMapping("/hotel/{hotelId}")
        public ResponseEntity<List<BookingResponse>> getBookingsByHotelId(
                @PathVariable long hotelId
     ){
         return ResponseEntity.ok(bookingService.findByHotel(hotelId));
     }
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByRoomId(
            @PathVariable long roomId
    ){
        return ResponseEntity.ok(bookingService.findByRoom(roomId));
    }
}

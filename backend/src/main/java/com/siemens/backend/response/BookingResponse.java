package com.siemens.backend.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookingResponse {
    private long bookingId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

}

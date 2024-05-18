package com.siemens.backend.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequest {
    private long roomId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}

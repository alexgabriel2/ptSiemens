package com.siemens.backend.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {
    private long id;
    private long roomNumber;
    private int type;
    private double price;
    private boolean isAvailable;
    private long hotelId;
    private String hotelName;
    private List<BookingResponse> bookings;
}

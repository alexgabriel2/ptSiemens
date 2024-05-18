package com.siemens.backend.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelResponse {

    private long id;
    private String name;
    private double latitude;
    private double longitude;
    private int distance;

}

package com.siemens.backend.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {
    private String name;
    private String message;
    private long hotelId;
}

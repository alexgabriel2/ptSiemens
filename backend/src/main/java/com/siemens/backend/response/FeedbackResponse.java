package com.siemens.backend.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeedbackResponse {
    private String name;
    private String message;
}

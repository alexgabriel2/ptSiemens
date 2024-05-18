package com.siemens.backend.controller;


import com.siemens.backend.request.FeedbackRequest;
import com.siemens.backend.response.FeedbackResponse;
import com.siemens.backend.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedback(
            @PathVariable long hotelId
    ){
        return ResponseEntity.ok(feedbackService.findByHotelId(hotelId));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(
            @RequestBody
            FeedbackRequest feedbackRequest
    ){
        return ResponseEntity.ok(feedbackService.addFeedback(feedbackRequest.getHotelId(),
                feedbackRequest.getName(),
                feedbackRequest.getMessage()));
    }

}

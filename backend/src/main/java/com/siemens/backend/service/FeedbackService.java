package com.siemens.backend.service;

import com.siemens.backend.entity.Feedback;
import com.siemens.backend.entity.Hotel;
import com.siemens.backend.entity.Room;
import com.siemens.backend.repository.FeedbackRepository;
import com.siemens.backend.response.FeedbackResponse;
import com.siemens.backend.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public List<FeedbackResponse> findByHotelId(long hotelId) {
        List<Feedback> feedback=feedbackRepository.findAllByHotelId(hotelId);
        return feedback.stream().map(feedback1 -> FeedbackResponse.builder()
                .name(feedback1.getName())
                .message(feedback1.getFeedback())
                .build()).toList();
    }

    public String addFeedback(long hotelId, String name, String feedback) {
        if (name == null || feedback == null) {
            return "Name or feedback cannot be null";
        }
        var feedback1 = Feedback.builder()
                .name(name)
                .feedback(feedback)
                .hotel(Hotel.builder().id(hotelId).build())
                .build();
        feedbackRepository.save(feedback1);

        return "Feedback added";
    }
}

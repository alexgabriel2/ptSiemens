package com.siemens.backend.repository;

import com.siemens.backend.entity.Feedback;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("select f from Feedback f where f.hotel.id=:hotelId")
    List<Feedback>findAllByHotelId(long hotelId);
}

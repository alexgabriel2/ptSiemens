package com.siemens.backend.repository;

import com.siemens.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query("select r from Room r where r.hotel.id = :hotelId")
    List<Room> findAllByHotelId(long hotelId);

}

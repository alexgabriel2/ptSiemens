package com.siemens.backend.repository;

import com.siemens.backend.entity.Booking;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
   @Query("SELECT b from Booking b join fetch b.rooms r where r.id = :roomId ")
    List<Booking>findAllByRoomId(@Param("roomId") long roomId);

    @Query(nativeQuery = true,value="SELECT b.* from booking b JOIN booking_room br on b.id = br.booking_id JOIN room r on br.room_id = r.id JOIN hotel h on r.hotel_id = h.id WHERE h.id = ?1 ")
   List<Booking> findAllByHotelId(@Param("hotelId") long hotelId);

}

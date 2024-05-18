package com.siemens.backend.service;

import com.siemens.backend.entity.Hotel;
import com.siemens.backend.entity.Room;
import com.siemens.backend.repository.RoomRepository;
import com.siemens.backend.response.RoomResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public void addRoom(long roomNumber, double price,boolean isAvailable, int type, long hotelId){
        var room= Room.builder()
                .roomNumber(roomNumber)
                .type(type)
                .price(price)
                .isAvailable(isAvailable)
                .hotel(Hotel.builder().id(hotelId).build())
                .build();
        roomRepository.save(room);
    }

    public List<RoomResponse> findAllRoomsById(long hotelId) {
        var rooms = roomRepository.findAllByHotelId(hotelId);
        return rooms.stream().map(room -> RoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .price(room.getPrice())
                .isAvailable(room.isAvailable())
                .hotelId(room.getHotel().getId())
                .hotelName(room.getHotel().getName())
                .build()).toList();
    }

    public Room findById(long id) {
        return roomRepository.findById(id).orElseThrow();
    }
}

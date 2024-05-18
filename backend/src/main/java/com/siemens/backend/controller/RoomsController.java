package com.siemens.backend.controller;

import com.siemens.backend.response.HotelResponse;
import com.siemens.backend.response.RoomResponse;
import com.siemens.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomsController {
    private final RoomService roomService;
    @GetMapping("/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getAllHotels(
          @PathVariable long hotelId
    ){
        return ResponseEntity.ok(roomService.findAllRoomsById(hotelId));
    }
}

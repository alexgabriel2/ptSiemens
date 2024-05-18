package com.siemens.backend.controller;

import com.siemens.backend.request.UserSearchRequest;
import com.siemens.backend.response.HotelResponse;
import com.siemens.backend.service.HotelService;
import com.siemens.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelsController {
    private final HotelService hotelService;
    private final RoomService roomService;
    @GetMapping("/all")
    public ResponseEntity<List<HotelResponse>> getAllHotels(

    ){
        return ResponseEntity.ok(hotelService.findAllHotels());
    }
    @PostMapping("/distance")
    public ResponseEntity<List<HotelResponse>>getHotelsByDistance(
            @RequestBody UserSearchRequest userSearchRequest
    ){
        return ResponseEntity.ok(hotelService.findHotelsByDistance(userSearchRequest));
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> getRoomsByHotelId(
            @PathVariable long hotelId
    ){
        return ResponseEntity.ok(hotelService.findById(hotelId));
    }

}

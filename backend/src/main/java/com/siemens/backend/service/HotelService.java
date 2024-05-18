package com.siemens.backend.service;

import com.siemens.backend.entity.Hotel;
import com.siemens.backend.repository.HotelRepository;
import com.siemens.backend.request.UserSearchRequest;
import com.siemens.backend.response.HotelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public void addHotel(long id, String name, double latitude, double longitude){
            var hotel= Hotel.builder()
                    .id(id)
                    .name(name)
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
            hotelRepository.save(hotel);
    }


    public List<HotelResponse> findAllHotels() {

        var hotels = hotelRepository.findAll();
        return hotels.stream().map(hotel -> HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .latitude(hotel.getLatitude())
                .longitude(hotel.getLongitude())
                .build()).toList();
    }

    public List<HotelResponse> findHotelsByDistance(UserSearchRequest userSearchRequest) {
        var distance = userSearchRequest.getDistance();
        var latitude = userSearchRequest.getLatitude();
        var longitude = userSearchRequest.getLongitude();
        var hotels = hotelRepository.findAll();
        List<HotelResponse> hotelResponses =new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (calculateDistance(latitude, longitude, hotel.getLatitude(), hotel.getLongitude()) <= distance) {
                hotelResponses.add(HotelResponse.builder()
                        .id(hotel.getId())
                        .name(hotel.getName())
                        .latitude(hotel.getLatitude())
                        .longitude(hotel.getLongitude())
                        .distance((int)(calculateDistance(latitude, longitude, hotel.getLatitude(), hotel.getLongitude())*1000))
                        .build());
            }
        }
        return hotelResponses;
    }
    // Haversine formula to calculate distance between two points
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int earthRadius = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    public HotelResponse findById(long hotelId) {
        var hotel = hotelRepository.findById(hotelId).orElseThrow();
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .latitude(hotel.getLatitude())
                .longitude(hotel.getLongitude())
                .build();
    }
}

package com.siemens.backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.backend.entity.Hotel;
import com.siemens.backend.entity.Room;
import com.siemens.backend.repository.BookingRepository;
import com.siemens.backend.repository.FeedbackRepository;
import com.siemens.backend.repository.HotelRepository;
import com.siemens.backend.repository.RoomRepository;
import com.siemens.backend.service.HotelService;
import com.siemens.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/populate")
public class PopulateController  {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final BookingRepository bookingRepository;
    private final FeedbackRepository feedbackRepository;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @PostMapping("/parse")
    public ResponseEntity<?> parse(
            String name
    ) {
        try {

            bookingRepository.deleteAll();
            feedbackRepository.deleteAll();
            roomRepository.deleteAll();
            hotelRepository.deleteAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            List<Hotel> hotels = mapper.readValue(new File("hotels/"+name+".json"), new TypeReference<>() {
            });
            for (Hotel myObj : hotels) {
                hotelService.addHotel(myObj.getId(),myObj.getName(),myObj.getLatitude(),myObj.getLongitude());
                for (Room room : myObj.getRooms()) {

                    roomService.addRoom(room.getRoomNumber(),room.getPrice(),room.isAvailable(),room.getType(),myObj.getId());
                }
            }
            return ResponseEntity.ok("Populated");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/jsons")
    public ResponseEntity<List<String>> getJsons(
    ) {

        File folder = new File("hotels");
        File[] listOfFiles = folder.listFiles();
        StringBuilder sb = new StringBuilder();
        assert listOfFiles != null;
        List<String>fileName=new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
               fileName.add(file.getName().replace(".json",""));
            }
        }
        return ResponseEntity.ok(fileName);
    }
    @PostMapping(value ="/add", consumes = "multipart/form-data")
    public ResponseEntity<?>addJson(
            @RequestParam("file") MultipartFile file
    ){
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            for (File file1 : Objects.requireNonNull(new File("hotels").listFiles())) {
                if (file1.getName().equals(fileName)) {
                    return ResponseEntity.badRequest().body("File already exists");
                }
            }
            if (fileName.endsWith(".json")) {
                File file1 = new File("hotels/" + fileName);
                Files.write(file1.toPath(), file.getBytes());
            }

            return ResponseEntity.ok().body("Added");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

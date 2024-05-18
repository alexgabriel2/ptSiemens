package com.siemens.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hotel {
    @Id
    private long id;
    private String name;
    private double latitude;
    private double longitude;

    public Hotel(long id, double latitude, double longitude, List<Room> rooms, String name) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rooms = rooms;
        this.name = name;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Room> rooms;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Feedback> feedbacks;


}

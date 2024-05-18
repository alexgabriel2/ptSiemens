package com.siemens.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long roomNumber;
    private int type;
    private double price;
    private boolean isAvailable;

    @JsonProperty("isAvailable")
    public boolean isAvailable() {
        return isAvailable;
    }

    @ManyToMany(mappedBy = "rooms")
    private Set<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", hotel=" + hotel +
                '}';
    }
}

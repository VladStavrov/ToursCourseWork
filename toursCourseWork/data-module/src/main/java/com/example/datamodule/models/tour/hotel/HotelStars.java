package com.example.datamodule.models.tour.hotel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelStars {
    @Id
    private Long id;
    private String name;
    private int weight;
}

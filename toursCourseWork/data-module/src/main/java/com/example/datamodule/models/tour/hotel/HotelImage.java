package com.example.datamodule.models.tour.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelImage that = (HotelImage) o;
        return Objects.equals(id, that.id) && Objects.equals(link, that.link) && Objects.equals(hotel, that.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, hotel);
    }
}

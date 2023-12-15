package com.example.datamodule.models.tour.hotel;

import com.example.datamodule.models.tour.Tour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String hotelName;
    private String regionName;
    private String hotelImage;
    private int stars;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "country_id")
    private Countries country;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
    private List<Tour> tourList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
    private List<HotelImage> imageList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return stars == hotel.stars && Objects.equals(id, hotel.id) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(regionName, hotel.regionName) && Objects.equals(hotelImage, hotel.hotelImage) && Objects.equals(country, hotel.country) && Objects.equals(tourList, hotel.tourList) && Objects.equals(imageList, hotel.imageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotelName, regionName, hotelImage, stars, country, tourList, imageList);
    }
}

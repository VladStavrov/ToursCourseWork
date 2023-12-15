package com.example.datamodule.repositories;

import com.example.datamodule.models.tour.hotel.HotelStars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelStarsRepository extends JpaRepository<HotelStars,Long> {
    HotelStars findByName(String name);
    List<HotelStars> findByWeight(int weight);
}

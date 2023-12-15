package com.example.datamodule.repositories;

import com.example.datamodule.models.tour.hotel.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage,Long> {

}

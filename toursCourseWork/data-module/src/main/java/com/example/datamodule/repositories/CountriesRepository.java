package com.example.datamodule.repositories;

import com.example.datamodule.models.tour.hotel.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Countries,Long> {
}

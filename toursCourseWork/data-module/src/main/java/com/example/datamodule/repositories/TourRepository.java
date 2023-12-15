package com.example.datamodule.repositories;

import com.example.datamodule.models.tour.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
}

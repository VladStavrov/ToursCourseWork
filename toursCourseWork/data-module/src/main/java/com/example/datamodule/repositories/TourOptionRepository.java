package com.example.datamodule.repositories;

import com.example.datamodule.models.tour.info.TourOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourOptionRepository extends JpaRepository<TourOption,Long> {

}

package com.example.datamodule.repositories;

import com.example.datamodule.models.tour.info.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
}

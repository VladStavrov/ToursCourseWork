package com.example.datamodule.repositories;

import com.example.datamodule.models.order.PayCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCardRepository extends JpaRepository<PayCard,Long> {
}
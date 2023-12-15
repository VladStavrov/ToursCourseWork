package com.example.datamodule.repositories;

import com.example.datamodule.models.order.OrderCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCustomRepository extends JpaRepository<OrderCustom, Long> {
    List<OrderCustom> findAllByPhoneNumber(String phoneNumber);
}

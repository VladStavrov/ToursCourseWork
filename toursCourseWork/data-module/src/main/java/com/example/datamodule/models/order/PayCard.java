package com.example.datamodule.models.order;

import com.example.datamodule.models.order.OrderCustom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String month;
    private String year;
    private String cvv;
    private String ownerData;

    @OneToOne(mappedBy = "payCard")
    private OrderCustom orderCustom;

    /*@OneToOne(mappedBy = "payCard")
    private OrderUser orderUser;*/
}

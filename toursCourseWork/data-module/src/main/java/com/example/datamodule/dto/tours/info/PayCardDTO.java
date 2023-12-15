package com.example.datamodule.dto.tours.info;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCardDTO {
    private Long id;
    private String cardNumber;
    private String month;
    private String year;
    private String cvv;
    private String ownerData;

    public PayCardDTO(String cardNumber, String month, String year, String cvv, String ownerData) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.ownerData = ownerData;
    }
}

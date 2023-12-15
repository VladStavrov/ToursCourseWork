package com.example.datamodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasportPersonDTO {
    private Long id;
    private String sex;
    private String name;
    private String lastName;
    private String surname;
    private String birthDate;
    private String startDate;
    private String endDate;
    private String passportSeries;
    private String passportNumber;
}

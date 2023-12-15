package com.example.datamodule.dto.person;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDTO {
    private String phoneNumber;
    private String name;
    private String lastname;
}

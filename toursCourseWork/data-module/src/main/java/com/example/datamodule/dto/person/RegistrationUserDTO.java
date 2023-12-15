package com.example.datamodule.dto.person;

import lombok.Data;

@Data
public class RegistrationUserDTO {
    private String phoneNumber;
    private String name;
    private String lastname;
    private String password;
    private String confirmPassword;
}


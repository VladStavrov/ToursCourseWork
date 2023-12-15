package com.example.datamodule.request;

import lombok.Data;

@Data
public  class JwtRequest {
    private String phoneNumber;
    private String password;
}
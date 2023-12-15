package com.example.datamodule.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private List<String> roles;
}

package com.example.datamodule.request;


import lombok.Data;

import java.util.List;

@Data
public class ApiRequest {
    private List<List<Object>> data;
}

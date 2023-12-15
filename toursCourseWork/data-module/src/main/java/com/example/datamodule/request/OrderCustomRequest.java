package com.example.datamodule.request;

import com.example.datamodule.dto.tours.TourRequestDTO;
import com.example.datamodule.models.person.PasportPerson;
import com.example.datamodule.models.order.PayCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCustomRequest {
    private  String name;
    private String lastname;
    private String phoneNumber;
    private TourRequestDTO tour;
    private List<PasportPerson> tourists;
    private PayCard payCard;
}

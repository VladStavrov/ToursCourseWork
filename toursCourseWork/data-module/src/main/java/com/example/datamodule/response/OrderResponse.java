package com.example.datamodule.response;

import com.example.datamodule.dto.PasportPersonDTO;
import com.example.datamodule.dto.tours.info.PayCardDTO;
import com.example.datamodule.dto.tours.TourRequestDTO;
import com.example.datamodule.models.person.PasportPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String name;
    private String lastname;
    private String phoneNumber;
    private String status;
    private String dateOfCreated;
    private TourRequestDTO tour;
    private List<PasportPersonDTO> tourists;
    private PayCardDTO payCard;

}

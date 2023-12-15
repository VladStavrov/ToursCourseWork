package com.example.datamodule.dto.tours;

import com.example.datamodule.dto.tours.hotel.HotelDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {

    HotelDTO hotel;

    List<TourOptionsDTO> tourOptions=new ArrayList<>();
}
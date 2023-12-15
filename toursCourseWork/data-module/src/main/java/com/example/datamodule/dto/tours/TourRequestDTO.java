package com.example.datamodule.dto.tours;

import com.example.datamodule.dto.tours.hotel.HotelDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder(toBuilder = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestDTO {
    HotelDTO hotel;
    TourOptionsDTO tourOption;
}
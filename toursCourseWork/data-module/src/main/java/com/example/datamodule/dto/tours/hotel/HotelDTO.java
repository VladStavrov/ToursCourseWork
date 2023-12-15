package com.example.datamodule.dto.tours.hotel;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    Long id;
    String hotelName;
    String hotelImage;
    String regionName;
    CountryDTO country;
    int stars;
    List<String> imageList=new ArrayList<>();

    public HotelDTO(CountryDTO country,Long id, String hotelName, String hotelImage,String regionName,int stars) {
        this.regionName=regionName;
        this.id = id;
        this.hotelName = hotelName;
        this.hotelImage = hotelImage;
        this.stars=stars;
        this.country=country;
    }
}

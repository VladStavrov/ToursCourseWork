package com.example.servicemodule.s.services;


import com.example.datamodule.dto.tours.hotel.CountryDTO;
import com.example.datamodule.dto.tours.hotel.HotelDTO;
import com.example.datamodule.models.tour.hotel.Hotel;
import com.example.datamodule.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private  final HotelRepository hotelRepository;

    public Hotel getHotelById(Long id){
        return hotelRepository.findById(id).orElse(null);
    }
    public Hotel saveHotel(Hotel hotel){
       return  hotelRepository.save(hotel);
    }
    public HotelDTO hotelToDTO(Hotel hotel){
        HotelDTO hotelDTO=HotelDTO.builder()
                .stars(hotel.getStars())
                .hotelName(hotel.getHotelName())
                .hotelImage(hotel.getHotelImage())
                .country(new CountryDTO(hotel.getCountry().getName(),hotel.getCountry().getId()))
                //.imageList(hotel.getImageList().)
                .id(hotel.getId())
                .regionName(hotel.getRegionName())
                .build();
        List<String> imageList= new ArrayList<>();
        hotel.getImageList().forEach(hotelImage ->
                imageList.add(hotelImage.getLink())
                );
        hotelDTO.setImageList(imageList);
        return hotelDTO;

    }
}

package com.example.servicemodule.s.services;

import com.example.datamodule.models.tour.hotel.HotelImage;
import com.example.datamodule.repositories.HotelImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelImageService {
    private final HotelImageRepository hotelImageRepository;
    List<HotelImage> saveImages(List<HotelImage> hotelImages){
       return hotelImageRepository.saveAll(hotelImages);
    }
}

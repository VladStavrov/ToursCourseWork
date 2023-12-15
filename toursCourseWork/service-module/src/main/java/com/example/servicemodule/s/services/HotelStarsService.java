package com.example.servicemodule.s.services;

import com.example.datamodule.models.tour.hotel.HotelStars;
import com.example.datamodule.repositories.HotelStarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelStarsService {
    private final HotelStarsRepository hotelStarsRepository;


    public int getStarsFromHotelName(String name){
        List<HotelStars> hotelStarsList= hotelStarsRepository.findAll();
        for (HotelStars hotelStar:
             hotelStarsList) {
            if(name.contains(hotelStar.getName())){
                return getStarRating(hotelStar.getWeight());
            }
        }
        return 1;
    }
    public Long getIdStarsByStars(int stars){
        int weight=-9;
        switch (stars){
            case 1:{
                weight=-9;
                break;
            }
            case 2:{
                weight=-1;
                break;
            }
            case 3:{
                weight=5;
                break;
            }
            case 4:{
                weight=-9;
                break;
            }
            case 5:{
                weight=8;
                break;
            }

        }
        return hotelStarsRepository.findByWeight(weight).get(0).getId();
    }
    public  int getStarRating(int weight) {
        if (weight < -9 || weight > 9) {
            throw new IllegalArgumentException("Invalid hotel stars value");
        }
        if (weight <= -5) {
            return 1;
        } else if (weight <= 0) {
            return 2;
        } else if (weight <= 4) {
            return 3;
        } else if (weight <= 7) {
            return 4;
        } else {
            return 5;
        }
    }
}

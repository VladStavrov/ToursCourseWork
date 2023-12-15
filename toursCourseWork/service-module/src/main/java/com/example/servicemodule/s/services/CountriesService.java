package com.example.servicemodule.s.services;

import com.example.datamodule.models.tour.hotel.Countries;
import com.example.datamodule.repositories.CountriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountriesService {
    private  final CountriesRepository countriesRepository;
    public Countries getById(Long id){
        return countriesRepository.findById(id).get();
    }
}

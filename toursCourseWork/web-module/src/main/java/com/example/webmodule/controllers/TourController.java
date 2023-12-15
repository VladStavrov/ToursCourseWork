package com.example.webmodule.controllers;

import com.example.datamodule.dto.tours.TourDTO;
import com.example.datamodule.request.FilterDTO;
import com.example.servicemodule.s.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController {
    private final TourService tourService;
    @PostMapping("/find")
    ResponseEntity<?> getInfo(@RequestBody FilterDTO filterDTO){
         return ResponseEntity.ok( tourService.getTours(filterDTO));
    }
    @GetMapping("/images/{id}")
    ResponseEntity<?> getImages(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok( tourService.getImagesById(id));
    }
    @PostMapping("/find/{id}")
    ResponseEntity<?> getTourFindById(@PathVariable(name = "id") Long id, @RequestBody  FilterDTO filterDTO){
        return ResponseEntity.ok( tourService.getTourByHotelId(id,filterDTO));
    }
}
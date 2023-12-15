package com.example.servicemodule.s.Parsing;

import com.example.datamodule.dto.tours.TourDTO;
import com.example.datamodule.request.ApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Parser {

    private final  ParsingTourFromApi parsingTourFromApi;
    @Cacheable("2")
    public List<TourDTO> parseApiToTourList(ApiRequest apiRequest,Long countryId,int peoples){
       return parsingTourFromApi.parse(apiRequest,countryId,peoples);
    }
    @Cacheable("imagesHotel2")
    public List<String> parseImageByHotelId(Long hotelId){
        System.out.println("Check");

        ParseImageFromSite parseImageFromSite = new ParseImageFromSite();
        return parseImageFromSite.getImages(hotelId);


    }
}

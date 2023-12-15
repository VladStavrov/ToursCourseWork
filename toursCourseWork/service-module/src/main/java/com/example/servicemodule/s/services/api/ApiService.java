package com.example.servicemodule.s.services.api;



import com.example.datamodule.request.ApiRequest;
import com.example.datamodule.request.FilterDTO;
import com.example.servicemodule.s.services.HotelStarsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final WebClient webClient;
    private final ModelMapper modelMapper;
    private  final HotelStarsService hotelStarsService;

    @Cacheable("1")
    public ApiRequest getToursApi(FilterDTO filterDTO){
        Map<String, String> params = new HashMap<>();
        params.put("accommodationId",String.valueOf(filterDTO.getPeoples()));//Сколько человек
        params.put("after",filterDTO.getStartDate());
        params.put("before",filterDTO.getEndDate());
        params.put("cityId","345");// Город вылета (Москва)
        params.put("countryId",String.valueOf(filterDTO.getCountryId())); // Страна отдыха
        params.put("nightsMin",String.valueOf(filterDTO.getNights()));
        params.put("nightsMax",String.valueOf(filterDTO.getNights()));
        params.put("currency","5561");
        params.put("priceMin","0");
        params.put("priceMax","120000");
        params.put("hotelClassId",String.valueOf(hotelStarsService.getIdStarsByStars(filterDTO.getStars())));//Звездность отеля
        params.put("hotelClassBetter","true");
        params.put("rAndBId","15350");//BB RO завтраки 15350 - не включена еда
        params.put("rAndBBetter","true");
        MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
        p.setAll(params);
        return webClient.get()
                .uri(uriBuilder ->uriBuilder
                        /*.path("/tariffsearch/getResult")*/
                        .queryParams(p)
                        .build())
                .retrieve()
                .bodyToMono(ApiRequest.class)
                .doOnError(error-> System.out.println("error: " +error.getMessage()))
                .block();
    }

}

package com.example.servicemodule.s.Parsing;

import com.example.commonmodule.exception.LocalException;
import com.example.datamodule.constApi.ApiConst;
import com.example.datamodule.dto.tours.hotel.HotelDTO;
import com.example.datamodule.dto.tours.hotel.CountryDTO;
import com.example.datamodule.dto.tours.*;
import com.example.datamodule.dto.tours.info.FoodDTO;
import com.example.datamodule.models.tour.hotel.Countries;
import com.example.datamodule.request.ApiRequest;
import com.example.servicemodule.s.services.CountriesService;
import com.example.servicemodule.s.services.HotelStarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ParsingTourFromApi {
    private final HotelStarsService hotelStarsService;
    private final CountriesService countriesService;

    public List<TourDTO> parse(ApiRequest apiRequest,Long countryId,int peoples){
        Countries countries =  countriesService.getById(countryId);
        CountryDTO countryDTO = new CountryDTO(countries.getName(),countryId);
        List<TourDTO> tourList= new ArrayList<>();
        if(apiRequest.getData()==null){
            throw new LocalException(HttpStatus.NOT_FOUND,"По данному запросу ничего не найдено");
        }
        apiRequest.getData().forEach(tourObject ->{
            List <Object> hotelObject = (List<Object>) tourObject.get(ApiConst.HOTEL_ARRAY_INDEX);
            LinkedHashMap<String,String > currencyObject = (LinkedHashMap<String, String>) tourObject.get(ApiConst.CURRENCY_ARRAY_INDEX);
            List<Object> roomObject=(List<Object>) tourObject.get(ApiConst.ROOM_ARRAY_INDEX);

            Long hotelId=Long.parseLong(hotelObject.get(ApiConst.HOTEL_ID_INDEX).toString());

            TourOptionsDTO tourOptions = new TourOptionsDTO();
            tourOptions.setDateStart(tourObject.get(ApiConst.DATE_START_ARRAY_INDEX).toString());
            tourOptions.setDateEnd(tourObject.get(ApiConst.DATE_END_ARRAY_INDEX).toString());
            tourOptions.setNights(Integer.parseInt(tourObject.get(ApiConst.NIGHTS_ARRAY_INDEX).toString()));
            tourOptions.setTotal(Integer.parseInt(currencyObject.get("total").split("\\.")[0]));
            tourOptions.setTypeRoom(roomObject.get(ApiConst.ROOM_TYPE_INDEX).toString());
            List <Object> foodObject = (List<Object>) tourObject.get(ApiConst.FOOD_ARRAY_INDEX);
            tourOptions.setFood(new FoodDTO(Long.valueOf(foodObject.get(ApiConst.FOOD_ID_INDEX).toString()),foodObject.get(ApiConst.FOOD_TYPE_INDEX).toString(),
                    foodObject.get(ApiConst.FOOD_DESCRIPTION_INDEX).toString()));
            tourOptions.setPeoples(peoples);
            //TODO cut currency 356.356
           // tourOptions.getCurrency().setCurrency(tourOptions.getCurrency().getCurrency().replaceAll("\\..*$",""));

            Optional<TourDTO> optionalTour = tourList.stream()
                    .filter(tour -> tour.getHotel().getId().equals(hotelId))
                    .findFirst();
            if (!optionalTour.isPresent()) {
               TourDTO tour =  new TourDTO();

                tour.setHotel(new HotelDTO(countryDTO,Long.parseLong(hotelObject.get(ApiConst.HOTEL_ID_INDEX).toString()),
                        hotelObject.get(ApiConst.HOTEL_NAME_INDEX).toString(),
                        hotelObject.get(ApiConst.HOTEL_IMAGE_INDEX).toString(),
                        ((List<Object>) tourObject.get(ApiConst.REGION_ARRAY_INDEX)).get(ApiConst.REGION_NAME_INDEX).toString(),
                        hotelStarsService.getStarsFromHotelName(hotelObject.get(ApiConst.HOTEL_NAME_INDEX).toString())));

                tour.getTourOptions().add(tourOptions);
                tourList.add(tour);
            }
            else{
                optionalTour.get().getTourOptions().add(tourOptions);
            }
           }
        );
        return tourList;
    }

}

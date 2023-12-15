package com.example.servicemodule.s.services;


import com.example.datamodule.dto.tours.*;
import com.example.datamodule.dto.tours.hotel.CountryDTO;
import com.example.datamodule.dto.tours.hotel.HotelDTO;
import com.example.datamodule.models.tour.Tour;
import com.example.datamodule.models.tour.hotel.Hotel;
import com.example.datamodule.models.tour.hotel.HotelImage;
import com.example.datamodule.models.tour.info.TourOption;
import com.example.datamodule.repositories.FoodRepository;
import com.example.datamodule.repositories.TourRepository;
import com.example.datamodule.request.ApiRequest;
import com.example.datamodule.request.FilterDTO;
import com.example.servicemodule.s.Parsing.Parser;
import com.example.servicemodule.s.services.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final HotelService hotelService;
    private  final Parser parser;
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;
    private final ApiService apiService;
    private final HotelImageService hotelImageService;

    @Cacheable("imagesHotel")
    public List<String> getImagesById(Long id){
        System.out.println("test");
        return parser.parseImageByHotelId( id);
    }
    @Cacheable("tours")
    public List<TourDTO> getTours(FilterDTO filterDTO){
        System.out.println("Получение туров");
        ApiRequest apiRequest=apiService.getToursApi(filterDTO);
        return parser.parseApiToTourList(apiRequest,filterDTO.getCountryId(),filterDTO.getPeoples());
    }
    public Tour saveTour(TourRequestDTO tourDTO) {
        Tour tour = new Tour();
        //TODO MAPPER
        TourOption tourOption = modelMapper.map(tourDTO.getTourOption(), TourOption.class);
        /*Food food = modelMapper.map(tourDTO.getTourOption().getFood(), Food.class);*/
        Hotel hotel = hotelService.getHotelById(tourDTO.getHotel().getId());
       /* tour.setFood(food);*/
        tour.setTourOption(tourOption);
        if (hotel == null) {
            hotel = modelMapper.map(tourDTO.getHotel(), Hotel.class);
            List<HotelImage> hotelImages = new ArrayList<>();
            for (String image:
                    tourDTO.getHotel().getImageList()) {
                HotelImage hotelImage = new HotelImage();
                hotelImage.setLink(image);
                hotelImage.setHotel(hotel);
                hotelImages.add(hotelImage);
            }
           /* tourDTO.getHotel().getImageList().forEach(image ->
            {
                HotelImage hotelImage = new HotelImage();
                hotelImage.setLink(image);
                hotelImages.add(hotelImage);
            });*/
          /*  hotelImageService.saveImages(hotelImages);*/
           hotel.setImageList(hotelImages);
            hotel = hotelService.saveHotel(hotel);
        }
        tour.setHotel(hotel);
        foodRepository.save(tour.getTourOption().getFood());
        tour = tourRepository.save(tour);



        return tour;
    }
    public TourDTO  getTourByHotelId(Long id,FilterDTO filterDTO){
        TourDTO tourDTO = getTours(filterDTO).stream().filter(tourDTO1 -> tourDTO1.getHotel().getId().equals(id)).findFirst().orElseThrow( () ->new ResponseStatusException(HttpStatus.NOT_FOUND,"Объект не был найден"));
        tourDTO.getHotel().setImageList(getImagesById(id));
        return tourDTO;
    }
    public TourRequestDTO tourToTourDto(Tour tour){
        TourRequestDTO tourDTO = new TourRequestDTO();
        TourOptionsDTO tourOption = modelMapper.map(tour.getTourOption(), TourOptionsDTO.class);
        System.out.println(0);
        HotelDTO hotelDTO =hotelService.hotelToDTO(tour.getHotel());

        System.out.println(1);

        /*tourOption.setFood(foodDTO);*/
        tourDTO.setTourOption(tourOption);

        tourDTO.setHotel(hotelDTO);
        System.out.println(2);
        return tourDTO;
    }




}

/*
package com.example.servicemodule.s.services;

import com.example.datamodule.dto.PasportPersonDTO;
import com.example.datamodule.dto.tours.info.PayCardDTO;
import com.example.datamodule.models.person.PasportPerson;
import com.example.datamodule.models.tour.Tour;
import com.example.datamodule.repositories.OrderCustomRepository;
import com.example.datamodule.request.OrderUserRequest;
import com.example.datamodule.response.OrderResponse;
import com.example.servicemodule.s.services.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderUserService {
    private  final OrderUserRepository orderUserRepository;
    private final TourService tourService;
    private final PersonService personService;
    private final ParpostPersonService parpostPersonService;
    private final ModelMapper modelMapper;
    private final OrderCustomRepository orderCustomRepository;


    public void createOrder( String phoneNumber, OrderUserRequest orderUserRequest){
        System.out.println(phoneNumber+" - PN");
        Tour tour = tourService.saveTour(orderUserRequest.getTourRequestDTO());
        OrderUser orderUser = new OrderUser();
        orderUser.setStatus("Создано");
        orderUser.setTour(tour);
        orderUser.setPayCard(orderUserRequest.getPayCard());
        orderUser.setPerson(personService.findByPhoneNumber(phoneNumber));
        for (PasportPerson tourist :
                orderUserRequest.getTourists()  ) {
            tourist.setOrderUser(orderUser);
parpostPersonService.saveNewTourist(tourist)

            orderUser.getPersonList().add(tourist);
        }
orderUserRequest.getTourists().forEach(tourist->{
            orderUser.getPersonList().add(parpostPersonService.saveNewTourist(tourist));
        });

        orderUserRepository.save(orderUser);

    }

    public List<OrderResponse> getAllOrders(){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<OrderUser> orderUserList = orderUserRepository.findAll();
        System.out.println("OrderUser");
        orderUserList.forEach(order->{


            OrderResponse orderResponse =  new OrderResponse()
modelMapper.map(order,OrderResponse.class)
;
            System.out.println("ПРоверка есть");
            orderResponse.setName(order.getPerson().getName());
            orderResponse.setLastName(order.getPerson().getLastname());
            orderResponse.setPhoneNumber(order.getPerson().getPhoneNumber());
            orderResponse.setId(order.getId());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setDateOfCreated(order.getDateOfCreated());
            orderResponse.setPayCard(modelMapper.map(order.getPayCard(), PayCardDTO.class));
            List<PasportPersonDTO> pasportPersonDTO = new ArrayList<>();
            order.getPersonList().forEach(pasportPerson -> {
                pasportPersonDTO.add(modelMapper.map(pasportPerson, PasportPersonDTO.class));
            });
            orderResponse.setPersonList(pasportPersonDTO);
            orderResponse.setTour(tourService.tourToTourDto(order.getTour()));

            orderResponseList.add(orderResponse);

        });
        return orderResponseList;


    }
    public void changeStatus(Long id,String status){
        OrderUser orderUser=orderUserRepository.findById(id).get();
        orderUser.setStatus(status);
       orderUserRepository.save(orderUser);

    }

}
*/

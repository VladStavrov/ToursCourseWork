package com.example.servicemodule.s.services;

import com.example.datamodule.dto.PasportPersonDTO;
import com.example.datamodule.dto.tours.info.PayCardDTO;
import com.example.datamodule.models.order.OrderCustom;
import com.example.datamodule.models.person.PasportPerson;
import com.example.datamodule.models.person.Person;
import com.example.datamodule.models.tour.Tour;
import com.example.datamodule.repositories.OrderCustomRepository;
import com.example.datamodule.request.OrderCustomRequest;
import com.example.datamodule.request.OrderUserRequest;
import com.example.datamodule.response.OrderResponse;
import com.example.servicemodule.s.services.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCustomService {
    private final OrderCustomRepository orderCustomRepository;
    private final TourService tourService;
    private  final ParpostPersonService parpostPersonService;
    private  final ModelMapper modelMapper;
    private final PersonService personService;


    public void createOrder(OrderCustomRequest orderCustomRequest){

        Tour tour = tourService.saveTour(orderCustomRequest.getTour());
        /*if(personService.isPersonAlive(orderCustomRequest.getPhoneNumber())){
            orderUserService
        }*/
        OrderCustom orderCustom = new OrderCustom();
        orderCustom.setStatus("Создано");
        orderCustom.setTour(tour);
        orderCustom.setName(orderCustomRequest.getName());
        orderCustom.setLastname(orderCustomRequest.getLastname());
        orderCustom.setPhoneNumber(orderCustomRequest.getPhoneNumber());

        orderCustom.setPayCard(orderCustomRequest.getPayCard());

        for (PasportPerson tourist :
                orderCustomRequest.getTourists()  ) {
            tourist.setOrderCustom(orderCustom);
            /*parpostPersonService.saveNewTourist(tourist)*/
            orderCustom.getPersonList().add(tourist);
        }
      /*  orderCustomRequest.getTourists().forEach(tourist->{
            orderCustom.getPersonList().add(parpostPersonService.saveNewTourist(tourist));
        });*/
        orderCustomRepository.save(orderCustom);
    }
    public void createOrder( String phoneNumber, OrderCustomRequest orderUserRequest){
        System.out.println(phoneNumber+" - PN");
        Tour tour = tourService.saveTour(orderUserRequest.getTour());
        OrderCustom orderUser = new OrderCustom();
        System.out.println(1);
        orderUser.setStatus("Создано");
        orderUser.setTour(tour);
        orderUser.setPayCard(orderUserRequest.getPayCard());
        System.out.println(2);
        Person person=  personService.findByPhoneNumber(phoneNumber);
        orderUser.setName(orderUserRequest.getName());
        orderUser.setLastname(orderUserRequest.getLastname());
        orderUser.setPhoneNumber(orderUserRequest.getPhoneNumber());
        System.out.println(3);
        for (PasportPerson tourist :
                orderUserRequest.getTourists()  ) {
            tourist.setOrderCustom(orderUser);
            /*parpostPersonService.saveNewTourist(tourist)*/
            orderUser.getPersonList().add(tourist);
        }
        System.out.println(4);
        orderUser=orderCustomRepository.save(orderUser);
        System.out.println(5);
        System.out.println("Stack trace: " + Arrays.toString(Thread.currentThread().getStackTrace()));
        person.getOrders().add(orderUser);
        System.out.println("Stack trace: " + Arrays.toString(Thread.currentThread().getStackTrace()));
        System.out.println(6);
        personService.savePerson(person);
        System.out.println(7);

    }
    public OrderResponse getById(Long id){
        OrderCustom orderCustom = orderCustomRepository.findById(id).orElseThrow();
        return orderToOrderResponse(orderCustom);
    }
    public void updateOrderData(OrderResponse orderResponse){
        OrderCustom orderCustom= orderCustomRepository.findById(orderResponse.getId()).get();
        orderCustom.setLastname(orderResponse.getLastname());
        orderCustom.setName(orderResponse.getName());
        orderCustom.setPhoneNumber(orderResponse.getPhoneNumber());
        for (PasportPersonDTO tourist :
                orderResponse.getTourists()  ) {
            PasportPerson pasportPerson = modelMapper.map(tourist,PasportPerson.class);
            System.out.println("new tourist: "+pasportPerson);
            pasportPerson.setOrderCustom(orderCustom);
            orderCustom.getPersonList().add(pasportPerson);
        }
        orderCustom=orderCustomRepository.save(orderCustom);

    }

    public OrderResponse orderToOrderResponse(OrderCustom order){
        OrderResponse orderResponse =  new OrderResponse()/*modelMapper.map(order,OrderResponse.class)*/;
        System.out.println("ПРоверка есть");
        orderResponse.setName(order.getName());
        orderResponse.setLastname(order.getLastname());
        orderResponse.setPhoneNumber(order.getPhoneNumber());
        orderResponse.setId(order.getId());

        orderResponse.setStatus(order.getStatus());
        orderResponse.setDateOfCreated(order.getDateOfCreated());
        orderResponse.setPayCard(modelMapper.map(order.getPayCard(), PayCardDTO.class));
        List<PasportPersonDTO> pasportPersonDTO = new ArrayList<>();
        order.getPersonList().forEach(pasportPerson -> {
            pasportPersonDTO.add(modelMapper.map(pasportPerson, PasportPersonDTO.class));
        });
        orderResponse.setTourists(pasportPersonDTO);
        orderResponse.setTour(tourService.tourToTourDto(order.getTour()));
        return orderResponse;
    }
    public List<OrderResponse> getMyOrders(String phoneNumber){

        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<OrderCustom> orderCustomList = orderCustomRepository.findAllByPhoneNumber(phoneNumber);
        orderCustomList.forEach(order->{
            orderResponseList.add(orderToOrderResponse(order));
        });
        return orderResponseList;
    }

    public List<OrderResponse> getAllOrders(){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<OrderCustom> orderCustomList = orderCustomRepository.findAll();
        System.out.println("OrderCustom");
        orderCustomList.forEach(order->{
            orderResponseList.add(orderToOrderResponse(order));
        });
        return orderResponseList;
    }
    public void changeStatus(Long id,String status){
        OrderCustom orderUser=orderCustomRepository.findById(id).get();
        orderUser.setStatus(status);
        orderCustomRepository.save(orderUser);
    }
}

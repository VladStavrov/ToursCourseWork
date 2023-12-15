package com.example.webmodule.controllers;

import com.example.datamodule.dto.tours.TourDTO;
import com.example.datamodule.dto.tours.TourRequestDTO;
import com.example.datamodule.request.ChangeStatusRequest;
import com.example.datamodule.request.OrderCustomRequest;
import com.example.datamodule.request.OrderUserRequest;
import com.example.datamodule.response.OrderResponse;
import com.example.servicemodule.s.services.OrderCustomService;
import com.example.servicemodule.s.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    /*private  final OrderUserService orderUserService;*/
    private final OrderCustomService orderCustomService;
    private final OrderService orderService;

    @PostMapping("/add")
    ResponseEntity<?> addTourToPerson( Authentication authentication,
                                      @RequestBody OrderCustomRequest orderUserRequest){
        System.out.println("number^ "+authentication.getName() );
        /*orderUserService.createOrder(authentication.getName(),orderUserRequest);*/
        orderCustomService.createOrder(authentication.getName(),orderUserRequest);
        return ResponseEntity.ok("Все четко");
    }

    @PostMapping("/application")
    ResponseEntity<?> createApplication(@RequestBody OrderCustomRequest orderCustomRequest){
        orderCustomService.createOrder(orderCustomRequest);
        return ResponseEntity.ok("Все четко");
    }

    @GetMapping("/getAll")
    ResponseEntity<?> createApplication(){

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/status")
    ResponseEntity<?> changeStatus( @RequestBody ChangeStatusRequest changeStatusRequest){
        System.out.println("Зашли в смену статуса");
        orderService.changeStatus(changeStatusRequest);
        return ResponseEntity.ok("Все норм");
    }
    @GetMapping("/myOrder")
    ResponseEntity<?> getMyOrder(Authentication authentication){
        return ResponseEntity.ok(orderCustomService.getMyOrders(authentication.getName()));
    }
    @GetMapping("/{id}")
    ResponseEntity<?> getOrderById(@PathVariable(name = "id")  Long id){
        return ResponseEntity.ok(orderCustomService.getById(id));
    }
    @PostMapping("/update/{id}")
    ResponseEntity<?> updateOrderData(@RequestBody OrderResponse orderResponse){
        orderCustomService.updateOrderData(orderResponse);
        return ResponseEntity.ok("Все ок");
    }
}

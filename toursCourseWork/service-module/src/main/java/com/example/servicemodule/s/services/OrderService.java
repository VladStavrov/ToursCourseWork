package com.example.servicemodule.s.services;

import com.example.datamodule.request.ChangeStatusRequest;
import com.example.datamodule.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderCustomService orderCustomService;

    public List<OrderResponse> getAllOrders(){
        return orderCustomService.getAllOrders();
    }
    public  void changeStatus(ChangeStatusRequest changeStatusRequest){
        /*if(changeStatusRequest.isAunteficated()){
            orderUserService.changeStatus(changeStatusRequest.getId(),changeStatusRequest.getStatus());
        }
        else {*/
            orderCustomService.changeStatus(changeStatusRequest.getId(),changeStatusRequest.getStatus());

    }

    public <T> List<T> merge(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<>();

        list.addAll(list1);
        list.addAll(list2);

        return list;
    }
}

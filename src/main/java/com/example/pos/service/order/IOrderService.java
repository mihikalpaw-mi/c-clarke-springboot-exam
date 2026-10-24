package com.example.pos.service.order;

import com.example.pos.dto.order.CreateOrderRequest;
import com.example.pos.dto.order.OrderResponse;
import com.example.pos.network.ResponseData;
import org.springframework.stereotype.Service;

@Service
public interface IOrderService {

    ResponseData<OrderResponse> createOrder(CreateOrderRequest request) ;
}

package com.example.pos.dto.order;

import com.example.pos.dto.orderItem.OrderItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private LocalDateTime orderDateTime;
    private String status;
    private Long customerId;
    private String customerName;
    private String mobileNumber;
    private List<OrderItemResponse> items;
    private double totalAmount;
    private int loyaltyPointsEarned;
}

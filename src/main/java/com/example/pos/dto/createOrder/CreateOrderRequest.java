package com.example.pos.dto.order;

import com.example.pos.dto.Customer.CustomerRequest;
import com.example.pos.dto.orderItem.OrderItemRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "Customer details are required")
    @Valid
    private CustomerRequest customer;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<@NotNull(message = "Item cannot be null") OrderItemRequest> items;
}
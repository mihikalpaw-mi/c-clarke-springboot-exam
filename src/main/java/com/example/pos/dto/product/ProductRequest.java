package com.example.pos.dto.product;


import com.example.pos.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotNull( message = "mandatory") String productName;
    @NotNull( message = "mandatory") String productCode;
    @Positive double unitPrice;
    @Positive Integer availableQuantity ;
    boolean activeStatus ;
    Long categoryId ;
}

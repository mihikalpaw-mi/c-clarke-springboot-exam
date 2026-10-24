package com.example.pos.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    Long productId ;
    String productName ;
    String productCode ;
    double unitPrice ;
    String categoryName ;
    Long categoryId ;
}

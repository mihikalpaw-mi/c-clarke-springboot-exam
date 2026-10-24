package com.example.pos.controller.product;

import com.example.pos.constant.EndpointConstants;
import com.example.pos.dto.product.ProductRequest;
import com.example.pos.dto.product.ProductResponse;
import com.example.pos.network.ApiResponseWrapper;
import com.example.pos.network.ResponseData;
import com.example.pos.network.ResponseDataPack;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndpointConstants.V1 + EndpointConstants.PRODUCTS)
public interface IProductController {

    @PostMapping
    ResponseEntity<ApiResponseWrapper<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest request) ;

    @GetMapping
    ResponseEntity<ApiResponseWrapper<List<ProductResponse>>> getFilteredProducts( @RequestParam("name") String name,
                                                                                   @RequestParam("minPrice") double minPrice,
                                                                                   @RequestParam("maxPrice") double maxPrice,
                                                                                   @RequestParam("categoryId") Long id ) ;



}

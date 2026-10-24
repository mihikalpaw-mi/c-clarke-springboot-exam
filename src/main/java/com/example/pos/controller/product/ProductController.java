package com.example.pos.controller.product;

import com.example.pos.dto.product.ProductRequest;
import com.example.pos.dto.product.ProductResponse;
import com.example.pos.network.ApiResponseWrapper;
import com.example.pos.network.NetworkUtils;
import com.example.pos.service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController implements IProductController{
    @Autowired
    private IProductService productService;
    @Override
    public ResponseEntity<ApiResponseWrapper<ProductResponse>> createProduct(ProductRequest request) {
        return NetworkUtils.wrap(productService.createProduct(request));
    }

    @Override
    public ResponseEntity<ApiResponseWrapper<List<ProductResponse>>> getFilteredProducts(String name, double minPrice, double maxPrice, Long id) {
        return NetworkUtils.wrap( productService.getFilterProducts( name , minPrice , maxPrice , id));
    }
}

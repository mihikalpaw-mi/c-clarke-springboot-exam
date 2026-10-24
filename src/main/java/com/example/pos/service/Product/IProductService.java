package com.example.pos.service.Product;


import com.example.pos.dto.product.ProductRequest;
import com.example.pos.dto.product.ProductResponse;
import com.example.pos.network.ResponseData;
import com.example.pos.network.ResponseDataPack;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {

    ResponseData<ProductResponse> createProduct(ProductRequest request) ;

    ResponseDataPack<ProductResponse> getFilterProducts( String name, double minPrice, double maxPrice, Long categoryId) ;
}

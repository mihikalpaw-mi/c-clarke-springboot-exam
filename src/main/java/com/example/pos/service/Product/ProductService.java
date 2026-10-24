package com.example.pos.service.Product;

import com.example.pos.dto.product.ProductRequest;
import com.example.pos.dto.product.ProductResponse;
import com.example.pos.network.ResponseData;
import com.example.pos.network.ResponseDataPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductServiceImpl productServiceImpl ;
    @Override
    public ResponseData<ProductResponse> createProduct(ProductRequest request) {
        try {
            return productServiceImpl.createProduct( request);
        } catch (Exception exception) {
            return ResponseData.error(HttpStatus.INTERNAL_SERVER_ERROR , "Unable to create product") ;
        }
    }

    @Override
    public ResponseDataPack<ProductResponse> getFilterProducts(String name, double minPrice, double maxPrice, Long categoryId) {
        try {
            return productServiceImpl.getFilteredProducts( name , minPrice , maxPrice, categoryId);
        } catch (Exception exception) {
            return ResponseDataPack.error( HttpStatus.INTERNAL_SERVER_ERROR , "Unable to get filtered products");
        }
    }


}

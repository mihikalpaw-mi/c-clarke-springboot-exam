package com.example.pos.service.Product;


import com.example.pos.constant.MessageConstants;
import com.example.pos.dto.product.ProductRequest;
import com.example.pos.dto.product.ProductResponse;
import com.example.pos.entity.Category;
import com.example.pos.entity.Product;
import com.example.pos.network.ResponseData;
import com.example.pos.network.ResponseDataPack;
import com.example.pos.repository.ICategoryRepository;
import com.example.pos.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    public ResponseData<ProductResponse> createProduct(ProductRequest request) {
        Category category = categoryRepository.findById( request.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseData.error( HttpStatus.NOT_FOUND , "Category not found");
        }
        if (!category.isActive()) {
            return ResponseData.error(HttpStatus.INTERNAL_SERVER_ERROR , "Category is Inactive");
        }

        if (request.getUnitPrice() < 0) {
            return ResponseData.error( HttpStatus.BAD_REQUEST , "price must be greater than zero");
        }
        if (request.getAvailableQuantity() < 0) {
            return ResponseData.error( HttpStatus.BAD_REQUEST , "quantity can not be negative");
        }

        Product product = new Product();
        product.setName( request.getProductName());
        product.setCode( request.getProductCode());
        product.setUnitPrice(request.getUnitPrice());
        product.setAvailableQuantity(request.getAvailableQuantity());
        product.setActive(request.isActiveStatus());
        product.setCategory( category);
        productRepository.save( product);

        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getUnitPrice(),
                product.getCategory().getName(),
                product.getCategory().getId()
        );

        return ResponseData.success( productResponse , MessageConstants.TEST_CREATED_SUCCESSFULLY) ;


    }

    public ResponseDataPack<ProductResponse> getFilteredProducts( String name, double minPrice, double maxPrice, Long categoryId) {

        if (categoryId < 0) {
            return ResponseDataPack.error( HttpStatus.BAD_REQUEST , "category id can not be less than 0");
        }
        if (minPrice > maxPrice) {
            return ResponseDataPack.error( HttpStatus.BAD_GATEWAY , "min price can not be greater than max price ");
        }

        List<ProductResponse> result = productRepository.filterProducts( name , minPrice , maxPrice , categoryId) ;

        return ResponseDataPack.success( result , "success");

    }


}

package com.example.pos.repository;

import com.example.pos.dto.product.ProductResponse;
import com.example.pos.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface IProductRepository extends JpaRepository<Product , Long> {
    @Query("""
            SELECT new com.example.pos.dto.product.ProductResponse(
                p.id, p.name, p.code, p.unitPrice, c.name, c.id)
            FROM Product p
            LEFT JOIN p.category c
            WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
              AND (:minPrice IS NULL OR p.unitPrice >= :minPrice)
              AND (:maxPrice IS NULL OR p.unitPrice <= :maxPrice)
              AND (:categoryId IS NULL OR c.id = :categoryId)
            """)
    List<ProductResponse> filterProducts(@Param("name") String name,
                                         @Param("minPrice") Double minPrice,
                                         @Param("maxPrice") Double maxPrice,
                                         @Param("categoryId") Long categoryId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id IN :ids ORDER BY p.id")
    List<Product> findAllByIdForUpdate(@Param("ids") Collection<Long> ids);
}

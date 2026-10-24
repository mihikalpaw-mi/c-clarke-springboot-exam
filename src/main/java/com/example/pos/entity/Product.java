package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column( name = "product_code" , nullable = false)
    private String code ;

    @Column( name = "product_name" , length = 100 , nullable = false)
    private String name ;

    @Column( name = "unit_price" , nullable = false)
    private double unitPrice ;

    @Column( name = "available_quantity" )
    private Integer availableQuantity ;

    @Column( name = "is_active" , nullable = false)
    private boolean active ;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "category_id" )
    private Category category;
}

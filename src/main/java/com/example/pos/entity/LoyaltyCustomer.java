package com.example.pos.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table( name = "loyalty_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCustomer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column( name = "mobile_number" , unique = true , nullable = false)
    private String mobileNumber ;

    @Column( name = "full_name" , nullable = false , length = 200)
    private String fullName ;

    @Column( name = "email" , nullable = false , unique = true)
    private String email ;

    @Column( name = "loyalty_points")
    private Integer loyaltyPoints ;

    @Column( name = "registered_at" , nullable = false)
    private LocalDateTime registeredAt ;
}

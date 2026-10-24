package com.example.pos.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "customer_id" , nullable = false)
    private LoyaltyCustomer customer ;

    @Column( name = "order_date_time")
    private LocalDateTime orderDateTime ;

    @Enumerated( EnumType.STRING)
    @Column( name = "order_status" , nullable = false)
    private Status orderStatus ;

    @Column( name = "total_amount" , nullable = false)
    private double totalAmount ;

    @Column( name = "loyalty_points_earned")
    private Integer loyaltyPointsEarned ;

}

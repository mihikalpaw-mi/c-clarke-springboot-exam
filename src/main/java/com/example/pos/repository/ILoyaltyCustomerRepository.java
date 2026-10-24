package com.example.pos.repository;

import com.example.pos.entity.LoyaltyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILoyaltyCustomerRepository extends JpaRepository<LoyaltyCustomer , Long > {

    Optional<LoyaltyCustomer> findByMobileNumber(String mobileNumber);
    boolean existsByEmail(String email);
}

package com.example.pos.repository;

import com.example.pos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order , Long > {
}

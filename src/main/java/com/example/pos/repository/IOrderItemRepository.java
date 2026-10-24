package com.example.pos.repository;

import com.example.pos.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem , Long> {
}

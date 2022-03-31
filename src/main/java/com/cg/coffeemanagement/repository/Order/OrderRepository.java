package com.cg.coffeemanagement.repository.Order;

import com.cg.coffeemanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> getByCoffeeTableId(Long id);

    void deleteOrderById(Long id);
}

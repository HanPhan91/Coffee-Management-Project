package com.cg.coffeemanagement.repository.Order;

import com.cg.coffeemanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> getByCoffeeTable(Long id);

    void deleteOrderById(Long id);


}

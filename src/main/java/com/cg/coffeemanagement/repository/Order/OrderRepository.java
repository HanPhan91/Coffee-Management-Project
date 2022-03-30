package com.cg.coffeemanagement.repository.Order;

import com.cg.coffeemanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Order getByCoffeeTableId(Long id);
}

package com.cg.coffeemanagement.repository.OrderItem;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    void deleteAllByOrder(Order order);

    List<OrderItem> findAllByOrder(Order order);
}

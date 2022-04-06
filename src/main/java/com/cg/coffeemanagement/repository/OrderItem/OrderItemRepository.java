package com.cg.coffeemanagement.repository.OrderItem;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    void deleteAllByOrder(Order order);

    List<OrderItem> findAllByOrder(Order order);

    @Query("SELECT SUM(o.totalPrice) from OrderItem o WHERE o.order.id = :id")
    BigDecimal calcSubAmount(@Param("id") Long id);
}

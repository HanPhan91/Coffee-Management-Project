package com.cg.coffeemanagement.services.Order;

import com.cg.coffeemanagement.model.Discount;
import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService extends IGeneralServices<Order> {

    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long id);

    @Override
    Order getById(Long id);

    @Override
    Order save(Order cart);

    @Override
    void remove(Long id);

    void deleteCartById(@Param("id") Long id);

//    void restoreCart(@Param("id") Long id);

    Optional<Order> getByCoffeeTable(Long id);

    void deleteOrderById(Long id);
    void doSplitOrder(Order oldOrder, Order newOrder, List<OrderItemDto> listOrderSplit, Discount discount, User user);

}

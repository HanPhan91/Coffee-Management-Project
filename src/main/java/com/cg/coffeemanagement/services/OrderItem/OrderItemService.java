package com.cg.coffeemanagement.services.OrderItem;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderItemService extends IGeneralServices<OrderItem> {

    @Override
    List<OrderItem> findAll();

    @Override
    Optional<OrderItem> findById(Long id);

    @Override
    OrderItem getById(Long id);

    @Override
    OrderItem save(OrderItem cartItem);

    @Override
    void remove(Long id);

    void deleteCartItem(@Param("id") Long id);

    void restoreCartItem(@Param("id") Long id);

    List<OrderItem> findAllNotDeleted();

    List<OrderItem> findAllDeleted();

    void deletedCartItemByCatalog(@Param("idCatalog") Long idCatalog);

    void restoreCartItemByCatalog(@Param("idCatalog") Long idCatalog);

    void deleteAllByOrder(Order order);
}

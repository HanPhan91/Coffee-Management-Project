package com.cg.coffeemanagement.services.OrderItem;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.model.dto.OrderItemMenuDto;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
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

    List<OrderItem> findAllByOrder(Order order);

//    List<OrderItemMenuDto> findAllOrderItemMenuDTOByOrder(String idOrder);

    List<OrderItemMenuDto> findAllOrderItemMenuDTOByOrder(Order objOrder);

    BigDecimal calcSubAmount(@Param("order") Long id);

}

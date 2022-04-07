package com.cg.coffeemanagement.repository.OrderItem;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.model.dto.OrderItemMenuDto;
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


//    @Query("SELECT NEW com.cg.coffeemanagement.model.dto.OrderItemMenuDto (od.id, od.drink.name, od.quantity, od.totalPrice) " +
//            "FROM OrderItem od " +
//            "WHERE od.order.id =:idOrder ")
//    List<OrderItemMenuDto> findAllOrderItemMenuDTOByOrder(@Param("idOrder") String idOrder);

    @Query("SELECT NEW com.cg.coffeemanagement.model.dto.OrderItemMenuDto (od.id, od.drink.name, od.quantity, od.totalPrice) " +
            "FROM OrderItem od " +
            "WHERE od.order =:objOrder ")
    List<OrderItemMenuDto> findAllOrderItemMenuDTOByOrder(@Param("objOrder") Order objOrder);
}

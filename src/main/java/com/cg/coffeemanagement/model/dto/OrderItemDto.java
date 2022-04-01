package com.cg.coffeemanagement.model.dto;


import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.repository.Drink.DrinkRepository;
import com.cg.coffeemanagement.repository.Order.OrderRepository;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @Autowired
    DrinkService drinkService;
    @Autowired
    OrderRepository orderRepository;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long drink;

    private int quantity;

    private BigDecimal totalPrice;

    public OrderItem toOderItem() {
        return new OrderItem()
                .setQuantity(quantity);
    }
}

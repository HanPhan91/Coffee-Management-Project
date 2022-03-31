package com.cg.coffeemanagement.model.dto;


import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.repository.Drink.DrinkRepository;
import com.cg.coffeemanagement.repository.Order.OrderRepository;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    OrderRepository orderRepository;

    private Long id = System.currentTimeMillis() / 1000;

    private Long drink;

    private Long order;

    private int quantity;

    private BigDecimal totalPrice;

    public OrderItem toOderItem() {
        return new OrderItem()
                .setId(id)
                .setDrink(drinkRepository.findById(drink).get())
                .setQuantity(quantity)
                .setTotalPrice(drinkRepository.findById(drink).get().getPrice().multiply(BigDecimal.valueOf(quantity)));
    }
}

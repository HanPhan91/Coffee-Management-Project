package com.cg.coffeemanagement.model.dto;

import com.cg.coffeemanagement.model.Drink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderItemMenuDto {

    private Long id;

    private String name;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String discount;

    public OrderItemDto toOrderItemDto(Drink drink) {
        return new OrderItemDto()
                .setId(id)
                .setDrink(drink)
                .setQuantity(quantity)
                .setPrice(price)
                .setDiscount(discount);
    }

}

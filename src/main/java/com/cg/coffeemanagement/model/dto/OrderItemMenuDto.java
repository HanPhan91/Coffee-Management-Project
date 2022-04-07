package com.cg.coffeemanagement.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItemMenuDto {

    private Long id;

    private String name;

    private int quantity;

    private BigDecimal totalPrice;

    public OrderItemMenuDto(Long id, String name, int quantity, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

//    public OrderItemDto toOrderItemDto() {
//        return new OrderItemDto()
//                .setId(id)
//                .set
//    }
}

package com.cg.coffeemanagement.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private Long id;

    private Long drink;

    private Long cart;

    private int quantity;

    private BigDecimal totalPrice;


}

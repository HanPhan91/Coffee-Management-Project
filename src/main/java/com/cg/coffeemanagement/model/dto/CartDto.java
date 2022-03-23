package com.cg.coffeemanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDto {

    private Long id;

    private int quantity;

    private BigDecimal price;

    private String drink;

    private String table;

}

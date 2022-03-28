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
public class DrinkDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private Long catalog;

    private String description;

    private int inventory;
}

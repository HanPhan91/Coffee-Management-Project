package com.cg.coffeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    private Long id = System.currentTimeMillis()/1000;

    @OneToMany
    @JoinColumn(name = "id_cart")
    private Set<CartItem> cartItem;

    @OneToOne
    @JoinColumn(name = "id_table")
    private CoffeeTable coffeeTable;

    private BigDecimal totalPrice;

}

package com.cg.coffeemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Drink drink;

    private int quantity;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;

    private BigDecimal totalPrice;

    private String discount;

    public BillDetail toBillDetail() {
        return new BillDetail()
                .setId(id)
                .setDrink(drink.getName())
                .setQuantity(quantity)
                .setDrinkPrice(drink.getPrice())
                .setTotalPrice(totalPrice);
    }


}

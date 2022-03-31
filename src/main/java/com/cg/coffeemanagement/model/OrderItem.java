package com.cg.coffeemanagement.model;

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
    private Long id = System.currentTimeMillis()/1000;

    @OneToOne
    @JoinColumn(name = "id_drink")
    private Drink drink;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cart")
    private Order order;

    private BigDecimal totalPrice;

    public BillDetail toBillDetail(){
        return new BillDetail()
                .setId(id)
                .setDrink(drink)
                .setQuantity(quantity)
                .setTotalPrice(totalPrice);
    }
}

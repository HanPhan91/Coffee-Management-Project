package com.cg.coffeemanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private Long id = System.currentTimeMillis()/1000;

    @Column(name = "percent_discount")
    private int percentDiscount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;



}

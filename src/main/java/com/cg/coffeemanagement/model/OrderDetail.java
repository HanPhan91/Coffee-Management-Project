package com.cg.coffeemanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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


}

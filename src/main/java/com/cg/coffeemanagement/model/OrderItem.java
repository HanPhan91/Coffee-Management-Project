package com.cg.coffeemanagement.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    private Long id = System.currentTimeMillis()/1000;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order ;
}

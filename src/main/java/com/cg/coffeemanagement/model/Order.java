package com.cg.coffeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "orders")
public class Order {

    @Id
    private Long id = System.currentTimeMillis()/1000;

    @OneToMany
    @JoinColumn(name = "id_orderitem")
    private Set<OrderItem> orderItem;

    @OneToOne
    @JoinColumn(name = "id_table")
    private CoffeeTable coffeeTable;

    @Column(name= "sub_amount")
    private BigDecimal subAmount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "id_table")
    private CoffeeTable table ;

    public Order newOrder(){
        return new Order()
                .setId(id)
                .setTable(table);
    }
}

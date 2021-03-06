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

//    @OneToMany
//    @JoinColumn(name = "id_orderitem")
//    private Set<OrderItem> orderItem;

    @Column(name = "table_id")
    private Long coffeeTable;

    @Column(name= "sub_amount")
    private BigDecimal subAmount;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "staff_name")
    private String staffName;

    public Order newOrder(){
        return new Order()
                .setId(id)
                .setCoffeeTable(coffeeTable);
    }
}

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
@Table(name = "bill_detail")
@Accessors(chain = true)
public class BillDetail {

    @Id
    private Long id = System.currentTimeMillis()/1000;

    @OneToOne
    @JoinColumn(name = "id_drink")
    private Drink drink;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_bill")
    private Bill bill;

    private BigDecimal totalPrice;
}

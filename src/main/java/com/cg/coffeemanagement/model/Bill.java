package com.cg.coffeemanagement.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Table;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bills")

public class Bill {
    @Id
    private Long id = System.currentTimeMillis() / 1000;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "id_table")
    private CoffeeTable table;

    @OneToMany
    @JoinColumn(name = "id_billitem")
    private Set<BillDetail> billDetails;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "sub_amount")
    private BigDecimal subAmount;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}

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

    @Column(name = "table_name")
    private String coffeeTable;

    @OneToMany
    @JoinColumn(name = "id_billitem")
    private Set<BillDetail> billDetails;

    @Column(name = "code_discount")
    private String codeDiscount;

    @Column(name = "percent_discount")
    private String discountPercent;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "sub_amount")
    private BigDecimal subAmount;

    @Column(name = "staff_name")
    private String staffName;

}

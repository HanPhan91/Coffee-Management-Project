package com.cg.coffeemanagement.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "drinks")
public class Drink {
    @Id
    private Long id = System.currentTimeMillis() / 1000;

    @Column(name = "drink_name")
    private String name;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal price;

    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "id_catalog")
    private Catalog catalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cart")
    private Order cart;

}
